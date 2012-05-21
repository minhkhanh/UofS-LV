package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.app.MyAppSettings;
import client.menu.bus.LoadDishUnitsAsyncTask;
import client.menu.db.dao.MonAnDAO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.util.U;

public class DishListFragment extends ListFragment {

    // private static final int LOADER_ID_DISH_LIST = 0;

    private int mMaDanhMuc;
    private SimpleCursorAdapter mListAdapter;
    List<LoadDishUnitsAsyncTask> mDishUnitsLoadTaskList = new ArrayList<LoadDishUnitsAsyncTask>();

    private boolean mHaveExtendPane;

    LoadDishListTask mLoadDishListTask;

    class LoadDishListTask extends AsyncTask<Integer, Integer, Cursor> {

        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);

            mListAdapter.changeCursor(result);
            setListShown(true);
        }

        @Override
        protected Cursor doInBackground(Integer... params) {
            NgonNguDTO ngonNgu = MyAppSettings.getCurrentAppLocale(getActivity())
                    .getLanguage();
            return MonAnDAO.getInstance().cursorByMaDanhMuc(params[0],
                    ngonNgu.getMaNgonNgu());
        }

    };

    private void cancelLoadDishUnitsTasks() {
        // ArrayList<Integer> savedIndices = new ArrayList<Integer>();
        for (int i = 0; i < mDishUnitsLoadTaskList.size(); ++i) {
            LoadDishUnitsAsyncTask task = mDishUnitsLoadTaskList.get(i);
            if (task.getStatus() != AsyncTask.Status.FINISHED) {
                // savedIndices.add(task.getIndex());
                task.cancel(true);
                // mDishUnitsLoadTaskList.set(i, null);
            }
        }
        mDishUnitsLoadTaskList.clear();
        // outState.putIntegerArrayList("IndexArray", savedIndices);
    }

    private OnHierarchyChangeListener mOnListItemChange = new OnHierarchyChangeListener() {

        @Override
        public void onChildViewRemoved(View parent, View child) {
        }

        @Override
        public void onChildViewAdded(View parent, View child) {
            Spinner spinner = (Spinner) ((ViewGroup) child)
                    .findViewById(R.id.spinDishPrices);

            int index = getListView().getPositionForView(child);
            Cursor cursor = mListAdapter.getCursor();
            if (cursor.moveToPosition(index)) {
                Integer maMonAn = cursor.getInt(cursor
                        .getColumnIndex(MonAnDTO.CL_MA_MON_AN));

                LoadDishUnitsAsyncTask task = new LoadDishUnitsAsyncTask(
                        DishListFragment.this.getActivity(), spinner, maMonAn, null);
                mDishUnitsLoadTaskList.add(task);

                task.execute();
            }
        }
    };

    // private LoaderCallbacks<Cursor> mLoaderCallbacks = new
    // LoaderCallbacks<Cursor>() {
    //
    // @Override
    // public void onLoaderReset(Loader<Cursor> loader) {
    // switch (loader.getId()) {
    // case LOADER_ID_DISH_LIST:
    // // Cursor c = mListAdapter.getCursor();
    // mListAdapter.swapCursor(null);
    // // Cursor c = ((SimpleCursorAdapter)
    // // getListAdapter()).getCursor();
    // // c = mListAdapter.getCursor();
    //
    // // Log.d(C.TAG, "onLoaderReset : id = " +
    // // LOADER_ID_DISH_LIST);
    // break;
    // }
    // }
    //
    // @Override
    // public void onLoadFinished(Loader<Cursor> loader, Cursor loadedCursor) {
    // switch (loader.getId()) {
    // case LOADER_ID_DISH_LIST:
    // mListAdapter.swapCursor(loadedCursor);
    // break;
    // }
    // }
    //
    // @Override
    // public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    // switch (id) {
    // case LOADER_ID_DISH_LIST:
    // String[] proj = null;
    //
    // String selection = MonAnContract.CL_MA_DANH_MUC + "=? and "
    // + MonAnDaNgonNguContract.CL_MA_NGON_NGU + "=?";
    //
    // Integer sid = MyApplication.getSettings(getActivity()).getLocale()
    // .getLanguage().getMaNgonNgu();
    //
    // CursorLoader loader = new CursorLoader(getActivity(),
    // MonAnContract.URI_MONAN_INNER_DANGONNGU, proj, selection,
    // new String[] { String.valueOf(mMaDanhMuc), sid.toString() },
    // null);
    //
    // return loader;
    // }
    //
    // return null;
    // }
    // };

    @Override
    public void onResume() {
        super.onResume();

        mLoadDishListTask = new LoadDishListTask();
        mLoadDishListTask.execute(mMaDanhMuc);

        setListShown(false);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mLoadDishListTask.getStatus() != AsyncTask.Status.FINISHED) {
            mLoadDishListTask.cancel(true);
        } else {
            mListAdapter.changeCursor(null);
        }

        cancelLoadDishUnitsTasks();
    }

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
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // restoreDishUnitsLoadTaskList(savedInstanceState);

        if (savedInstanceState != null) {
            mMaDanhMuc = savedInstanceState.getInt("mMaDanhMuc");
        }

        setHasOptionsMenu(true);

        String[] from = new String[] { MonAnDaNgonNguDTO.CL_TEN_MON,
                MonAnDaNgonNguDTO.CL_MO_TA_MON };
        int[] to = new int[] { R.id.textDishName, R.id.textDishDescription };
        mListAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item_dish_list,
                null, from, to, 0);

        setListAdapter(mListAdapter);
        // setListShown(false);

        getListView().setOnHierarchyChangeListener(mOnListItemChange);

        // getLoaderManager().initLoader(LOADER_ID_DISH_LIST, null,
        // mLoaderCallbacks);

        View orderPreview = getActivity().findViewById(R.id.ExtendPaneHolder);
        mHaveExtendPane = orderPreview != null
                && orderPreview.getVisibility() == View.VISIBLE;
    }

    private DonViTinhMonAnDTO extractDonViTinhMonAnDTO(int pos) {
        ViewGroup row = (ViewGroup) getListView().getChildAt(pos);
        Spinner spinner = (Spinner) row.findViewById(R.id.spinDishPrices);

        SimpleCursorAdapter adapter = (SimpleCursorAdapter) spinner.getAdapter();
        Cursor cursor = adapter.getCursor();
        if (cursor != null && cursor.moveToPosition(spinner.getSelectedItemPosition())) {
            return DonViTinhMonAnDTO.extractFrom(cursor);
        }

        return null;
    }

    private SimpleCursorAdapter extractUnitsAdapter(int pos) {
        ViewGroup row = (ViewGroup) getListView().getChildAt(pos);
        Spinner spinner = (Spinner) row.findViewById(R.id.spinDishPrices);

        return (SimpleCursorAdapter) spinner.getAdapter();
    }

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

    private MonAnDTO extractMonAnDTO(int pos) {
        Cursor cursor = mListAdapter.getCursor();
        if (cursor != null && cursor.moveToPosition(pos)) {
            return MonAnDTO.extractFrom(cursor);
        }

        return null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        MonAnDTO monAn = extractMonAnDTO(position);
        MonAnDaNgonNguDTO monAnDaNgonNgu = extractMonAnDaNgonNguDTO(position);
        SimpleCursorAdapter adapter = extractUnitsAdapter(position);
        DonViTinhMonAnDTO donViTinhMonAn = extractDonViTinhMonAnDTO(position);

        DishDetailDialogFragment newFragment = new DishDetailDialogFragment(monAn,
                monAnDaNgonNgu, donViTinhMonAn.getMaDonViTinh(), adapter);
        U.showDlgFragment(this, newFragment, "dialog");

        // if (mHaveExtendPane) {
        // OrderPreviewFragment orderPreview = (OrderPreviewFragment)
        // getFragmentManager()
        // .findFragmentById(R.id.ExtendPaneHolder);
        //
        // if (orderPreview == null) {
        // orderPreview = new OrderPreviewFragment();
        // orderPreview.addItemData(monAnDaNgonNgu, donViTinhDaNgonNgu);
        //
        // FragmentTransaction ft = getFragmentManager().beginTransaction();
        // ft.replace(R.id.ExtendPaneHolder, orderPreview);
        // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // ft.commit();
        // } else {
        // orderPreview.updateList(monAnDaNgonNgu, donViTinhDaNgonNgu);
        // }
        // }
    }
}
