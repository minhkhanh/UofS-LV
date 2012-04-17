package tablet.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TabletDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SampleDTO[] dtoArr = new SampleDTO[10];
        for (int i = 0; i < 10; ++i) {
        	dtoArr[i] = new SampleDTO();
        	dtoArr[i].setId(i);
        	dtoArr[i].setName("item " + i);
        }
        
        loadSpinner(dtoArr);
        
    }
    
    private void loadSpinner(SampleDTO[] dtoArr) {
    	Spinner spinner = (Spinner) findViewById(R.id.spinner1);
    	ArrayList<SampleDTO> spinnerArr = new ArrayList<SampleDTO>();
    	
    	for (int i = 0; i < dtoArr.length; ++i) {
    		spinnerArr.add(dtoArr[i]);	
    	}
    	ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerArr);
    	
    	spinner.setAdapter(adapter);
    	spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
//				Toast.makeText(TabletDemoActivity.this, ((SampleDTO)arg0.getSelectedItem()).getName(),
//						Toast.LENGTH_SHORT).show();
				
				SampleDTO dto = (SampleDTO)arg0.getSelectedItem();
				GridView grid = (GridView) findViewById(R.id.gridView1);
				populateItems(dto.getId(), grid);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				Toast.makeText(TabletDemoActivity.this, "nothing selected",
						Toast.LENGTH_SHORT).show();
			}
		});
    	/*
    	LinearLayout layBtnPan = (LinearLayout) findViewById(R.id.layBtnPan);
    	for (int i = 0; i < arrNum.length; ++i) {
    		Button btn = new Button(this);
    		btn.setTag(arrNum[i]);
    		btn.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					GridView grid = (GridView) findViewById(R.id.gridView1);
					SampleDTO[] data = new SampleDTO[(Integer) v.getTag()];
					
					for (int i = 0; i < data.length; ++i) {
						data[i] = new SampleDTO();
						data[i].setId(i);
						data[i].setName("Bàn " + i);
					}
					
			        populateItems(data, grid);
				}
			});
    		
    		btn.setText("Button " + arrNum[i]);
    		btn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    		layBtnPan.addView(btn);
    		
    	}*/
    }
    
    private void populateItems(int count, GridView grid) {
    	SampleDTO[] data = new SampleDTO[count];
		
		for (int i = 0; i < data.length; ++i) {
			data[i] = new SampleDTO();
			data[i].setId(i);
			data[i].setName("Bàn " + i);
		}
		
    	grid.setAdapter(new ImgBtnAdapter(data, this));
    	grid.setOnItemClickListener(new OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				Toast.makeText(TabletDemoActivity.this, "" + pos,
						Toast.LENGTH_SHORT).show();				
			}    		
		});
    }
}