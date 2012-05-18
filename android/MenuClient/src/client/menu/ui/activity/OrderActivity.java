package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.app.MyAppLocale;
import client.menu.bus.LoadDishUnitsAsyncTask;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.db.contract.ChiTietOrderContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.ui.adapter.OrderItemsAdapter;
import client.menu.util.U;

public class OrderActivity extends ListActivity {
    private List<Map<String, Object>> mListData = new ArrayList<Map<String, Object>>();
    private SimpleAdapter mListAdapter;

    private List<LoadOrderItemAsyncTask> mLoadOrderItemTasks = new ArrayList<OrderActivity.LoadOrderItemAsyncTask>();
    private List<LoadDishUnitsAsyncTask> mLoadDishUnitsTasks = new ArrayList<LoadDishUnitsAsyncTask>();

    private OnHierarchyChangeListener mListHierarchyChangeListener = new OnHierarchyChangeListener() {

        @Override
        public void onChildViewRemoved(View parent, View child) {
        }

        @Override
        public void onChildViewAdded(View parent, View child) {
            Spinner spinner = (Spinner) ((ViewGroup) child)
                    .findViewById(R.id.spinDishPrices);

            int index = getListView().getPositionForView(child);
            Integer maMonAn = (Integer) mListData.get(index).get(
                    ChiTietOrderContract.CL_MA_MON_AN);
            Integer maDonViTinh = (Integer) mListData.get(index).get(
                    ChiTietOrderContract.CL_MA_DON_VI_TINH);

            LoadDishUnitsAsyncTask task = new LoadDishUnitsAsyncTask(OrderActivity.this,
                    spinner, maMonAn, maDonViTinh);
            mLoadDishUnitsTasks.add(task);

            task.execute();
        }
    };

    private class LoadOrderItemAsyncTask extends
            AsyncTask<Void, Integer, Map<String, Object>> {
        private ChiTietOrderDTO mChiTietOrder;

        public LoadOrderItemAsyncTask(ChiTietOrderDTO chiTietOrder) {
            mChiTietOrder = chiTietOrder;
        }

        @Override
        protected void onPostExecute(Map<String, Object> result) {
            super.onPostExecute(result);

            mListData.add(result);
            mListAdapter.notifyDataSetChanged();
//            getListView().add
        }

        @Override
        protected Map<String, Object> doInBackground(Void... params) {
            String[] projection = null;
            String selection = MonAnContract.CL_MA_MON_AN_QN + "=? and "
                    + MonAnDaNgonNguContract.CL_MA_NGON_NGU + "=?";
            String[] selectionArgs = {
                    mChiTietOrder.getMaMonAn().toString(),
                    MyAppLocale.getCurrentLanguage(OrderActivity.this).getMaNgonNgu()
                            .toString() };

            Cursor cursor = getContentResolver().query(
                    MonAnContract.URI_MONAN_INNER_DANGONNGU, projection, selection,
                    selectionArgs, null);

            cursor.moveToFirst();
            MonAnDaNgonNguDTO monAnDaNgonNgu = MonAnDaNgonNguDTO.extractFrom(cursor);

            Map<String, Object> map = new Hashtable<String, Object>();
            map.put(ChiTietOrderContract.CL_MA_MON_AN, monAnDaNgonNgu.getMaMonAn());
            map.put(MonAnDaNgonNguContract.CL_TEN_MON, monAnDaNgonNgu.getTenMonAn());
            map.put(ChiTietOrderContract.CL_SO_LUONG, mChiTietOrder.getSoLuong());
            map.put(ChiTietOrderContract.CL_GHI_CHU, mChiTietOrder.getGhiChu());
            map.put(ChiTietOrderContract.CL_MA_DON_VI_TINH,
                    mChiTietOrder.getMaDonViTinh());

            return map;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        cancelAsyncTasks();
    }

    private void cancelAsyncTasks() {
        for (int i = 0; i < mLoadDishUnitsTasks.size(); ++i) {
            LoadDishUnitsAsyncTask task = mLoadDishUnitsTasks.get(i);
            if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
                task.cancel(true);
            }
        }

        mLoadDishUnitsTasks.clear();

        for (int i = 0; i < mLoadOrderItemTasks.size(); ++i) {
            LoadOrderItemAsyncTask task = mLoadOrderItemTasks.get(i);
            if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
                task.cancel(true);
            }
        }

        mLoadOrderItemTasks.clear();
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

//        String[] from = { MonAnDaNgonNguContract.CL_TEN_MON,
//                ChiTietOrderContract.CL_SO_LUONG, ChiTietOrderContract.CL_GHI_CHU };
//        int[] to = { R.id.textDishName, R.id.textQuantity, R.id.editDishNote };
//        mListAdapter = new SimpleAdapter(this, mListData, R.layout.item_order, from, to);
//        setListAdapter(mListAdapter);
//
//        getListView().setOnHierarchyChangeListener(mListHierarchyChangeListener);
//
        ServiceOrder order = SessionManager.loadCurrentSession(this).getOrder();
//        for (int i = 0; i < order.getCount(); ++i) {
//            ChiTietOrderDTO chiTietOrder = order.getItem(i);
//            LoadOrderItemAsyncTask task = new LoadOrderItemAsyncTask(chiTietOrder);
//            mLoadOrderItemTasks.add(task);
//
//            task.execute();
//        }
        
        OrderItemsAdapter adapter = new OrderItemsAdapter(this, order.getData());
        setListAdapter(adapter);

        // U.logOwnTag("onCreate");
    }

    public void onChildClick(View v) {
        // if (v.getId() == R.id.btnIncrease) {
        // ViewGroup group = (ViewGroup) v.getParent();
        // EditText editQuantity = (EditText)
        // group.findViewById(R.id.editQuantity);
        // String text = editQuantity.getText().toString();
        // Integer value = Integer.valueOf(text) + 1;
        // if (value > 99) {
        // value = 0;
        // }
        // editQuantity.setText(value.toString());
        // // U.toastText(this, text);
        // } else if (v.getId() == R.id.btnDecrease) {
        // ViewGroup group = (ViewGroup) v.getParent();
        // EditText editQuantity = (EditText)
        // group.findViewById(R.id.editQuantity);
        // String text = editQuantity.getText().toString();
        // Integer value = Integer.valueOf(text) - 1;
        // if (value < 0) {
        // value = 99;
        // }
        // editQuantity.setText(value.toString());
        // } else if (v.getId() == R.id.btnRemove) {
        // ViewGroup group = (ViewGroup) v.getParent();
        // int pos = mListOrder.getPositionForView(group);
        // mListData.remove(pos);
        // mListAdapter.notifyDataSetChanged();
        // }
    }
}
