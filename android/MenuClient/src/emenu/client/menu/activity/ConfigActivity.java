package emenu.client.menu.activity;

import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import emenu.client.menu.R;

public class ConfigActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);

        loadHeadersFromResource(R.xml.pref_head, target);
    }
}
