package client.menu.ui.activity;

import client.menu.R;
import client.menu.ui.fragment.AreaListFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MergeTableActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_two_panes);
        
        View v = findViewById(android.R.id.content);
        v.setBackgroundResource(R.drawable.romantic_mood_wallpaper_070);
        
        FragmentManager fm = getFragmentManager();
        AreaListFragment f = (AreaListFragment) fm.findFragmentByTag("AreaListFragment");
        if (f == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.LeftPaneHolder, new AreaListFragment(), "AreaListFragment");
            ft.commit();
        }
    }
}
