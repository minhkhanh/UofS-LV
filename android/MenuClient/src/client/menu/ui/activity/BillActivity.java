package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.bus.task.LoadBillItemsTask;
import client.menu.dao.BanDAO;
import client.menu.dao.HoaDonDAO;
import client.menu.dao.OrderDAO;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.ChiTietHoaDonDTO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.HoaDonDTO;
import client.menu.ui.adapter.BillItemsAdapter;
import client.menu.ui.fragment.CustomBillSplitDialog;
import client.menu.ui.fragment.EqualBillSplitDialog;
import client.menu.util.U;

public class BillActivity extends Activity {
    List<ContentValues> mContentList = new ArrayList<ContentValues>();
    BillItemsAdapter mBillAdapter;

    CustomLoadBillItemsTask mLoadBillItemsTask;

    private TextView mBillTotalText;
    protected ProgressDialog mWatingDlg;
    
    class PutTableTask extends AsyncTask<BanDTO, Void, Void> {

        boolean mResult;

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (mResult) {
                Intent intent = new Intent(BillActivity.this, TableMapActivity.class);
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(BillActivity.this);
                builder.setMessage(BillActivity.this.getResources().getString(
                        R.string.message_connect_server_failed));

                AlertDialog alert = builder.create();
                alert.show();
            }
        }

        @Override
        protected Void doInBackground(BanDTO... params) {
            mResult = BanDAO.getInstance().putUpdate(params[0]);
            return null;
        }
    };

    private class SendBillTask extends AsyncTask<Void, Void, Boolean> {

        private List<ChiTietOrderDTO> mItems = new ArrayList<ChiTietOrderDTO>();
        private HoaDonDTO mHoaDon;

        public SendBillTask() {
            ServiceOrder order = SessionManager.getInstance().loadCurrentSession()
                    .getOrder();
            mItems.addAll(order.getBindedItems());
            mHoaDon = order.makeHoaDon();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mWatingDlg.cancel();
            if (result) {
                U.toastText(BillActivity.this, BillActivity.this.getResources()
                        .getString(R.string.message_bill_sent));
//                BanDTO ban = SessionManager.getInstance().loadCurrentSession().getMaBanChinh();
//                ban.setActive(true);
//                new PutTableTask().execute(ban);
                SessionManager.getInstance().destroyCurrentSession();
            } else {
                AlertDialog.Builder builder = new Builder(BillActivity.this)
                        .setMessage(getResources().getString(
                                R.string.message_connect_server_failed));
                builder.create().show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int total = OrderDAO.getInstance().queryTongTien(mItems);
            mHoaDon.setTongTien(total);
            mHoaDon = HoaDonDAO.getInstance().postHoaDon(mHoaDon);

            List<ChiTietHoaDonDTO> listChiTietHoaDon = HoaDonDAO.getInstance()
                    .createListChiTietHoaDon(mItems, mHoaDon.getMaHoaDon());
            listChiTietHoaDon = HoaDonDAO.getInstance().postChiTietHoaDonArray(
                    listChiTietHoaDon);

            if (listChiTietHoaDon == null) {
                return false;
            }

            return true;
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
                CustomBillSplitDialog dlgCustom = new CustomBillSplitDialog();
                U.showDlgFragment(this, dlgCustom, "dlg");
                break;
            case R.id.miEqualSplit:
                EqualBillSplitDialog dlgEqual = new EqualBillSplitDialog(
                        Integer.valueOf(mBillTotalText.getText().toString()));
                U.showDlgFragment(this, dlgEqual, "dlg");
                break;
            case R.id.miConfirmBill:
                AlertDialog.Builder builder = new Builder(this)
                        .setMessage(
                                getResources().getString(
                                        R.string.message_confirm_send_bill))
                        .setCancelable(false)
                        .setPositiveButton(
                                getResources().getString(R.string.caption_yes),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mWatingDlg = ProgressDialog.show(
                                                BillActivity.this, "", "Wating...");
                                        new SendBillTask().execute();
                                    }
                                })
                        .setNegativeButton(getResources().getString(R.string.caption_no),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                builder.create().show();
                break;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bill);

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

        mLoadBillItemsTask = new CustomLoadBillItemsTask(this, order.getBindedItems());
        mLoadBillItemsTask.execute();
    }
}
