package client.menu.ui.activity;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.app.AppLocale;
import client.menu.app.ApplicationSettings;
import client.menu.app.MyApplication;
import client.menu.db.contract.NgonNguContract;
import client.menu.db.dto.NgonNguDTO;
import client.menu.util.U;

public class MenuClientActivity extends Activity implements LoaderCallbacks<Cursor>,
        OnItemSelectedListener {

    public static final int LOADER_ID_LANGUAGE_LIST = 0;

    Spinner mSpinner;
    SimpleCursorAdapter langAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.gSettings.getLocale().applyLanguage(this);
        setContentView(R.layout.layout_main);

        mSpinner = (Spinner) findViewById(R.id.spinner1);
        String[] from = new String[] { NgonNguContract.COL_TEN_NGON_NGU };
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
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ID_LANGUAGE_LIST:
                String[] proj = new String[] { NgonNguContract._ID,
                        NgonNguContract.COL_MA_NGON_NGU, NgonNguContract.COL_TEN_NGON_NGU,
                        NgonNguContract.COL_KI_HIEU };
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
                String abbr = cursor.getString(cursor
                        .getColumnIndex(NgonNguContract.COL_KI_HIEU));

                AppLocale locale = MyApplication.gSettings.getLocale();
                String settAbbr = locale.loadLangAbbr();
                if (settAbbr == null || !settAbbr.equals(abbr)) {
                    locale.setLanguage(NgonNguDTO.extractFrom(cursor));

                    if (!locale.loadLangAbbr().equals(settAbbr)) {
                        U.restartActivity(MenuClientActivity.this);
                    }
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}