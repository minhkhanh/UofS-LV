package client.menu.activity;

import client.menu.R;
import client.menu.dto.ChiTietDanhMucDaNgonNguDTO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu_layout);
		
		ListView catList = (ListView) findViewById(R.id.CategoryList);
		loadCategoryList(catList, prepCatListData(3));
	}
	
	private ChiTietDanhMucDaNgonNguDTO[] prepCatListData(int count) {
		ChiTietDanhMucDaNgonNguDTO[] data = new ChiTietDanhMucDaNgonNguDTO[count];
		for (int i = 0; i < count; ++i) {
			data[i] = new ChiTietDanhMucDaNgonNguDTO();
			data[i].setMaDanhMuc(i);
			data[i].setTenDanhMuc("Danh mục " + i);
		}
		
		return data;
	}
	
	private void loadCategoryList(ListView catList, ChiTietDanhMucDaNgonNguDTO[] dataAray) {
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.two_line_list_item, dataAray);
		catList.setAdapter(adapter);
		catList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				ChiTietDanhMucDaNgonNguDTO clickedItem = (ChiTietDanhMucDaNgonNguDTO) (arg0.getItemAtPosition(pos));
				Toast.makeText(MainMenuActivity.this, clickedItem.getTenDanhMuc(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
