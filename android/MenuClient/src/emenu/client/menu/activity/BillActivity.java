package emenu.client.menu.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;

import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetServingOrderItemsTask;
import emenu.client.bus.task.LoadSurchargesTask;
import emenu.client.bus.task.PostBillTask;
import emenu.client.db.dto.OrderDTO;
import emenu.client.db.dto.PhuThuDTO;
import emenu.client.db.dto.VoucherDTO;
import emenu.client.menu.R;
import emenu.client.menu.adapter.MainBillAdapter;
import emenu.client.menu.adapter.SurchargeAdapter;
import emenu.client.menu.adapter.VoucherAdapter;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceSession;
import emenu.client.menu.fragment.CustomBillSplitDlgFragment;
import emenu.client.menu.fragment.EqualBillSplitDlgFragment;
import emenu.client.menu.fragment.VoucherSearchDlgFragment;
import emenu.client.menu.fragment.VoucherSearchDlgFragment.OnVoucherUsedListener;
import emenu.client.menu.view.BillHeaderView;
import emenu.client.util.U;

public class BillActivity extends Activity implements OnVoucherUsedListener,
        OnItemClickListener {

    private GetServingOrderItemsTask mGetServingOrderItemsTask;

    private TextView mBillTotalText;
    private MainBillAdapter mBillAdapter;
    private VoucherAdapter mVoucherAdapter;
    private SurchargeAdapter mSurchargeAdapter;
    private PostBillTask mPostBillTask;
    private MergeAdapter mMergeAdapter;

    private OnPostExecuteListener<Integer, Void, List<ContentValues>> mOnPostGetServingOrderItems = new OnPostExecuteListener<Integer, Void, List<ContentValues>>() {
        @Override
        public void onPostExecute(
                CustomAsyncTask<Integer, Void, List<ContentValues>> task,
                List<ContentValues> result) {
            mBillAdapter.clear();
            mBillAdapter.addAll(result);
            mBillAdapter.notifyDataSetChanged();

            updateBillTotalText();

            Bundle b = task.getExtras();
            ContentValues c = (ContentValues) b.getParcelable(OrderDTO.TABLE_NAME);
            if (c != null)
                new LoadSurchargesTask().setOnPostExecuteListener(mOnPostLoadSurcharges)
                        .execute(c.getAsInteger(OrderDTO.CL_MA_BAN));
        }
    };

    private OnPostExecuteListener<Integer, Void, List<PhuThuDTO>> mOnPostLoadSurcharges = new OnPostExecuteListener<Integer, Void, List<PhuThuDTO>>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Integer, Void, List<PhuThuDTO>> task,
                List<PhuThuDTO> result) {
            mSurchargeAdapter.clear();
            mSurchargeAdapter.setBillTotal(mBillAdapter.calcBillTotal());
            mSurchargeAdapter.addAll(result);
            mSurchargeAdapter.notifyDataSetChanged();

            updateBillTotalText();
        }
    };

    private OnPostExecuteListener<Integer, Void, String> mOnPostBill = new OnPostExecuteListener<Integer, Void, String>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Integer, Void, String> task,
                String result) {
            if (result != null && result.compareTo("") == 0) {
                U.toastText(BillActivity.this,
                        BillActivity.this.getString(R.string.message_bill_sent));
                SessionManager.getInstance().finishCurrentSession();

                finish();
                Intent intent = new Intent(BillActivity.this, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                if (result != null)
                    U.showErrorDialog(BillActivity.this, result);
                else
                    U.showErrorDialog(BillActivity.this,
                            R.string.message_connect_server_failed);
            }
        }
    };

    private void updateBillTotalText() {
        int bill = mBillAdapter.calcBillTotal();
        int voucher = mVoucherAdapter.calcVoucherTotal();
        int surcharge = mSurchargeAdapter.calcSurchargeTotal();
        int total = bill - voucher + surcharge;

        mBillTotalText.setText("" + total);
    }

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
                CustomBillSplitDlgFragment dlgCustom = new CustomBillSplitDlgFragment(
                        mBillAdapter.getData());
                U.showDlgFragment(this, dlgCustom, true);
                break;
            case R.id.miEqualSplit:
                EqualBillSplitDlgFragment dlgEqual = new EqualBillSplitDlgFragment(
                        Integer.valueOf(mBillTotalText.getText().toString()));
                U.showDlgFragment(this, dlgEqual, true);
                break;
            case R.id.miConfirmBill:
                U.showConfirmDialog(this, R.string.message_confirm_send_bill,
                        new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                U.cancelAsyncTask(mPostBillTask);

                                Integer orderId = SessionManager.getInstance()
                                        .loadCurrentSession().getOrderId();
                                mPostBillTask = new PostBillTask(mVoucherAdapter
                                        .getAllVoucherCodes());
                                mPostBillTask.setWaitingDialog(U
                                        .createWaitingDialog(BillActivity.this));
                                mPostBillTask.setOnPostExecuteListener(mOnPostBill)
                                        .execute(orderId);
                            }
                        });
                break;
            case R.id.miAddVoucher:
                VoucherSearchDlgFragment dlg = new VoucherSearchDlgFragment(
                        mBillAdapter.calcBillTotal());
                U.showDlgFragment(this, dlg, true);
                break;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bill);

        mMergeAdapter = new MergeAdapter();

        mBillAdapter = new MainBillAdapter(this, new ArrayList<ContentValues>());
        mVoucherAdapter = new VoucherAdapter(this, new ArrayList<ContentValues>());
        mSurchargeAdapter = new SurchargeAdapter(this, new ArrayList<PhuThuDTO>(), 0);

        BillHeaderView header = new BillHeaderView(this);
        header.setText(R.string.text_order);
        mMergeAdapter.addView(header);
        mMergeAdapter.addAdapter(mBillAdapter);

        header = new BillHeaderView(this);
        header.setText(R.string.text_surcharge);
        mMergeAdapter.addView(header);
        mMergeAdapter.addAdapter(mSurchargeAdapter);

        header = new BillHeaderView(this);
        header.setText(R.string.text_voucher_list);
        mMergeAdapter.addView(header);
        mMergeAdapter.addAdapter(mVoucherAdapter);

        ListView listBill = (ListView) findViewById(R.id.listBill);
        listBill.setAdapter(mMergeAdapter);
        listBill.setOnItemClickListener(this);

        mBillTotalText = (TextView) findViewById(R.id.textBillTotal);
    }

    @Override
    protected void onPause() {
        super.onPause();

        U.cancelAsyncTask(mGetServingOrderItemsTask);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ServiceSession session = SessionManager.getInstance().loadCurrentSession();

        mGetServingOrderItemsTask = new GetServingOrderItemsTask(
                GetServingOrderItemsTask.OrderFlag.OrderedOnly);
        mGetServingOrderItemsTask.setOnPostExecuteListener(mOnPostGetServingOrderItems)
                .execute(session.getOrderId());
    }

    @Override
    public void onVoucherUsed(ContentValues values) {
        mVoucherAdapter.add(values);
        mVoucherAdapter.notifyDataSetChanged();

        updateBillTotalText();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Object item = arg0.getItemAtPosition(arg2);
        if (item instanceof ContentValues) {
            ContentValues values = (ContentValues) item;
            if (values.getAsString(VoucherDTO.CL_EX_VOUCHER_CODE) != null) {
                mVoucherAdapter.remove(values);
                mVoucherAdapter.notifyDataSetChanged();
            }
        }
    }
}
