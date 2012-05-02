package client.menu.activity;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.db.contract.NgonNguContract;
import client.menu.util.AppSettings;
import client.menu.util.C;
import client.menu.util.Utilitiy;

public class MenuClientActivity extends Activity implements LoaderCallbacks<Cursor>,
        OnItemSelectedListener {

    public static final int LOADER_ID_LANGUAGE_LIST = 0;

    Spinner mSpinner;
    SimpleCursorAdapter langAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppSettings.appLocale.applyLanguage(this);
        setContentView(R.layout.layout_main);

        mSpinner = (Spinner) findViewById(R.id.spinner1);
        String[] from = new String[] { NgonNguContract.COL_DISPLAY_NAME };
        int[] to = new int[] { android.R.id.text1 };
        langAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item,
                null, from, to, 0);
        langAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(langAdapter);
        mSpinner.setOnItemSelectedListener(this);

        getLoaderManager().initLoader(LOADER_ID_LANGUAGE_LIST, null, this);
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
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ID_LANGUAGE_LIST:
                String[] proj = new String[] { NgonNguContract._ID,
                        NgonNguContract.COL_DISPLAY_NAME, NgonNguContract.COL_ABBREVIATE };
                CursorLoader loader = new CursorLoader(MenuClientActivity.this,
                        NgonNguContract.CONTENT_URI, proj, null, null, null);

                return loader;
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
        switch (arg0.getId()) {
            case LOADER_ID_LANGUAGE_LIST:
                langAdapter.swapCursor(cursor);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        switch (arg0.getId()) {
            case LOADER_ID_LANGUAGE_LIST:
                langAdapter.swapCursor(null);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long id) {
        if (arg0 == mSpinner) {
            Cursor cursor = ((SimpleCursorAdapter) arg0.getAdapter()).getCursor();
            if (cursor.moveToPosition(pos)) {
                String lang = cursor.getString(cursor
                        .getColumnIndex(NgonNguContract.COL_ABBREVIATE));

//                Log.d(C.TAG, lang + " : " + AppSettings.appLocale.getLanguage());
                if (!AppSettings.appLocale.getLanguage().equals(lang)) {
                    AppSettings.appLocale.setLanguage(lang);
                    Utilitiy.restartActivity(MenuClientActivity.this);
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}