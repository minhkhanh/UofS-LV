package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import client.menu.R;
import client.menu.bus.loader.RootCategoryListLoader;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.bus.task.LoadChildCategoryListTask;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.adapter.ExpandableCategoryAdapter;

public class CategoryListFragment extends ListFragment implements
        LoaderCallbacks<List<DanhMucDaNgonNguDTO>>,
        OnPostExecuteAsyncTaskListener<Void, Integer, List<DanhMucDaNgonNguDTO>> {
    private int mSelIndex;
    private boolean mIsDualPane;

    private ExpandableCategoryAdapter mListAdapter;

    public static class CategoryNode {
        public static final int EXPANDED = 1;
        public static final int COLLAPSED = -1;
        public static final int NORMAL = 0;

        public DanhMucDaNgonNguDTO danhMuc;
        public int indent = 0;
        public int state = NORMAL;
    }

    private List<CategoryNode> mCategoryTree = new ArrayList<CategoryListFragment.CategoryNode>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mSelIndex = savedInstanceState.getInt("mSelIndex", 0);
        }

        getView().setBackgroundResource(R.color._55f5f5f5);

        mListAdapter = new ExpandableCategoryAdapter(getActivity(),
                new ArrayList<CategoryNode>());
        setListAdapter(mListAdapter);
        getListView().setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        getListView().setSelector(R.drawable.activated_background);

        getLoaderManager().initLoader(0, null, this);

        View dishList = getActivity().findViewById(R.id.RightPaneHolder);
        mIsDualPane = dishList != null && dishList.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onLoaderReset(Loader<List<DanhMucDaNgonNguDTO>> loader) {
        mListAdapter.clear();
        mListAdapter.notifyDataSetChanged();

        mCategoryTree.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<DanhMucDaNgonNguDTO>> loader,
            List<DanhMucDaNgonNguDTO> result) {
        mCategoryTree.clear();
        for (DanhMucDaNgonNguDTO d : result) {
            CategoryNode node = new CategoryNode();
            node.danhMuc = d;
            mCategoryTree.add(node);
        }

        mListAdapter.clear();
        mListAdapter.addAll(mCategoryTree);
        mListAdapter.setTreeData(mCategoryTree);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public Loader<List<DanhMucDaNgonNguDTO>> onCreateLoader(int id, Bundle args) {
        return new RootCategoryListLoader(getActivity());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
        DanhMucDaNgonNguDTO danhMucCha = mListAdapter.getItem(position).danhMuc;
        int treePosition = mListAdapter.getTreePosition(danhMucCha.getMaDanhMuc());

        if (mListAdapter.getItem(position).state == CategoryNode.EXPANDED) {
            mListAdapter.collapse(position, treePosition);
            mListAdapter.notifyDataSetChanged();
        } else {
            if (mListAdapter.isChildrenLoaded(treePosition)) {
                mListAdapter.expand(position, treePosition);
                mListAdapter.notifyDataSetChanged();
            } else {
                Bundle extras = new Bundle();
                extras.putInt("position", position);
                extras.putInt("treePosition", treePosition);

                LoadChildCategoryListTask task = new LoadChildCategoryListTask(
                        getActivity(), danhMucCha.getMaDanhMuc());
                task.setOnPostExecuteListener(CategoryListFragment.this);
                task.setExtras(extras);
                task.execute();
            }
        }

        showDetails(danhMucCha.getMaDanhMuc());
    }

    void showDetails(Integer maDanhMuc) {
        if (mIsDualPane) {
            DishListFragment dishList = (DishListFragment) getFragmentManager()
                    .findFragmentById(R.id.RightPaneHolder);

            if (dishList == null || dishList.getMaDanhMuc() != maDanhMuc) {
                dishList = new DishListFragment(maDanhMuc);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.RightPaneHolder, dishList);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            DishListFragment dishList = new DishListFragment(maDanhMuc);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.LeftPaneHolder, dishList);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    @Override
    public void onPostExecuteAsyncTask(
            CustomAsyncTask<Void, Integer, List<DanhMucDaNgonNguDTO>> task,
            List<DanhMucDaNgonNguDTO> result) {
        if (result.size() > 0) {
            Bundle ex = task.getExtras();
            int position = ex.getInt("position");
            int treePosition = ex.getInt("treePosition");
            mListAdapter.expand(position, treePosition, result);
            mListAdapter.notifyDataSetChanged();
        }
    }
}
