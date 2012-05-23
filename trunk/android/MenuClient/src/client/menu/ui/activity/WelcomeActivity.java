package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.bus.LanguageListLoader;
import client.menu.db.dto.NgonNguDTO;

public class WelcomeActivity extends Activity {
    private static final int LD_LANGUAGE_LIST = 0;

    SimpleAdapter mLanguageAdapter;
    List<Map<String, Object>> mLanguageAdapterData = new ArrayList<Map<String, Object>>();

    LoaderCallbacks<List<Map<String, Object>>> mLanguageListLoaderCallbacks = new LoaderCallbacks<List<Map<String, Object>>>() {

        @Override
        public void onLoaderReset(Loader<List<Map<String, Object>>> arg0) {
            mLanguageAdapterData.clear();
            mLanguageAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLoadFinished(Loader<List<Map<String, Object>>> arg0,
                List<Map<String, Object>> arg1) {
            mLanguageAdapterData.clear();
            mLanguageAdapterData.addAll(arg1);
            mLanguageAdapter.notifyDataSetChanged();
        }

        @Override
        public Loader<List<Map<String, Object>>> onCreateLoader(int id, Bundle args) {
            return new LanguageListLoader(WelcomeActivity.this);
        }
    };

    OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnMainMenu:

                    break;

                case R.id.btnPayment:

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);

        Button btnMainMenu = (Button) findViewById(R.id.btnMainMenu);
        Button btnPayment = (Button) findViewById(R.id.btnPayment);

        btnMainMenu.setOnClickListener(mOnClickListener);
        btnPayment.setOnClickListener(mOnClickListener);

        String[] from = new String[] { NgonNguDTO.CL_TEN_NGON_NGU };
        int[] to = new int[] { android.R.id.text1 };
        mLanguageAdapter = new SimpleAdapter(WelcomeActivity.this, mLanguageAdapterData,
                android.R.layout.simple_spinner_item, from, to);
        mLanguageAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinLanguage = (Spinner) findViewById(R.id.spinLanguage);
        spinLanguage.setAdapter(mLanguageAdapter);

        getLoaderManager().initLoader(LD_LANGUAGE_LIST, null,
                mLanguageListLoaderCallbacks);
    }
}
