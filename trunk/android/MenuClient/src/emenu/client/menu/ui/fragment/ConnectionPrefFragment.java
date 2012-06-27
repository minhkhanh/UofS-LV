package emenu.client.menu.ui.fragment;

import client.menu.R;
import emenu.client.menu.dao.AbstractDAO;
import emenu.client.menu.util.C;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;

public class ConnectionPrefFragment extends PreferenceFragment {
    EditTextPreference mServerAddrPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference_connection);

        mServerAddrPref = (EditTextPreference) getPreferenceManager().findPreference(
                getString(R.string.key_pref_server_address));
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                C.SHARED_PREF_FILE, 0);

        String key = getString(R.string.key_pref_server_address);
        String servAddr = sharedPref.getString(key, "");
        mServerAddrPref.setText(servAddr);
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                C.SHARED_PREF_FILE, 0);
        SharedPreferences.Editor editor = sharedPref.edit();

        String key = getString(R.string.key_pref_server_address);
        String servAddr = mServerAddrPref.getText();
        if (!servAddr.endsWith("/"))
            servAddr += "/";

        editor.putString(key, servAddr);
        editor.commit();

        AbstractDAO.LOCAL_SERVER_URL = servAddr;
    }
}
