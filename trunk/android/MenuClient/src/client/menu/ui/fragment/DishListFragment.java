package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.app.MyApplication;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.bus.SessionManager.ServiceSession;
import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.contract.NgonNguContract;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.util.C;
import client.menu.util.U;

public class DishListFragment extends ListFragment {

    private static final int LOADER_ID_DISH_LIST = 0;

    private int mMaDanhMuc;
    private SimpleCursorAdapter mListAdapter;
    List<LoadDishUnitsAsyncTask> mDishUnitsLoadTaskList = new ArrayList<LoadDishUnitsAsyncTask>();

    private boolean mHaveExtendPane;

    @SuppressWarnings("unused")
    private void restoreDishUnitsLoadTaskList(Bundle savedInstanceState) {
        mDishUnitsLoadTaskList = new ArrayList<LoadDishUnitsAsyncTask>();
        ArrayList<Integer> savedIndices = savedInstanceState
                .getIntegerArrayList("IndexArray");
        if (savedIndices != null) {
            for (int i = 0; i < savedIndices.size(); ++i) {
                LoadDishUnitsAsyncTask task = new LoadDishUnitsAsyncTask(
                        savedIndices.get(i));
                task.execute();
                mDishUnitsLoadTaskList.add(task);
            }
        }
    }

    private void cancelDishUnitsLoadTaskList() {
        // ArrayList<Integer> savedIndices = new ArrayList<Integer>();
        for (int i = 0; i < mDishUnitsLoadTaskList.size(); ++i) {
            LoadDishUnitsAsyncTask task = mDishUnitsLoadTaskList.get(i);
            if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
                // savedIndices.add(task.getIndex());
                task.cancel(true);
                // mDishUnitsLoadTaskList.set(i, null);
            }
        }
        mDishUnitsLoadTaskList.clear();
        // outState.putIntegerArrayList("IndexArray", savedIndices);
    }

    private class LoadDishUnitsAsyncTask extends
            AsyncTask<Void, Integer, SimpleCursorAdapter> {

        Integer mIndex;
        Spinner mSpinner;

        public LoadDishUnitsAsyncTask(int index) {
            mIndex = index;

            ViewGroup row = (ViewGroup) getListView().getChildAt(index);
            mSpinner = (Spinner) row.findViewById(R.id.spinDishPrices);
        }

        @Override
        protected void onPostExecute(SimpleCursorAdapter result) {
            mSpinner.setAdapter(result);
            mSpinner.setFocusable(false); // does the magic!
        };

        @Override
        protected SimpleCursorAdapter doInBackground(Void... unused) {
            Cursor adaptCursor = mListAdapter.getCursor();
            if (adaptCursor == null || adaptCursor.moveToPosition(mIndex) == false) {
                return null;
            }

            Integer maMonAn = adaptCursor.getInt(adaptCursor
                    .getColumnIndex(MonAnContract.CL_MA_MON_AN));

            String[] projection = new String[] {
                    DonViTinhMonAnContract.TABLE_NAME + "." + DonViTinhMonAnContract.CL_ID,
                    DonViTinhMonAnContract.CL_MA_MON_AN,
                    DonViTinhMonAnContract.TABLE_NAME + "."
                            + DonViTinhMonAnContract.CL_MA_DON_VI,
                    DonViTinhMonAnContract.CL_DON_GIA,
                    DonViTinhDaNgonNguContract.CL_TEN_DON_VI };
            // String[] projection = null;

            String selection = DonViTinhMonAnContract.CL_MA_MON_AN + "=? and "
                    + DonViTinhDaNgonNguContract.CL_MA_NGON_NGU + "=?";

            String[] selArgs = new String[] {
                    maMonAn.toString(),
                    MyApplication.getSettings(getActivity()).getLocale().getLanguage()
                            .getMaNgonNgu().toString() };

            Cursor cursor = getActivity().getContentResolver().query(
                    DonViTinhMonAnContract.URI_DONVITINHMONAN_INNER_DANGONNGU,
                    projection, selection, selArgs, null);

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
                    R.layout.item_dish_units_spinner, cursor, new String[] {
                            DonViTinhDaNgonNguContract.CL_TEN_DON_VI,
                            DonViTinhMonAnContract.CL_DON_GIA }, new int[] {
                            R.id.textUnitName, R.id.textUnitPrice }, 0);
            adapter.setDropDownViewResource(R.layout.item_dish_units_spinner);

            return adapter;
        }
    };

    private OnHierarchyChangeListener mOnListItemChange = new OnHierarchyChangeListener() {

        @Override
        public void onChildViewRemoved(View parent, View child) {
        }

        @Override
        public void onChildViewAdded(View parent, View child) {
            if (child.getId() == R.id.itemDishList) {
                int index = getListView().getPositionForView(child);
                LoadDishUnitsAsyncTask task = new LoadDishUnitsAsyncTask(index);
                task.execute();
                mDishUnitsLoadTaskList.add(task);
                Log.d(C.TAG, "child view added");
            }
        }
    };

    private LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderCallbacks<Cursor>() {

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            switch (loader.getId()) {
                case LOADER_ID_DISH_LIST:
                    // Cursor c = mListAdapter.getCursor();
                    mListAdapter.swapCursor(null);
                    // Cursor c = ((SimpleCursorAdapter)
                    // getListAdapter()).getCursor();
                    // c = mListAdapter.getCursor();

                    // Log.d(C.TAG, "onLoaderReset : id = " +
                    // LOADER_ID_DISH_LIST);
                    break;
            }
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor loadedCursor) {
            switch (loader.getId()) {
                case LOADER_ID_DISH_LIST:
                    mListAdapter.swapCursor(loadedCursor);
                    break;
            }
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            switch (id) {
                case LOADER_ID_DISH_LIST:
                    // String[] proj = new String[] {
                    // MonAnContract.TABLE_NAME + "." + MonAnContract.CL_ID,
                    // MonAnContract.TABLE_NAME + "." +
                    // MonAnContract.CL_MA_MON_AN,
                    // MonAnContract.CL_DIEM_DANH_GIA,
                    // MonAnContract.CL_SO_LUOT_RATE,
                    // MonAnDaNgonNguContract.CL_TEN_MON,
                    // MonAnDaNgonNguContract.CL_MO_TA_MON };
                    String[] proj = null;

                    String selection = MonAnContract.CL_MA_DANH_MUC + "=? and "
                            + MonAnDaNgonNguContract.CL_MA_NGON_NGU + "=?";

                    Integer sid = MyApplication.getSettings(getActivity()).getLocale()
                            .getLanguage().getMaNgonNgu();

                    CursorLoader loader = new CursorLoader(getActivity(),
                            MonAnContract.URI_MONAN_INNER_DANGONNGU, proj, selection,
                            new String[] { String.valueOf(mMaDanhMuc), sid.toString() },
                            null);

                    return loader;
            }

            return null;
        }
    };

    public int getMaDanhMuc() {
        return mMaDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.mMaDanhMuc = maDanhMuc;
    }

    public static DishListFragment newInstance(int maDanhMuc) {
        DishListFragment f = new DishListFragment();
        f.mMaDanhMuc = maDanhMuc;

        return f;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mMaDanhMuc", mMaDanhMuc);

        cancelDishUnitsLoadTaskList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // restoreDishUnitsLoadTaskList(savedInstanceState);

        if (savedInstanceState != null) {
            mMaDanhMuc = savedInstanceState.getInt("mMaDanhMuc");
        }

        setHasOptionsMenu(true);

        String[] from = new String[] { MonAnDaNgonNguContract.CL_TEN_MON,
                MonAnDaNgonNguContract.CL_MO_TA_MON };
        int[] to = new int[] { R.id.textDishName, R.id.textDishDescription };
        mListAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item_dish_list,
                null, from, to, 0);

        setListAdapter(mListAdapter);
        // setListShown(false);

        getListView().setOnHierarchyChangeListener(mOnListItemChange);

        getLoaderManager().initLoader(LOADER_ID_DISH_LIST, null, mLoaderCallbacks);

        View orderPreview = getActivity().findViewById(R.id.ExtendPaneHolder);
        mHaveExtendPane = orderPreview != null
                && orderPreview.getVisibility() == View.VISIBLE;
    }

    // private DonViTinhMonAnDTO extractDonViTinhMonAnDTO(int pos) {
    // ViewGroup row = (ViewGroup) getListView().getChildAt(pos);
    // Spinner spinner = (Spinner) row.findViewById(R.id.spinDishPrices);
    //
    // SimpleCursorAdapter adapter = (SimpleCursorAdapter) spinner.getAdapter();
    // Cursor cursor = adapter.getCursor();
    // if (cursor != null &&
    // cursor.moveToPosition(spinner.getSelectedItemPosition())) {
    // return DonViTinhMonAnDTO.extractFrom(cursor);
    // }
    //
    // return null;
    // }

    private DonViTinhDaNgonNguDTO extractDonViTinhDaNgonNguDTO(int pos) {
        ViewGroup row = (ViewGroup) getListView().getChildAt(pos);
        Spinner spinner = (Spinner) row.findViewById(R.id.spinDishPrices);

        SimpleCursorAdapter adapter = (SimpleCursorAdapter) spinner.getAdapter();
        Cursor cursor = adapter.getCursor();
        if (cursor != null && cursor.moveToPosition(spinner.getSelectedItemPosition())) {
            return DonViTinhDaNgonNguDTO.extractFrom(cursor);
        }

        return null;
    }

    private MonAnDaNgonNguDTO extractMonAnDaNgonNguDTO(int pos) {
        Cursor cursor = mListAdapter.getCursor();
        if (cursor != null && cursor.moveToPosition(pos)) {
            return MonAnDaNgonNguDTO.extractFrom(cursor);
        }

        return null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        MonAnDaNgonNguDTO monAnDaNgonNgu = extractMonAnDaNgonNguDTO(position);
        DonViTinhDaNgonNguDTO donViTinhDaNgonNgu = extractDonViTinhDaNgonNguDTO(position);

        SessionManager sessionManager = MyApplication.getSessionManager(getActivity());
        ServiceOrder order = sessionManager.loadCurrentSession().getOrder();

        order.addItem(monAnDaNgonNgu.getMaMonAn(), donViTinhDaNgonNgu.getMaDonViTinh());

        if (mHaveExtendPane) {
            OrderPreviewFragment orderPreview = (OrderPreviewFragment) getFragmentManager()
                    .findFragmentById(R.id.ExtendPaneHolder);

            if (orderPreview == null) {
                orderPreview = new OrderPreviewFragment();
                orderPreview.addItemData(monAnDaNgonNgu, donViTinhDaNgonNgu);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.ExtendPaneHolder, orderPreview);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            } else {
                orderPreview.updateList(monAnDaNgonNgu, donViTinhDaNgonNgu);
            }
        }
    }
}
