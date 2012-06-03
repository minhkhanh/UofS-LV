package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import client.menu.R;
import client.menu.bus.loader.RootCategoryListLoader;
import client.menu.db.dto.DanhMucDTO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.adapter.ExpandableCategoryListAdapter;
import client.menu.ui.view.ExpandableCategoryList;
import client.menu.ui.view.ExpandableCategoryView;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;

public class CategoryListFragment extends Fragment implements
        LoaderCallbacks<List<DanhMucDaNgonNguDTO>> {
    private int mSelIndex;
    private boolean mIsDualPane;

    private ExpandableCategoryList mCategoryList;
    private ExpandableCategoryListAdapter mListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return new ExpandableCategoryList(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mSelIndex = savedInstanceState.getInt("mSelIndex", 0);
        }

        getView().setBackgroundResource(R.color._55f5f5f5);

        mListAdapter = new ExpandableCategoryListAdapter(getActivity(),
                new ArrayList<DanhMucDaNgonNguDTO>());
        mCategoryList = (ExpandableCategoryList) getView();
        mCategoryList.setCategoryAdapter(mListAdapter);

        getLoaderManager().initLoader(0, null, this);

        View dishList = getActivity().findViewById(R.id.RightPaneHolder);
        mIsDualPane = dishList != null && dishList.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onLoaderReset(Loader<List<DanhMucDaNgonNguDTO>> loader) {
        mListAdapter.clear();
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadFinished(Loader<List<DanhMucDaNgonNguDTO>> loader,
            List<DanhMucDaNgonNguDTO> result) {
        // for (DanhMucDaNgonNguDTO d : result) {
        // ExpandableCategoryView item = new
        // ExpandableCategoryView(getActivity(), mCategoryList);
        // item.bindData(d);
        //
        // mCategoryList.addCategory(item);
        // }
        mListAdapter.clear();
        mListAdapter.addAll(result);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public Loader<List<DanhMucDaNgonNguDTO>> onCreateLoader(int id, Bundle args) {
        return new RootCategoryListLoader(getActivity());
    }

    // void showDetails(int index) {
    // mSelIndex = index;
    //
    // Cursor cursor = ((SimpleCursorAdapter) getListAdapter()).getCursor();
    // if (cursor == null || // in case of loader not finished yet
    // !cursor.moveToPosition(index))
    // return;
    //
    // int maDanhMuc =
    // cursor.getInt(cursor.getColumnIndex(DanhMucDTO.CL_MA_DANH_MUC));
    //
    // if (mIsDualPane) {
    // getListView().setItemChecked(index, true);
    //
    // DishListFragment dishList = (DishListFragment) getFragmentManager()
    // .findFragmentById(R.id.RightPaneHolder);
    //
    // if (dishList == null || dishList.getMaDanhMuc() != maDanhMuc) {
    // dishList = DishListFragment.newInstance(maDanhMuc);
    //
    // FragmentTransaction ft = getFragmentManager().beginTransaction();
    // ft.replace(R.id.RightPaneHolder, dishList);
    // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    // ft.commit();
    // }
    //
    // } else {
    // DishListFragment dishList = DishListFragment.newInstance(maDanhMuc);
    //
    // FragmentTransaction ft = getFragmentManager().beginTransaction();
    // ft.replace(R.id.LeftPaneHolder, dishList);
    // ft.addToBackStack(null);
    // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    // ft.commit();
    // }
    // }
}
