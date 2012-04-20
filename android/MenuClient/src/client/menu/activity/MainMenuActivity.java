package client.menu.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import client.menu.R;
import client.menu.fragment.CategoryListFragment;

public class MainMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_fragment_layout);

		FragmentManager fm = getFragmentManager();
		CategoryListFragment f = (CategoryListFragment) fm
				.findFragmentByTag("CategoryListFragment");
		if (f == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.LeftPaneHolder,
					new CategoryListFragment(), "CategoryListFragment");
			ft.commit();
		}
	}
}
