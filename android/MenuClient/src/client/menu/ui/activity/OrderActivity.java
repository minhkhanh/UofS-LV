package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ContentValues;
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
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.ui.adapter.OrderItemsAdapter;
import client.menu.util.U;

public class OrderActivity extends ListActivity {

    private DataSetObserver mOrderObserver = new DataSetObserver() {
        public void onChanged() {
            excuteLoadData();
        };
    };

    private OrderItemsAdapter mListAdapter;
    List<LoadOrderItemAsyncTask> mLoadOrderItemTasks = new ArrayList<OrderActivity.LoadOrderItemAsyncTask>();

    private List<ChiTietOrderDTO> mChiTietOrderList = new ArrayList<ChiTietOrderDTO>();
    private List<ContentValues> mContentValuesList = new ArrayList<ContentValues>();

    private class LoadOrderItemAsyncTask extends
            AsyncTask<ChiTietOrderDTO, Integer, Void> {

        ChiTietOrderDTO mItem;
        ContentValues mValues;

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            mContentValuesList.add(mValues);
            mChiTietOrderList.add(mItem);
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
                U.toastText(this, "Gửi order: Đang xây dựng");
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

        // mChiTietOrderList = order.getContent();

        mListAdapter = new OrderItemsAdapter(this, mContentValuesList, mChiTietOrderList);
        setListAdapter(mListAdapter);

        // excuteLoadData();
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
        mContentValuesList.clear();
        mChiTietOrderList.clear();
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
