package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.bus.SessionManager.ServiceSession;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import client.menu.bus.task.GetServingOrderItemsTask;
import client.menu.dao.OrderDAO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.ui.adapter.OrderItemsAdapter;
import client.menu.util.U;

public class OrderActivity extends ListActivity {
    private OrderItemsAdapter mListAdapter;
    private ProgressDialog mWatingDlg;

    private DataSetObserver mOrderObserver = new DataSetObserver() {
        public void onChanged() {
            refreshList();
        };
    };

    private class PostOrderItemsTask extends CustomAsyncTask<Void, Void, Boolean> {
        List<ChiTietOrderDTO> mItems;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ServiceSession session = SessionManager.getInstance().loadCurrentSession();

            mItems = session.bindOrder().getOrderItems();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                return OrderDAO.getInstance().postArrayChiTietOrder(mItems);
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            ServiceSession session = SessionManager.getInstance().loadCurrentSession();

            mWatingDlg.cancel();
            if (result) {
                U.toastText(OrderActivity.this, OrderActivity.this.getResources()
                        .getString(R.string.message_order_sent));

                session.getOrder().clear();

                refreshList();
            } else {
                new AlertDialog.Builder(OrderActivity.this)
                        .setMessage(R.string.message_connect_server_failed).create()
                        .show();

                // session.unbindOrder();
            }
        };

    };

    private OnPostExecuteListener<Integer, Void, List<ContentValues>> mOnPostGetServingOrderItems = new OnPostExecuteListener<Integer, Void, List<ContentValues>>() {
        @Override
        public void onPostExecute(
                CustomAsyncTask<Integer, Void, List<ContentValues>> task,
                List<ContentValues> result) {
            mListAdapter.clear();
            mListAdapter.addAll(result);
            mListAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miConfirmOrder:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(
                        this.getResources().getString(R.string.message_confirm_order))
                        .setCancelable(false)
                        .setPositiveButton(
                                this.getResources().getString(R.string.caption_ok),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        mWatingDlg = ProgressDialog.show(
                                                OrderActivity.this, "", "Wating...");
                                        new PostOrderItemsTask().execute();
                                    }
                                })
                        .setNegativeButton(
                                this.getResources().getString(R.string.caption_cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alert = builder.create();
                alert.show();

                break;

            default:
                break;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_order, menu);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServiceSession session = SessionManager.getInstance().loadCurrentSession();
        ServiceOrder order = session.getOrder();
        order.registerObserver(mOrderObserver);

        mListAdapter = new OrderItemsAdapter(this, new ArrayList<ContentValues>());
        setListAdapter(mListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }

    private void refreshList() {
        ServiceSession session = SessionManager.getInstance().loadCurrentSession();
        new GetServingOrderItemsTask(true).setOnPostExecuteListener(
                mOnPostGetServingOrderItems).execute(session.getOrderId());
    }
}
