package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import client.menu.R;
import client.menu.bus.loader.DishListLoader;
import client.menu.bus.task.LoadDishUnitsAsyncTask;
import client.menu.ui.adapter.DishListAdapter;
import client.menu.util.U;

public class DishListFragment extends ListFragment {

    private Integer mMaDanhMuc;
    List<LoadDishUnitsAsyncTask> mDishUnitsLoadTaskList = new ArrayList<LoadDishUnitsAsyncTask>();
    private DishListAdapter mListAdapter;

    private boolean mHaveExtendPane;

    private LoaderCallbacks<List<ContentValues>> mLoaderCallbacks = new LoaderCallbacks<List<ContentValues>>() {

        @Override
        public void onLoaderReset(Loader<List<ContentValues>> arg0) {
            mListAdapter.clear();
            mListAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLoadFinished(Loader<List<ContentValues>> arg0,
                List<ContentValues> arg1) {
            mListAdapter.clear();
            mListAdapter.addAll(arg1);
            mListAdapter.notifyDataSetChanged();

            setListShown(true);
        }

        @Override
        public Loader<List<ContentValues>> onCreateLoader(int id, Bundle args) {
            setListShown(false);
            return new DishListLoader(getActivity(), mMaDanhMuc);
        }
    };

    private void cancelLoadDishUnitsTasks() {
        for (int i = 0; i < mDishUnitsLoadTaskList.size(); ++i) {
            LoadDishUnitsAsyncTask task = mDishUnitsLoadTaskList.get(i);
            U.cancelAsyncTask(task);
        }
        mDishUnitsLoadTaskList.clear();
    }

//    private OnHierarchyChangeListener mOnItemListChange = new OnHierarchyChangeListener() {
//
//        @Override
//        public void onChildViewRemoved(View parent, View child) {
//        }
//
//        @Override
//        public void onChildViewAdded(View parent, View child) {
//            int index = getListView().getPositionForView(child);
//            ContentValues c = mListAdapter.getItem(index);
//            Integer maMonAn = c.getAsInteger(MonAnDTO.CL_MA_MON_AN);
//
//            LoadDishUnitsAsyncTask2 task = new LoadDishUnitsAsyncTask2(getActivity(), 0,
//                    maMonAn);
//            task.setOnPostExecuteListener(DishListFragment.this);
//            mDishUnitsLoadTaskList.add(task);
//            task.execute();
//        }
//    };

    @Override
    public void onResume() {
        super.onResume();

        getLoaderManager().initLoader(0, null, mLoaderCallbacks);
    }

    @Override
    public void onPause() {
        super.onPause();

        cancelLoadDishUnitsTasks();
    }

    public int getMaDanhMuc() {
        return mMaDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.mMaDanhMuc = maDanhMuc;
    }

    public DishListFragment(Integer maDanhMuc) {
        mMaDanhMuc = maDanhMuc;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mMaDanhMuc", mMaDanhMuc);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mMaDanhMuc = savedInstanceState.getInt("mMaDanhMuc");
        }

        getView().setBackgroundResource(R.color._55f5f5f5);

        setHasOptionsMenu(true);

        mListAdapter = new DishListAdapter(getActivity(), new ArrayList<ContentValues>());
        setListAdapter(mListAdapter);

//        getListView().setOnHierarchyChangeListener(mOnItemListChange);

        View orderPreview = getActivity().findViewById(R.id.ExtendPaneHolder);
        mHaveExtendPane = orderPreview != null
                && orderPreview.getVisibility() == View.VISIBLE;
    }
    
    private void prepareViews() {
    }

    // private DonViTinhMonAnDTO extractDonViTinhMonAnDTO(int pos) {
    // ViewGroup row = (ViewGroup) getListView().getChildAt(pos);
    // Spinner spinner = (Spinner) row.findViewById(R.id.spinDishUnits);
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
    //
    // private SimpleCursorAdapter extractUnitsAdapter(int pos) {
    // ViewGroup row = (ViewGroup) getListView().getChildAt(pos);
    // Spinner spinner = (Spinner) row.findViewById(R.id.spinDishUnits);
    //
    // return (SimpleCursorAdapter) spinner.getAdapter();
    // }
    //
    // private DonViTinhDaNgonNguDTO extractDonViTinhDaNgonNguDTO(int pos) {
    // ViewGroup row = (ViewGroup) getListView().getChildAt(pos);
    // Spinner spinner = (Spinner) row.findViewById(R.id.spinDishUnits);
    //
    // SimpleCursorAdapter adapter = (SimpleCursorAdapter) spinner.getAdapter();
    // Cursor cursor = adapter.getCursor();
    // if (cursor != null &&
    // cursor.moveToPosition(spinner.getSelectedItemPosition())) {
    // return DonViTinhDaNgonNguDTO.extractFrom(cursor);
    // }
    //
    // return null;
    // }
    //
    // private MonAnDaNgonNguDTO extractMonAnDaNgonNguDTO(int pos) {
    // Cursor cursor = mListAdapter.getCursor();
    // if (cursor != null && cursor.moveToPosition(pos)) {
    // return MonAnDaNgonNguDTO.extractFrom(cursor);
    // }
    //
    // return null;
    // }
    //
    // private MonAnDTO extractMonAnDTO(int pos) {
    // Cursor cursor = mListAdapter.getCursor();
    // if (cursor != null && cursor.moveToPosition(pos)) {
    // return MonAnDTO.from(cursor);
    // }
    //
    // return null;
    // }

    // @Override
    // public void onListItemClick(ListView l, View v, int position, long id) {
    // super.onListItemClick(l, v, position, id);
    //
    // MonAnDTO monAn = extractMonAnDTO(position);
    // MonAnDaNgonNguDTO monAnDaNgonNgu = extractMonAnDaNgonNguDTO(position);
    // SimpleCursorAdapter adapter = extractUnitsAdapter(position);
    // DonViTinhMonAnDTO donViTinhMonAn = extractDonViTinhMonAnDTO(position);
    //
    // DishDetailDialogFragment newFragment = new
    // DishDetailDialogFragment(monAn,
    // monAnDaNgonNgu, donViTinhMonAn.getMaDonViTinh(), adapter);
    // U.showDlgFragment(getActivity(), newFragment, "dialog");
    //
    // // if (mHaveExtendPane) {
    // // OrderPreviewFragment orderPreview = (OrderPreviewFragment)
    // // getFragmentManager()
    // // .findFragmentById(R.id.ExtendPaneHolder);
    // //
    // // if (orderPreview == null) {
    // // orderPreview = new OrderPreviewFragment();
    // // orderPreview.addItemData(monAnDaNgonNgu, donViTinhDaNgonNgu);
    // //
    // // FragmentTransaction ft = getFragmentManager().beginTransaction();
    // // ft.replace(R.id.ExtendPaneHolder, orderPreview);
    // // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    // // ft.commit();
    // // } else {
    // // orderPreview.updateList(monAnDaNgonNgu, donViTinhDaNgonNgu);
    // // }
    // // }
    // }

//    @Override
//    public void onPostExecuteAsyncTask(
//            CustomAsyncTask<Void, Integer, SimpleCursorAdapter> task,
//            SimpleCursorAdapter result) {
//        LoadDishUnitsAsyncTask2 specTask = (LoadDishUnitsAsyncTask2) task;
//        int index = mListAdapter.indexOf(specTask.getMaMonAn());
//        View v = getListView().getChildAt(index);
//        Spinner spinner = (Spinner) v.findViewById(R.id.spinDishUnits);
//        spinner.setAdapter(result);
//    }
}
