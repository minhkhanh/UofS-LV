package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import client.menu.R;
import client.menu.bus.LoadBillItemsTask;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.ui.adapter.MiniBillAdapter;
import client.menu.util.U;

public class CustomBillSplitActivity extends Activity {
    private List<ChiTietOrderDTO> mChiTietOrderList;
    private List<ContentValues> mContentListMain = new ArrayList<ContentValues>();
    private List<ContentValues> mContentListSub = new ArrayList<ContentValues>();

    private MiniBillAdapter mMainAdapter;
    private MiniBillAdapter mSubAdapter;
    private CustomLoadBillItemsTask mLoadBillItemsTask;
    private TextView mMainTotal;
    private TextView mSubTotal;

    private class CustomLoadBillItemsTask extends LoadBillItemsTask {

        public CustomLoadBillItemsTask(Activity host, List<ChiTietOrderDTO> orderItems) {
            super(host, orderItems);
        }

        @Override
        protected void onPostExecute(List<ContentValues> result) {
            super.onPostExecute(result);

            mContentListMain.clear();
            mContentListMain.addAll(result);
            mMainAdapter.notifyDataSetChanged();

            Integer total = mMainAdapter.getBillTotal();
            mMainTotal.setText(total.toString());
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

    private void swapSingle(int pos, List<ContentValues> sender,
            List<ContentValues> receiver) {
        ContentValues values = sender.get(pos);
        Integer soLuongMain = values.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
        soLuongMain -= 1;

        if (soLuongMain == 0) {
            sender.remove(pos);
        } else if (soLuongMain > 0) {
            values.put(ChiTietOrderDTO.CL_SO_LUONG, soLuongMain);
        }

        for (ContentValues c : receiver) {
            if (c.getAsInteger(ChiTietOrderDTO.CL_MA_MON_AN) == values
                    .getAsInteger(ChiTietOrderDTO.CL_MA_MON_AN)
                    && c.getAsInteger(ChiTietOrderDTO.CL_MA_DON_VI_TINH) == values
                            .getAsInteger(ChiTietOrderDTO.CL_MA_DON_VI_TINH)) {
                Integer soLuongSub = c.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG) + 1;
                c.put(ChiTietOrderDTO.CL_SO_LUONG, soLuongSub);

                return;
            }
        }

        ContentValues c = new ContentValues();
        c.putAll(values);
        c.put(ChiTietOrderDTO.CL_SO_LUONG, 1);
        receiver.add(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_bill_split);

        mSubAdapter = new MiniBillAdapter(this, mContentListSub);
        View paneSub = findViewById(R.id.paneSub);
        ListView listSub = (ListView) paneSub.findViewById(R.id.listBill);
        listSub.setAdapter(mSubAdapter);
        listSub.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                swapSingle(arg2, mContentListSub, mContentListMain);
                mMainAdapter.notifyDataSetChanged();
                mSubAdapter.notifyDataSetChanged();
                Integer total = mMainAdapter.getBillTotal();
                mMainTotal.setText(total.toString());
                
                total = mSubAdapter.getBillTotal();
                mSubTotal.setText(total.toString());
            }
        });
        mSubTotal = (TextView) paneSub.findViewById(R.id.textBillTotal);

        mMainAdapter = new MiniBillAdapter(this, mContentListMain);
        View paneMain = findViewById(R.id.paneMain);
        ListView listMain = (ListView) paneMain.findViewById(R.id.listBill);
        listMain.setAdapter(mMainAdapter);
        listMain.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                swapSingle(arg2, mContentListMain, mContentListSub);
                mMainAdapter.notifyDataSetChanged();
                mSubAdapter.notifyDataSetChanged();
                Integer total = mMainAdapter.getBillTotal();
                mMainTotal.setText(total.toString());
                
                total = mSubAdapter.getBillTotal();
                mSubTotal.setText(total.toString());
            }
        });
        mMainTotal = (TextView) paneMain.findViewById(R.id.textBillTotal);        
    }
}
