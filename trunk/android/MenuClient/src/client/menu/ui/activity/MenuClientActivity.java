package client.menu.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.app.MyAppLocale;
import client.menu.app.MyApplication;
import client.menu.db.dao.NgonNguDAO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.util.U;

public class MenuClientActivity extends Activity implements OnItemSelectedListener {

    // public static final int LOADER_ID_LANGUAGE_LIST = 0;

    int mSelIndex;

    Spinner mSpinner;
    SimpleCursorAdapter mLanguageAdapter;

    LoadLanguageListTask mLoadLanguageListTask;

    class LoadLanguageListTask extends AsyncTask<Void, Integer, Cursor> {

        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);
            mLanguageAdapter.swapCursor(result);
            mSpinner.setSelection(mSelIndex);
        }

        @Override
        protected Cursor doInBackground(Void... params) {
            return NgonNguDAO.getInstance().cursorAll();
        }

    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getSettings(this).getLocale().applyLanguage(this);

        setContentView(R.layout.layout_main);

        mSpinner = (Spinner) findViewById(R.id.spinner1);
        String[] from = new String[] { NgonNguDTO.CL_TEN_NGON_NGU };
        int[] to = new int[] { android.R.id.text1 };
        mLanguageAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item,
                null, from, to, 0);
        mLanguageAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(mLanguageAdapter);
        mSpinner.setOnItemSelectedListener(this);

        // new LoadLanguageListTask().execute();
        // getLoaderManager().initLoader(LOADER_ID_LANGUAGE_LIST, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mLoadLanguageListTask = new LoadLanguageListTask();
        mLoadLanguageListTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mLoadLanguageListTask.getStatus() != AsyncTask.Status.FINISHED) {
            mLoadLanguageListTask.cancel(true);
        } else {
            mLanguageAdapter.swapCursor(null);
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.TableListBtn) {
            Intent intent = new Intent(this, TableListActivity.class);
            startActivity(intent);
        } else if (v == findViewById(R.id.MainMenuBtn)) {
            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        } else if (v == findViewById(R.id.SplitTableBtn)) {
            Intent intent = new Intent(this, SplitTableActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.testBtn) {
            Intent intent = new Intent(this, BillActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("mSelIndex", mSelIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mSelIndex = savedInstanceState.getInt("mSelIndex");
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
        if (arg0 == mSpinner) {
            mSelIndex = pos;
            Cursor cursor = ((SimpleCursorAdapter) arg0.getAdapter()).getCursor();
            if (cursor.moveToPosition(pos)) {
                NgonNguDTO ngonNgu = NgonNguDTO.valueOf(cursor);
                MyAppLocale locale = MyApplication.getSettings(this).getLocale();
                if (locale.applyLanguage(ngonNgu, this)) {
                    U.restartActivity(this);
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}