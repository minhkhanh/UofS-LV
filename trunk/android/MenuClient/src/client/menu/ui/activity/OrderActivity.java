package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import client.menu.R;
import client.menu.app.MyAppLocale;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.ui.adapter.OrderItemsAdapter;
import client.menu.util.U;

public class OrderActivity extends ListActivity {

    List<ChiTietOrderDTO> mChiTietOrderList;
    private OrderItemsAdapter mListAdapter;
    private int mItemTotal;
    private int mItemCount = 0;

    private List<LoadOrderItemAsyncTask> mLoadOrderItemTasks = new ArrayList<OrderActivity.LoadOrderItemAsyncTask>();

    private List<MonAnDaNgonNguDTO> mMonAnDaNgonNguList = new ArrayList<MonAnDaNgonNguDTO>();
    private List<DonViTinhDaNgonNguDTO> mDonViTinhDaNgonNguList = new ArrayList<DonViTinhDaNgonNguDTO>();
    private List<DonViTinhMonAnDTO> mDonViTinhMonAnList = new ArrayList<DonViTinhMonAnDTO>();

    private class LoadOrderItemAsyncTask extends AsyncTask<Void, Integer, Void> {
        private ChiTietOrderDTO mChiTietOrder;
        private MonAnDaNgonNguDTO mMonAnDaNgonNgu;
        private DonViTinhDaNgonNguDTO mDonViTinhDaNgonNgu;
        private DonViTinhMonAnDTO mDonViTinhMonAn;

        public LoadOrderItemAsyncTask(ChiTietOrderDTO chiTietOrder) {
            mChiTietOrder = chiTietOrder;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            mMonAnDaNgonNguList.add(mMonAnDaNgonNgu);
            mDonViTinhDaNgonNguList.add(mDonViTinhDaNgonNgu);
            mDonViTinhMonAnList.add(mDonViTinhMonAn);

            ++mItemCount;
            if (mItemCount == mItemTotal) {
                mListAdapter = new OrderItemsAdapter(OrderActivity.this,
                        mMonAnDaNgonNguList, mDonViTinhDaNgonNguList,
                        mDonViTinhMonAnList, mChiTietOrderList);
                setListAdapter(mListAdapter);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            String[] projection = null;
            String selection = DonViTinhMonAnContract.CL_MA_MON_AN_QN + "=? and "
                    + MonAnDaNgonNguContract.CL_MA_NGON_NGU_QN + "=? and "
                    + DonViTinhMonAnContract.CL_MA_DON_VI_QN + "=?";

            String[] selectionArgs = {
                    mChiTietOrder.getMaMonAn().toString(),
                    MyAppLocale.getCurrentLanguage(OrderActivity.this).getMaNgonNgu()
                            .toString(), mChiTietOrder.getMaDonViTinh().toString() };

            Cursor cursor = OrderActivity.this.getContentResolver().query(
                    DonViTinhMonAnContract.URI_MONANDANGONNGU_DONVITINHMONAN_DANGONNGU,
                    projection, selection, selectionArgs, null);

            cursor.moveToFirst();
            mMonAnDaNgonNgu = MonAnDaNgonNguDTO.extractFrom(cursor);

//            selection = DonViTinhMonAnContract.CL_MA_MON_AN + "=? and "
//                    + DonViTinhDaNgonNguContract.CL_MA_NGON_NGU + "=? and "
//                    + DonViTinhMonAnContract.TABLE_NAME + "."
//                    + DonViTinhMonAnContract.CL_MA_DON_VI + "=?";
//
//            selectionArgs = new String[] {
//                    mMonAnDaNgonNgu.getMaMonAn().toString(),
//                    MyAppLocale.getCurrentLanguage(OrderActivity.this).getMaNgonNgu()
//                            .toString(), mChiTietOrder.getMaDonViTinh().toString() };
//
//            Cursor donViTinhCursor = OrderActivity.this.getContentResolver().query(
//                    DonViTinhMonAnContract.URI_DONVITINHMONAN_INNER_DANGONNGU,
//                    projection, selection, selectionArgs, null);

            cursor.moveToFirst();
            mDonViTinhDaNgonNgu = DonViTinhDaNgonNguDTO.extractFrom(cursor);
            cursor.moveToFirst();
            mDonViTinhMonAn = DonViTinhMonAnDTO.extractFrom(cursor);

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
        ServiceOrder order = SessionManager.loadCurrentSession(this).getOrder();
        mChiTietOrderList = order.getContent();

        mItemTotal = mChiTietOrderList.size();
        for (int i = 0; i < mItemTotal; ++i) {
            ChiTietOrderDTO c = mChiTietOrderList.get(i);
            LoadOrderItemAsyncTask task = new LoadOrderItemAsyncTask(c);
            mLoadOrderItemTasks.add(task);

            task.execute();
        }
    }
}
