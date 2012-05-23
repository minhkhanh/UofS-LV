package client.menu.ui.activity;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.app.MyAppLocale;
import client.menu.app.MyAppSettings;
import client.menu.bus.loader.LanguageListLoader;
import client.menu.db.dto.NgonNguDTO;
import client.menu.util.U;

public class WelcomeActivity extends Activity implements OnClickListener,
        OnItemSelectedListener, LoaderCallbacks<Cursor> {
    SimpleCursorAdapter mLanguageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);

        Button btnMainMenu = (Button) findViewById(R.id.btnMainMenu);
        Button btnPayment = (Button) findViewById(R.id.btnPayment);

        btnMainMenu.setOnClickListener(this);
        btnPayment.setOnClickListener(this);

        String[] from = new String[] { NgonNguDTO.CL_TEN_NGON_NGU };
        int[] to = new int[] { android.R.id.text1 };
        mLanguageAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item, null, from, to, 0);
        mLanguageAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinLanguage = (Spinner) findViewById(R.id.spinLanguage);
        spinLanguage.setAdapter(mLanguageAdapter);
        spinLanguage.setOnItemSelectedListener(this);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMainMenu:
                Intent intent = new Intent(this, MainMenuActivity.class);
                startActivity(intent);
                break;

            case R.id.btnPayment:
                intent = new Intent(this, BillActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        switch (arg0.getId()) {
            case R.id.spinLanguage:
                Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
                NgonNguDTO ngonNgu = NgonNguDTO.valueOf(cursor);
                MyAppLocale locale = MyAppSettings.getCurrentAppLocale(this);
                if (locale.applyLanguage(ngonNgu, this)) {
                    U.restartActivity(this);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mLanguageAdapter.swapCursor(null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        mLanguageAdapter.swapCursor(arg1);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new LanguageListLoader(this);
    }
}
