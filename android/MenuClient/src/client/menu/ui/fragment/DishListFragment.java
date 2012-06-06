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

        View orderPreview = getActivity().findViewById(R.id.ExtendPaneHolder);
        mHaveExtendPane = orderPreview != null
                && orderPreview.getVisibility() == View.VISIBLE;
    }
    
}
