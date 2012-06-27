package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

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
import client.menu.app.MyApplication;
import client.menu.bus.loader.LanguageListLoader;
import client.menu.db.dto.NgonNguDTO;
import client.menu.ui.adapter.LanguageListAdapter;
import client.menu.ui.view.LanguageSpinner;
import client.menu.util.U;

public class WelcomeActivity extends Activity implements OnClickListener,
        OnItemSelectedListener, LoaderCallbacks<List<NgonNguDTO>> {
    SimpleCursorAdapter mLanguageAdapter2;
    
    LanguageListAdapter mLanguageAdapter;
    
    List<NgonNguDTO> mAdapterData = new ArrayList<NgonNguDTO>();

    private LanguageSpinner mSpinLanguage;
    private int mSelIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);
        
        if (savedInstanceState != null) {
            mSelIndex = savedInstanceState.getInt("mSelIndex");
        }

        Button btnMainMenu = (Button) findViewById(R.id.btnMainMenu);
        Button btnPayment = (Button) findViewById(R.id.btnPayment);

        btnMainMenu.setOnClickListener(this);
        btnPayment.setOnClickListener(this);

//        String[] from = new String[] { NgonNguDTO.CL_TEN_NGON_NGU };
//        int[] to = new int[] { android.R.id.text1 };
//        mLanguageAdapter2 = new SimpleCursorAdapter(this,
//                android.R.layout.simple_spinner_item, null, from, to, 0);
//        mLanguageAdapter2
//                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguageAdapter = new LanguageListAdapter(this, new ArrayList<NgonNguDTO>());
        mSpinLanguage = (LanguageSpinner) findViewById(R.id.spinLanguage);
//        spinLanguage.setAdapter(mLanguageAdapter2);
        mSpinLanguage.setAdapter(mLanguageAdapter);
        mSpinLanguage.setOnItemSelectedListener(this);

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
//                Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
                mSelIndex = arg2;
                NgonNguDTO ngonNgu = mLanguageAdapter.getItem(arg2);
                MyAppLocale locale = MyApplication.getSettings(this).getLocale();
                if (locale.applyLanguage(ngonNgu, getApplicationContext())) {
                    U.restartActivity(this);
                }
                break;

            default:
                break;
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mSelIndex", mSelIndex);
        U.logOwnTag("on save");
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        
        U.logOwnTag("on restore");
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    @Override
    public void onLoaderReset(Loader<List<NgonNguDTO>> arg0) {
//        mLanguageAdapter2.swapCursor(null);
//        mLanguageAdapter = new LanguageListAdapter(this, new );
//        mLanguageAdapter.clear();
//        mLanguageAdapter.notifyDataSetChanged();
        U.logOwnTag("load reset");
    }
    
    @Override
    public void onLoadFinished(Loader<List<NgonNguDTO>> arg0, List<NgonNguDTO> arg1) {
//        mLanguageAdapter2.swapCursor(arg1);
//        mLanguageAdapter = new LanguageListAdapter(this, arg1);
//        mLanguageAdapter.clear();
        mLanguageAdapter.addAll(arg1);
        mLanguageAdapter.notifyDataSetChanged();
        
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(this);
        mSpinLanguage.selectItem(ngonNgu);
        U.logOwnTag("load finish");
    }

    @Override
    public Loader<List<NgonNguDTO>> onCreateLoader(int id, Bundle args) {
        U.logOwnTag("load create");
        return new LanguageListLoader(this);
    }
}
