package emenu.client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import client.menu.R;
import emenu.client.db.dto.HoaDonDTO;
import emenu.client.menu.bus.SessionManager;
import emenu.client.menu.bus.SessionManager.ServiceSession;
import emenu.client.menu.bus.task.CustomAsyncTask;
import emenu.client.menu.bus.task.GetServingOrderItemsTask;
import emenu.client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.menu.dao.HoaDonDAO;
import emenu.client.menu.ui.adapter.BillAdapter;
import emenu.client.menu.ui.adapter.VoucherItemsAdapter;
import emenu.client.menu.ui.fragment.CustomBillSplitDialog;
import emenu.client.menu.ui.fragment.EqualBillSplitDialog;
import emenu.client.menu.ui.fragment.VoucherSearchDlgFragment;
import emenu.client.menu.ui.fragment.VoucherSearchDlgFragment.OnUseVoucherListener;
import emenu.client.menu.util.U;

public class BillActivity extends Activity implements OnUseVoucherListener {
    private BillAdapter mBillAdapter;
    private GetServingOrderItemsTask mGetServingOrderItemsTask;

    private TextView mBillTotalText;
    private ProgressDialog mWatingDlg;

    private VoucherItemsAdapter mVoucherAdapter;

    private OnPostExecuteListener<Integer, Void, List<ContentValues>> mOnPostGettingServingOrderItems = new OnPostExecuteListener<Integer, Void, List<ContentValues>>() {
        @Override
        public void onPostExecute(
                CustomAsyncTask<Integer, Void, List<ContentValues>> task,
                List<ContentValues> result) {
            mBillAdapter.clear();
            mBillAdapter.addAll(result);
            mBillAdapter.notifyDataSetChanged();

            updateBillTotalText();
        }
    };

    private class PostBillTask extends CustomAsyncTask<Integer, Void, HoaDonDTO> {

        List<String> mVoucherCodes;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mVoucherCodes = mVoucherAdapter.getAllVoucherCodes();
        }

        @Override
        protected void onPostExecute(HoaDonDTO result) {
            super.onPostExecute(result);
            mWatingDlg.cancel();

            if (result != null) {
                U.toastText(BillActivity.this, BillActivity.this.getResources()
                        .getString(R.string.message_bill_sent));
                SessionManager.getInstance().finishCurrentSession();

                Intent intent = new Intent(BillActivity.this, MainMenuActivity.class);
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new Builder(BillActivity.this)
                        .setMessage(getResources().getString(
                                R.string.message_connect_server_failed));
                builder.create().show();
            }
        }

        @Override
        protected HoaDonDTO doInBackground(Integer... params) {
            return HoaDonDAO.getInstance().postLapHoaDon(params[0], mVoucherCodes);
        }
    };

    private void updateBillTotalText() {
        Float total = mBillAdapter.getBillTotal() - mVoucherAdapter.getTotalValue();
        mBillTotalText.setText(total.toString());
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
                CustomBillSplitDialog dlgCustom = new CustomBillSplitDialog();
                U.showDlgFragment(this, dlgCustom, "dlg");
                break;
            case R.id.miEqualSplit:
                EqualBillSplitDialog dlgEqual = new EqualBillSplitDialog(
                        Integer.valueOf(mBillTotalText.getText().toString()));
                U.showDlgFragment(this, dlgEqual, "dlg");
                break;
            case R.id.miConfirmBill:
                U.showConfirmDialog(this, R.string.message_confirm_send_bill,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mWatingDlg = ProgressDialog.show(BillActivity.this, "",
                                        "Wating...");
                                Integer orderId = SessionManager.getInstance()
                                        .loadCurrentSession().getOrderId();
                                new PostBillTask().execute(orderId);
                            }
                        });
                break;
            case R.id.miAddVoucher:
                VoucherSearchDlgFragment dlg = new VoucherSearchDlgFragment(
                        mBillAdapter.getBillTotal());
                U.showDlgFragment(this, dlg, "dlg");
                break;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bill);

        ListView listBill = (ListView) findViewById(R.id.listBill);
        mBillAdapter = new BillAdapter(this, new ArrayList<ContentValues>());
        listBill.setAdapter(mBillAdapter);

        ListView listVoucher = (ListView) findViewById(R.id.listVoucher);
        mVoucherAdapter = new VoucherItemsAdapter(this, new ArrayList<ContentValues>());
        listVoucher.setAdapter(mVoucherAdapter);

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
                GetServingOrderItemsTask.FLAG_ORDERED_ONLY);
        mGetServingOrderItemsTask.setOnPostExecuteListener(
                mOnPostGettingServingOrderItems).execute(session.getOrderId());
    }

    @Override
    public void onUseVoucher(ContentValues values) {
        mVoucherAdapter.add(values);
        mVoucherAdapter.notifyDataSetChanged();

        updateBillTotalText();
    }
}
