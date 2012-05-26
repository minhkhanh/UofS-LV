package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import client.menu.R;
import client.menu.app.MyAppLocale;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.db.dao.DonViTinhDAO;
import client.menu.db.dao.OrderDAO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.db.dto.OrderDTO;
import client.menu.ui.adapter.OrderItemsAdapter;
import client.menu.util.U;

public class OrderActivity extends ListActivity {

    private DataSetObserver mOrderObserver = new DataSetObserver() {
        public void onChanged() {
            excuteLoadData();
        };
    };

    private class PostOrderTask extends AsyncTask<Void, Void, Boolean> {
        private List<ChiTietOrderDTO> mItemToPost;// = new
                                                  // ArrayList<ChiTietOrderDTO>();
        private OrderDTO mOrder;

        public PostOrderTask() {
            ServiceOrder servOrder = SessionManager.getInstance().loadCurrentSession()
                    .getOrder();
            OrderDTO order = servOrder.makeOrder();
            mItemToPost = servOrder.getUnbindedItems();
            mOrder = order;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mWatingDlg.cancel();
            if (result == true) {
                U.toastText(OrderActivity.this, OrderActivity.this.getResources()
                        .getString(R.string.message_order_sent));

                SessionManager.getInstance().loadCurrentSession().getOrder()
                        .setOrderId(mOrder.getMaOrder());

                excuteLoadData();
            } else {
                AlertDialog.Builder builder = new Builder(OrderActivity.this)
                        .setMessage(getResources().getString(
                                R.string.message_connect_server_failed));
                builder.create().show();
            }
        };

        @Override
        protected Boolean doInBackground(Void... params) {
            if (mOrder.getMaOrder() <= 0) {
                mOrder = OrderDAO.getInstance().postNewOrder(mOrder);
                if (mOrder == null) {
                    return false;
                }
            }

            for (ChiTietOrderDTO c : mItemToPost) {
                c.setMaOrder(mOrder.getMaOrder());
            }

            List<ChiTietOrderDTO> list = OrderDAO.getInstance().postChiTietOrderArray(
                    mItemToPost);
            if (list == null) {
                return false;
            }

            return true;
        }
    };

    private OrderItemsAdapter mListAdapter;
    List<LoadOrderItemAsyncTask> mLoadOrderItemTasks = new ArrayList<OrderActivity.LoadOrderItemAsyncTask>();
    protected ProgressDialog mWatingDlg;

    private class LoadOrderItemAsyncTask extends
            AsyncTask<ChiTietOrderDTO, Integer, Void> {

        ChiTietOrderDTO mItem;
        ContentValues mValues;

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            mListAdapter.add(mValues);
            mListAdapter.addExtra(mItem);
            mListAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(ChiTietOrderDTO... params) {
            mItem = params[0];

            NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(OrderActivity.this);

            Cursor cursor = DonViTinhDAO.getInstance().cursorByDonViTinhMonAn(
                    mItem.getMaMonAn(), mItem.getMaDonViTinh(), ngonNgu.getMaNgonNgu());
            cursor.moveToFirst();

            mValues = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(cursor, mValues);

            cursor.close();

            return null;
        }
    }

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
                                        new PostOrderTask().execute();
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

        ServiceOrder order = SessionManager.getInstance().loadCurrentSession().getOrder();
        order.registerObserver(mOrderObserver);

        mListAdapter = new OrderItemsAdapter(this, new ArrayList<ContentValues>(),
                new ArrayList<ChiTietOrderDTO>());
        setListAdapter(mListAdapter);

    }

    private void cleanTasks() {
        for (int i = 0; i < mLoadOrderItemTasks.size(); ++i) {
            LoadOrderItemAsyncTask task = mLoadOrderItemTasks.get(i);
            if (task.getStatus() != AsyncTask.Status.FINISHED) {
                mLoadOrderItemTasks.get(i).cancel(true);
            }
        }

        mLoadOrderItemTasks.clear();
    }

    @Override
    protected void onPause() {
        super.onPause();

        cleanTasks();
    }

    @Override
    protected void onResume() {
        super.onResume();

        excuteLoadData();
    }

    private void excuteLoadData() {
        mListAdapter.clear();
        mListAdapter.notifyDataSetChanged();

        ServiceOrder order = SessionManager.getInstance().loadCurrentSession().getOrder();
        for (int i = 0; i < order.getCount(); ++i) {
            ChiTietOrderDTO c = order.getItem(i);
            LoadOrderItemAsyncTask task = new LoadOrderItemAsyncTask();
            mLoadOrderItemTasks.add(task);

            task.execute(c);
        }
    }
}
