package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.Loader;
import android.os.Bundle;
import emenu.client.bus.loader.DishListLoader;
import emenu.client.bus.task.LoadDishUnitsTask;
import emenu.client.menu.R;
import emenu.client.menu.adapter.DishListAdapter;
import emenu.client.util.U;

public class DishListFragment extends ListFragment {

    private Integer mMaDanhMuc;
    List<LoadDishUnitsTask> mDishUnitsLoadTaskList = new ArrayList<LoadDishUnitsTask>();
    private DishListAdapter mDishesAdapter;

    private boolean mHaveExtendPane;

    private LoaderCallbacks<List<ContentValues>> mLoaderCallbacks = new LoaderCallbacks<List<ContentValues>>() {

        @Override
        public void onLoaderReset(Loader<List<ContentValues>> arg0) {
            mDishesAdapter.clear();
            mDishesAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLoadFinished(Loader<List<ContentValues>> arg0,
                List<ContentValues> arg1) {
            mDishesAdapter.clear();
            mDishesAdapter.addAll(arg1);
            mDishesAdapter.notifyDataSetChanged();

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
            LoadDishUnitsTask task = mDishUnitsLoadTaskList.get(i);
            U.cancelAsyncTask(task);
        }
        mDishUnitsLoadTaskList.clear();
    }

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

        getView().setBackgroundResource(R.color._ccf5f5f5);

        setHasOptionsMenu(true);

        mDishesAdapter = new DishListAdapter(getActivity(), new ArrayList<ContentValues>());
        setListAdapter(mDishesAdapter);

//        View orderPreview = getActivity().findViewById(R.id.ExtendPaneHolder);
//        mHaveExtendPane = orderPreview != null
//                && orderPreview.getVisibility() == View.VISIBLE;
    }
    
}
