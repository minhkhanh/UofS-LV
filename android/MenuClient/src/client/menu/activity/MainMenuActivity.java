package client.menu.activity;

import client.menu.R;
import client.menu.adapter.MonAnDtoAdapter;
import client.menu.dto.ChiTietDanhMucDaNgonNguDTO;
import client.menu.dto.MonAnDTO;
import android.app.Activity;
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
		
		ListView catList = (ListView) findViewById(R.id.CategoryList);
		loadCategoryList(catList, prepCatListData(3));
	}
	
	private ChiTietDanhMucDaNgonNguDTO[] prepCatListData(int count) {
		ChiTietDanhMucDaNgonNguDTO[] data = new ChiTietDanhMucDaNgonNguDTO[count];
		for (int i = 0; i < count; ++i) {
			data[i] = new ChiTietDanhMucDaNgonNguDTO();
			data[i].setMaDanhMuc(i);
			data[i].setTenDanhMuc("Danh mục " + i);
			//data[i].setMoTaDanhMuc("Mô tả danh mục " + i);
		}
		
		return data;
	}
	
	private MonAnDTO[] prepDishGridData(ChiTietDanhMucDaNgonNguDTO danhMuc) {
		MonAnDTO[] data = new MonAnDTO[danhMuc.getMaDanhMuc()];
		for (int i = 0; i < data.length; ++i) {
			data[i].setDiemDanhGia(i);
		}
			
		return data;
	}
	
	private void loadCategoryList(ListView catList, ChiTietDanhMucDaNgonNguDTO[] dataAray) {
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataAray);
		catList.setAdapter(adapter);
		catList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				ChiTietDanhMucDaNgonNguDTO clickedItem = (ChiTietDanhMucDaNgonNguDTO) (arg0.getItemAtPosition(pos));

				GridView dishGrid = (GridView) findViewById(R.id.DishGrid);
				loadDishGrid(dishGrid, prepDishGridData(clickedItem), 1);
			}
		});
	}
	
	private void loadDishGrid(GridView dishGrid, MonAnDTO[] monAnArray, int colCount) {
		MonAnDtoAdapter adapter = new MonAnDtoAdapter(this, R.layout.mon_an_row_layout, monAnArray);
		
		dishGrid.setNumColumns(colCount);
		dishGrid.setAdapter(adapter);
		dishGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(MainMenuActivity.this, "" + arg2,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
