package emenu.client.menu.fragment;

import emenu.client.dao.AbstractDAO;
import emenu.client.menu.R;
import emenu.client.util.C;
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
        String servAddr = sharedPref.getString(key, AbstractDAO.SERVER_URL_SLASH);
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
        if (servAddr != null) {
            if (!servAddr.endsWith("/"))
                servAddr += "/";

            editor.putString(key, servAddr);
            editor.commit();

            AbstractDAO.SERVER_URL_SLASH = servAddr;
        }
    }
}