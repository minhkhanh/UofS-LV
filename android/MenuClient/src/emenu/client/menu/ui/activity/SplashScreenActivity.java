package emenu.client.menu.ui.activity;

import client.menu.R;
import emenu.client.menu.app.MyApplication;
import emenu.client.menu.bus.task.CustomAsyncTask;
import emenu.client.menu.bus.task.SyncDbTask;
import emenu.client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.menu.ui.fragment.HomeNavigationDlgFragment;
import emenu.client.menu.util.C;
import emenu.client.menu.util.U;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashScreenActivity extends Activity implements
        OnPostExecuteListener<Void, String, Boolean>, OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getSettings(this).getLocale()
                .applyLanguage(getApplicationContext());

        setContentView(R.layout.layout_splash);

        SharedPreferences sharedPref = getSharedPreferences(C.SHARED_PREF_FILE, 0);
        boolean syncFlag = sharedPref.getBoolean(getString(R.string.key_pref_auto_sync),
                false);

        if (syncFlag) {
            U.showConfirmDialog(this, R.string.message_confirm_sync_db,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            SyncDbTask task = new SyncDbTask(new ProgressDialog(
                                    SplashScreenActivity.this));
                            task.setOnPostExecuteListener(SplashScreenActivity.this);
                            task.execute();
                        }
                    });
        }

        // Button btnTableMap = (Button) findViewById(R.id.btnTableMap);
        // btnTableMap.setOnClickListener(this);
        // Button btnPreferences = (Button) findViewById(R.id.btnPreferences);
        // btnPreferences.setOnClickListener(this);

        findViewById(android.R.id.content).setOnClickListener(this);
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Void, String, Boolean> task, Boolean result) {
        // nextScreen();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.content:
                HomeNavigationDlgFragment dlg = new HomeNavigationDlgFragment();
                U.showDlgFragment(this, dlg, "dlg");
                break;

            default:
                break;
        }

    }

}
