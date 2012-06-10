package client.menu.ui.activity;

import client.menu.R;
import client.menu.app.MyApplication;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.bus.task.SyncDbTask;
import client.menu.util.C;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashScreenActivity extends Activity implements
        OnPostExecuteAsyncTaskListener<Void, String, Boolean>, OnClickListener {

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
            new AlertDialog.Builder(SplashScreenActivity.this)
                    .setMessage(R.string.message_confirm_sync_db)
                    .setCancelable(false)
                    .setPositiveButton(R.string.caption_yes,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    SyncDbTask task = new SyncDbTask(
                                            SplashScreenActivity.this);
                                    task.setOnPostExecuteListener(SplashScreenActivity.this);
                                    task.execute();
                                }
                            })
                    .setNegativeButton(R.string.caption_no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
//                                    nextScreen();
                                }
                            }).create().show();

        }
//        else {
//            nextScreen();
//        }

        Button btnTableMap = (Button) findViewById(R.id.btnTableMap);
        btnTableMap.setOnClickListener(this);
        Button btnPreferences = (Button) findViewById(R.id.btnPreferences);
        btnPreferences.setOnClickListener(this);
    }

//    private void nextScreen() {
//        Intent intent = new Intent(this, AppPreferenceActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onPostExecuteAsyncTask(CustomAsyncTask<Void, String, Boolean> task,
            Boolean result) {
//        nextScreen();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTableMap:
                Intent intent = new Intent(SplashScreenActivity.this,
                        TableMapActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPreferences:
                intent = new Intent(SplashScreenActivity.this,
                        AppPreferenceActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }

    }

}
