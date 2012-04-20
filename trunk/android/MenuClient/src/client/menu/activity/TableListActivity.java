package client.menu.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import client.menu.R;
import client.menu.fragment.AreaListFragment;

public class TableListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_two_fragment);

		FragmentManager fm = getFragmentManager();
		AreaListFragment f = (AreaListFragment) fm
				.findFragmentByTag("AreaListFragment");
		if (f == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.LeftPaneHolder, new AreaListFragment(),
					"AreaListFragment");
			ft.commit();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
