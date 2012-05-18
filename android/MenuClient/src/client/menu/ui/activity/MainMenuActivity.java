package client.menu.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import client.menu.R;
import client.menu.ui.fragment.CategoryListFragment;

public class MainMenuActivity extends Activity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_main_menu, menu);
        
        return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mitemViewOrder:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                break;
        }
        
        return super.onMenuItemSelected(featureId, item);
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_three_panes);

		FragmentManager fm = getFragmentManager();
		CategoryListFragment f = (CategoryListFragment) fm
				.findFragmentByTag("CategoryListFragment");
		if (f == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.GroupPaneHolder,
					new CategoryListFragment(), "CategoryListFragment");
			ft.commit();
		}
	}
}
