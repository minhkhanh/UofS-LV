package client.menu.ui.activity;

import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import client.menu.R;
import client.menu.bus.LoadBillItemsTask;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.ui.view.MiniBillView;
import client.menu.util.U;

public class CustomBillSplitActivity extends Activity {
    private MiniBillView mMainBillView;
    private MiniBillView mSubBillView;
    private List<ChiTietOrderDTO> mChiTietOrderList;
    
    private HorizontalScrollView mScrollView;

    private CustomLoadBillItemsTask mLoadBillItemsTask;

    private class CustomLoadBillItemsTask extends LoadBillItemsTask {

        public CustomLoadBillItemsTask(Activity host, List<ChiTietOrderDTO> orderItems) {
            super(host, orderItems);
        }

        @Override
        protected void onPostExecute(List<ContentValues> result) {
            super.onPostExecute(result);

            mMainBillView.bindItems(result);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        U.cancelAsyncTask(mLoadBillItemsTask);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ServiceOrder order = SessionManager.getInstance().loadCurrentSession().getOrder();
        mLoadBillItemsTask = new CustomLoadBillItemsTask(this, order.getContent());
        mLoadBillItemsTask.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom_bill_split);
        
//        mScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
        final ViewGroup scrollBill = (ViewGroup) findViewById(R.id.scrollBill);
        
        Button btnSplit = (Button) findViewById(R.id.btnSplit);
        btnSplit.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                MiniBillView newBill = mSubBillView.clone();
                scrollBill.addView(newBill);
                
                mSubBillView.bindItems(null);
            }
        });

        mMainBillView = (MiniBillView) findViewById(R.id.billMain);
        ListView listMain = mMainBillView.getBillList();
        listMain.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mMainBillView.swapItem(mSubBillView, arg2);
            }
        });

        mSubBillView = (MiniBillView) findViewById(R.id.billSub);
        ListView listSub = mSubBillView.getBillList();
        listSub.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mSubBillView.swapItem(mMainBillView, arg2);
            }
        });
    }
}
