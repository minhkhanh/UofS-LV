package client.menu.activity;

import client.menu.R;
import client.menu.adapter.MonAnDtoAdapter;
import client.menu.dto.ChiTietDanhMucDaNgonNguDTO;
import client.menu.dto.MonAnDTO;
import client.menu.fragment.AreaListFragment;
import client.menu.fragment.CategoryListFragment;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu_layout);
		
		FragmentManager fm = getFragmentManager();
		CategoryListFragment f = (CategoryListFragment) fm
				.findFragmentByTag("CategoryListFragment");
		if (f == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.CategoryListPlaceHolder, new CategoryListFragment(),
					"CategoryListFragment");
			ft.commit();
		}
	}
}
