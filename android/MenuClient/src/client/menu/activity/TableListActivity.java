package client.menu.activity;

import client.menu.R;
import client.menu.adapter.BanDtoButtonAdapter;
import client.menu.dto.BanDTO;
import client.menu.dto.KhuVucDTO;
import android.app.Activity;
import android.app.AlertDialog;
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
		
		ListView areaList = (ListView) findViewById(R.id.AreaList);
		loadAreaList(areaList, prepTmpKhuVucDTO());
		
//		Spinner areaSpinner = (Spinner) findViewById(R.id.AreaSpinner);
//		loadAreaSpinner(areaSpinner, prepTmpKhuVucDTO());
		
//		AlertDialog.Builder alert = new AlertDialog.Builder(this);
//
//		alert.setTitle("Title");
//		alert.setMessage("Message");
//
//		// Set an EditText view to get user input 
//		final EditText input = new EditText(this);
//		alert.setView(input);
//		
//		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//		public void onClick(DialogInterface dialog, int whichButton) {
//		  //String value = input.getText();
//		  // Do something with value!
//		  }
//		});
//
//		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//		  public void onClick(DialogInterface dialog, int whichButton) {
//		    // Canceled.
//		  }
//		});
//
//		alert.show();
	}
	
	private KhuVucDTO[] prepTmpKhuVucDTO() {
		KhuVucDTO[] khuVucArray = new KhuVucDTO[5];
		for (int i = 0; i < khuVucArray.length; ++i) {
			khuVucArray[i] = new KhuVucDTO();
			khuVucArray[i].setMaKhuVuc(i);
			khuVucArray[i].setTenKhuVuc("Khu vực " + i);
		}
		
		return khuVucArray;
	}
	
	private BanDTO[] prepTmpBanDTO(KhuVucDTO khuVuc) {
		BanDTO[] banArray = new BanDTO[khuVuc.getMaKhuVuc()];
		for (int i = 0; i < banArray.length; ++i) {
			banArray[i] = new BanDTO();
			banArray[i].setMaBan(i);
			banArray[i].setTenBan("Bàn " + i);
		}
		
		return banArray;
	}
	
	private void loadAreaList(ListView areaList, KhuVucDTO[] khuVucArray) {
		ArrayAdapter<KhuVucDTO> dataAdapter = new ArrayAdapter<KhuVucDTO>(this, android.R.layout.simple_list_item_1, khuVucArray);
		areaList.setAdapter(dataAdapter);
		areaList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				KhuVucDTO khuVuc = (KhuVucDTO)arg0.getItemAtPosition(pos);
				BanDTO[] banArray = prepTmpBanDTO(khuVuc);
				GridView grid = (GridView) findViewById(R.id.TableGrid);
				loadTableGrid(grid, banArray);
			}
		});
	}
	
//	private void loadAreaSpinner(Spinner areaSpinner, KhuVucDTO[] khuVucArray) {
//		ArrayAdapter<KhuVucDTO> dataAdapter = new ArrayAdapter<KhuVucDTO>(this, android.R.layout.simple_spinner_dropdown_item, khuVucArray);
//		areaSpinner.setAdapter(dataAdapter);
//		areaSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				KhuVucDTO khuVuc = (KhuVucDTO)arg0.getSelectedItem();
//				BanDTO[] banArray = prepTmpBanDTO(khuVuc);
//				GridView grid = (GridView) findViewById(R.id.TableGrid);
//				loadTableGrid(grid, banArray);
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//			}
//		});
//		
//	}
	
	private void loadTableGrid(GridView tableGrid, BanDTO[] banArray) {
		tableGrid.setAdapter(new BanDtoButtonAdapter(banArray, this));
		tableGrid.setOnItemClickListener(new OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				Toast.makeText(TableListActivity.this, "" + pos,
						Toast.LENGTH_SHORT).show();				
			}    		
		});
	}
}
