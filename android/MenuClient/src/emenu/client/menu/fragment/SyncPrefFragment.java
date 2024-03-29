package emenu.client.menu.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.SyncDbTask;
import emenu.client.menu.R;
import emenu.client.util.C;
import emenu.client.util.U;

public class SyncPrefFragment extends PreferenceFragment implements
        OnPostExecuteListener<Void, String, Boolean> {

    Preference mSyncNowPref;
    CheckBoxPreference mAutoSyncPref;

    SharedPreferences mSharedPref;

    long mWhenSyncDone = -1;

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
                    + getString(R.string.text_at_time) + " "
                    + U.formatDateTime(C.LONG_DATETIME_FORMAT, mWhenSyncDone));
        } else {
            mSyncNowPref.setSummary(R.string.text_unsync);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            Preference preference) {
        if (getString(R.string.key_pref_sync_now).equals(preference.getKey())) {
            U.showConfirmDialog(getActivity(), R.string.message_confirm_sync_db,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            SyncDbTask task = new SyncDbTask(getActivity());
                            task.setOnPostExecuteListener(SyncPrefFragment.this);
                            task.execute();
                        }
                    });
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Void, String, Boolean> task, Boolean result) {
        if (result) {
            mWhenSyncDone = System.currentTimeMillis();
            String whenString = U.formatDateTime(C.LONG_DATETIME_FORMAT, mWhenSyncDone);
            mSyncNowPref.setSummary(getString(R.string.message_sync_succeed) + " "
                    + getString(R.string.text_at_time) + " " + whenString);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = mSharedPref.edit();

        String key = getString(R.string.key_pref_auto_sync);
        boolean syncFlag = mAutoSyncPref.isChecked();
        editor.putBoolean(key, syncFlag);

        // if (mWhenSyncDone != -1) {
        // key = getString(R.string.key_pref_sync_now);
        // editor.putLong(key, mWhenSyncDone);
        // }

        editor.commit();
    }
}
