package tablet.demo;

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
import android.widget.Button;
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
        
        populateButtons(new int[]{1, 2, 10, 20, 4, 8, 40, 80});
    }
    
    private void populateButtons(int[] arrNum) {
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
    	}
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