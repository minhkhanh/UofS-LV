package client.menu.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import client.menu.R;
import client.menu.ui.fragment.CategoryListFragment;

public class MainMenuActivity extends Activity {
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
