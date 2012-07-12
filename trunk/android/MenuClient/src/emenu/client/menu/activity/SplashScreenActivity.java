package emenu.client.menu.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import emenu.client.bus.task.SyncDbTask;
import emenu.client.menu.R;
import emenu.client.menu.fragment.HomeNavigationDlgFragment;
import emenu.client.util.C;
import emenu.client.util.U;

public class SplashScreenActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        SharedPreferences sharedPref = getSharedPreferences(C.SHARED_PREF_FILE, 0);
        boolean syncFlag = sharedPref.getBoolean(getString(R.string.key_pref_auto_sync),
                false);

        if (syncFlag) {
            U.showConfirmDialog(this, R.string.message_confirm_sync_db,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            SyncDbTask task = new SyncDbTask(getApplicationContext());
                            task.execute();
                        }
                    });
        }

        findViewById(android.R.id.content).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.content:
                HomeNavigationDlgFragment dlg = new HomeNavigationDlgFragment();
                U.showDlgFragment(this, dlg, true);
                break;

            default:
                break;
        }
    }
}
