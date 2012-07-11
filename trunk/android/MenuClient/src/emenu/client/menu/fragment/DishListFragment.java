package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.app.SearchManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import emenu.client.bus.loader.DishListLoader;
import emenu.client.bus.task.LoadDishUnitsTask;
import emenu.client.menu.R;
import emenu.client.menu.adapter.DishListAdapter;
import emenu.client.util.U;

public class DishListFragment extends ListFragment implements OnQueryTextListener,
        OnActionExpandListener {

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

    public DishListFragment(Integer maDanhMuc) {
        mMaDanhMuc = maDanhMuc;
    }

    private void cancelLoadDishUnitsTasks() {
        for (int i = 0; i < mDishUnitsLoadTaskList.size(); ++i) {
            LoadDishUnitsTask task = mDishUnitsLoadTaskList.get(i);
            U.cancelAsyncTask(task);
        }
        mDishUnitsLoadTaskList.clear();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem item = menu.findItem(R.id.miDishSearch);
        item.setOnActionExpandListener(this);
        // Get the SearchView and set the searchable configuration
        SearchView searchView = (SearchView) item.getActionView();
        // SearchManager searchManager = (SearchManager)
        // getActivity().getSystemService(
        // Context.SEARCH_SERVICE);
        // searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity()
        // .getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mMaDanhMuc", mMaDanhMuc);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mMaDanhMuc = savedInstanceState.getInt("mMaDanhMuc");
        }

        setHasOptionsMenu(true);

        getView().setBackgroundResource(R.color._ccf5f5f5);

        mDishesAdapter = new DishListAdapter(getActivity(),
                new ArrayList<ContentValues>());
        setListAdapter(mDishesAdapter);
        getListView().setTextFilterEnabled(true);
    }

    public int getMaDanhMuc() {
        return mMaDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.mMaDanhMuc = maDanhMuc;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            getListView().clearTextFilter();
        } else {
            getListView().setFilterText(newText.toString());
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        getLoaderManager().restartLoader(0, null, mLoaderCallbacks);
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }
}
