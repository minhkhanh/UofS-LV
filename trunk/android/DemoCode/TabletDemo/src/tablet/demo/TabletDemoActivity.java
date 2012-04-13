package tablet.demo;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

public class TabletDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
    }
    
    private void populateButtons(int[] arrNum) {
    	LinearLayout layBtnPan = (LinearLayout) findViewById(R.id.layBtnPan);
    }
    
    public void onClickLoadingGrid(View v) {
    	int count = 0;
    	SampleDTO[] data = new SampleDTO[count];    	
    	GridView grid = (GridView) findViewById(R.id.gridView1);
    	
    	if (v.getId() == R.id.button1) {
    		count = 30;
    	} else if (v.getId() == R.id.button2) {
    		count = 10;
    	}
    	
    	data = new SampleDTO[count];
		for (int i = 0; i < count; ++i) {
			data[i] = new SampleDTO();
			data[i].setId(i);
			data[i].setName("number " + i);
		}
		
        populateItems(data, grid);
    }
    
    private void populateItems(SampleDTO[] data, GridView grid) {
    	//Log.d("mylog", "populateButton " + count);
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