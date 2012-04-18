package client.menu.activity;

import client.menu.R;
import client.menu.adapter.BanDtoButtonAdapter;
import client.menu.dto.BanDTO;
import client.menu.dto.KhuVucDTO;
import client.menu.fragment.AreaListFragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class TableListActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_list_layout);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.AreaListPlaceHolder, new AreaListFragment());
		ft.commit();
	}
}
