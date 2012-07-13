package emenu.client.menu.fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetTestServerTask;
import emenu.client.dao.AbstractDAO;
import emenu.client.menu.R;
import emenu.client.util.C;
import emenu.client.util.U;

public class ConnectionPrefFragment extends PreferenceFragment implements
        OnPreferenceChangeListener, OnPostExecuteListener<String, Void, String> {

    public static final String KEY_LATEST_TEST = "KEY_LATEST_TEST";

    EditTextPreference mServerAddrPref;
    private GetTestServerTask mTestServerTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference_connection);

        mServerAddrPref = (EditTextPreference) getPreferenceManager().findPreference(
                getString(R.string.key_pref_server_address));
        mServerAddrPref.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                C.SHARED_PREF_FILE, 0);

        String key = getString(R.string.key_pref_server_address);
        String servAddr = sharedPref.getString(key, AbstractDAO.SERVER_URL_SLASH);
        mServerAddrPref.setText(servAddr);

        String summary = sharedPref.getString(KEY_LATEST_TEST, "");
        if (!TextUtils.isEmpty(summary))
            mServerAddrPref.setSummary(summary);
    }

    private String formatAddress(String servAddr) {
        if (!TextUtils.isEmpty(servAddr)) {
            if (!servAddr.endsWith("/"))
                servAddr += "/";

            return servAddr;
        }

        return "";
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                C.SHARED_PREF_FILE, 0);
        SharedPreferences.Editor editor = sharedPref.edit();

        String key = getString(R.string.key_pref_server_address);
        String servAddr = formatAddress(mServerAddrPref.getText());

        editor.putString(key, servAddr);

        String summary = mServerAddrPref.getSummary().toString();
        editor.putString(KEY_LATEST_TEST, summary);

        editor.commit();

        AbstractDAO.SERVER_URL_SLASH = servAddr;
    }

    @Override
    public boolean onPreferenceChange(Preference arg0, Object arg1) {
        U.cancelAsyncTask(mTestServerTask);

        String addr = formatAddress(arg1.toString());

        mTestServerTask = new GetTestServerTask();
        mTestServerTask.setOnPostExecuteListener(this);
        mTestServerTask.setWaitingDialog(U.createWaitingDialog(getActivity()));
        mTestServerTask.execute(addr);

        return true;
    }

    @Override
    public void onPostExecute(CustomAsyncTask<String, Void, String> task, String result) {
        String summary = "";
        if (result != null) {
            new AlertDialog.Builder(getActivity()).setMessage(result).create().show();

            summary += getString(R.string.message_test_connection_ok);
        } else {
            U.showErrorDialog(getActivity(), R.string.message_connect_server_failed);

            summary += getString(R.string.message_test_connection_failed);
        }

        summary += " " + getString(R.string.text_at_time) + " "
                + U.formatDateTime(C.LONG_DATETIME_FORMAT, System.currentTimeMillis());

        mServerAddrPref.setSummary(summary);
    }
}
