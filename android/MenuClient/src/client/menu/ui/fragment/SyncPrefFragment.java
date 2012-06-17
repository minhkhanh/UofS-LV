package client.menu.ui.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import client.menu.R;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import client.menu.bus.task.SyncDbTask;
import client.menu.util.C;
import client.menu.util.U;

public class SyncPrefFragment extends PreferenceFragment implements
        OnPostExecuteListener<Void, String, Boolean> {

    Preference mSyncNowPref;
    CheckBoxPreference mAutoSyncPref;

    SharedPreferences mSharedPref;

    long mWhenSyncDone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference_syncing);

        mSyncNowPref = getPreferenceManager().findPreference(
                getString(R.string.key_pref_sync_now));
        mAutoSyncPref = (CheckBoxPreference) getPreferenceManager().findPreference(
                getString(R.string.key_pref_auto_sync));

        mSharedPref = getActivity().getSharedPreferences(C.SHARED_PREF_FILE, 0);
    }

    @Override
    public void onStart() {
        super.onStart();

        String key = getString(R.string.key_pref_auto_sync);
        boolean syncFlag = mSharedPref.getBoolean(key, false);
        mAutoSyncPref.setChecked(syncFlag);

        key = getString(R.string.key_pref_sync_now);
        mWhenSyncDone = mSharedPref.getLong(key, -1);
        if (mWhenSyncDone != -1) {
            mSyncNowPref.setSummary(getString(R.string.message_sync_succeed) + " "
                    + getString(R.string.text_at) + " "
                    + U.formatDateTime(C.LONG_DATETIME_FORMAT, mWhenSyncDone));
        } else {
            mSyncNowPref.setSummary(R.string.text_unsync);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            Preference preference) {
        if (getString(R.string.key_pref_sync_now).equals(preference.getKey())) {

            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.message_confirm_sync_db)
                    .setPositiveButton(R.string.caption_yes,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    SyncDbTask task = new SyncDbTask(new ProgressDialog(getActivity()));
                                    task.setOnPostExecuteListener(SyncPrefFragment.this);
                                    task.execute();
                                }
                            })
                    .setNegativeButton(R.string.caption_no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).create().show();

        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Void, String, Boolean> task,
            Boolean result) {
        if (result) {
            mWhenSyncDone = System.currentTimeMillis();
            String whenString = U.formatDateTime(C.LONG_DATETIME_FORMAT, mWhenSyncDone);
            mSyncNowPref.setSummary(getString(R.string.message_sync_succeed) + " "
                    + getString(R.string.text_at) + " " + whenString);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = mSharedPref.edit();

        String key = getString(R.string.key_pref_auto_sync);
        boolean syncFlag = mAutoSyncPref.isChecked();
        editor.putBoolean(key, syncFlag);
        editor.commit();
    }
}
