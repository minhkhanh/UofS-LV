package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import client.menu.R;
import client.menu.bus.LoadBillItemsTask;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.ui.adapter.BillItemsAdapter;
import client.menu.util.U;

public class BillActivity extends Activity {
    List<ContentValues> mContentList = new ArrayList<ContentValues>();
    BillItemsAdapter mBillAdapter;

    CustomLoadBillItemsTask mLoadBillItemsTask;

    private TextView mCustomerNumText;
    private TextView mEachPayText;
    private TextView mBillTotalText;

    OnClickListener mOnChangeCustomerNumber = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Integer number = 1;
            switch (v.getId()) {
                case R.id.btnPlus:
                    number = Integer.valueOf(mCustomerNumText.getText().toString()) + 1;
                    break;

                case R.id.btnMinus:
                    number = Integer.valueOf(mCustomerNumText.getText().toString()) - 1;
                    if (number < 1) {
                        number = 1;
                    }

                    mCustomerNumText.setText(number.toString());
                    break;
            }

            Integer total = Integer.valueOf(mBillTotalText.getText().toString());

            mCustomerNumText.setText(number.toString());

            Float eachPay = total * 1f / number;
            mEachPayText.setText(eachPay.toString());
        }
    };

    private class CustomLoadBillItemsTask extends LoadBillItemsTask {

        public CustomLoadBillItemsTask(Activity host, List<ChiTietOrderDTO> orderItems) {
            super(host, orderItems);
        }

        @Override
        protected void onPostExecute(List<ContentValues> result) {
            super.onPostExecute(result);

            mContentList.clear();
            mContentList.addAll(result);
            mBillAdapter.notifyDataSetChanged();

            Integer total = mBillAdapter.getBillTotal();
            mBillTotalText.setText(total.toString());
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_bill, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miCustomSplit:
                Intent intent = new Intent(this, CustomBillSplitActivity.class);
                startActivity(intent);
                break;
            case R.id.miEqualSplit:
                ViewStub stub1 = (ViewStub) findViewById(R.id.stubEqualSplit);
                if (stub1 != null) {
                    stub1.inflate();

                    mCustomerNumText = (TextView) findViewById(R.id.textCustomerNumber);

                    mEachPayText = (TextView) findViewById(R.id.textEachPay);
                    mEachPayText.setText(mBillTotalText.getText()); // with
                                                                    // number
                                                                    // customer
                                                                    // = 1

                    Button btnMinus = (Button) findViewById(R.id.btnMinus);
                    Button btnPlus = (Button) findViewById(R.id.btnPlus);
                    btnMinus.setOnClickListener(mOnChangeCustomerNumber);
                    btnPlus.setOnClickListener(mOnChangeCustomerNumber);

                }
                break;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bill);
        
        View v = findViewById(android.R.id.content);
        v.setBackgroundResource(R.drawable.p1575_1920x1200);

        ListView listBill = (ListView) findViewById(R.id.listBill);
        mBillAdapter = new BillItemsAdapter(this, mContentList);
        listBill.setAdapter(mBillAdapter);
        
        mBillTotalText = (TextView) findViewById(R.id.textBillTotal);
    }

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
}
