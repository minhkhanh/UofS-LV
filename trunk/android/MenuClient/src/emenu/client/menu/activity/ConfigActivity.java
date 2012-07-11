package emenu.client.menu.activity;

import java.util.List;

import emenu.client.menu.R;

import android.preference.PreferenceActivity;

public class ConfigActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        
        loadHeadersFromResource(R.xml.pref_head, target);
    }
}
