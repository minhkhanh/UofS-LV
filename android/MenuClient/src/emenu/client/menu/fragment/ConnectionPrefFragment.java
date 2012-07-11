package emenu.client.menu.fragment;

import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetTestServerTask;
import emenu.client.dao.AbstractDAO;
import emenu.client.menu.R;
import emenu.client.util.C;
import emenu.client.util.U;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.text.TextUtils;

public class ConnectionPrefFragment extends PreferenceFragment implements
        OnPreferenceChangeListener, OnPostExecuteListener<String, Void, String> {
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
    }

    private String formatAddress() {
        String servAddr = mServerAddrPref.getText();
        if (!TextUtils.isEmpty(servAddr)) {
            if (!servAddr.endsWith("/"))
                servAddr += "/";
            if (!servAddr.startsWith(C.SERVER_PREFIX)) {
                servAddr = C.SERVER_PREFIX + servAddr;
            }
            mServerAddrPref.setText(servAddr);

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
        String servAddr = formatAddress();
        
        editor.putString(key, servAddr);
        editor.commit();

        AbstractDAO.SERVER_URL_SLASH = servAddr;
    }

    @Override
    public boolean onPreferenceChange(Preference arg0, Object arg1) {
        U.cancelAsyncTask(mTestServerTask);

        String addr = formatAddress();

        mTestServerTask = new GetTestServerTask();
        mTestServerTask.setOnPostExecuteListener(this);
        mTestServerTask.setWaitingDialog(U.createWaitingDialog(getActivity()));
        mTestServerTask.execute(addr);

        return true;
    }

    @Override
    public void onPostExecute(CustomAsyncTask<String, Void, String> task, String result) {
        if (result != null) {
            new AlertDialog.Builder(getActivity()).setMessage(result).create().show();
        } else {
            U.showErrorDialog(getActivity(), R.string.message_connect_server_failed);
        }
    }
}
