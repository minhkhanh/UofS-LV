package emenu.client.menu.activity;

import java.util.List;

import emenu.client.dao.NgonNguDAO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.R;
import emenu.client.menu.app.MenuApplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

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
