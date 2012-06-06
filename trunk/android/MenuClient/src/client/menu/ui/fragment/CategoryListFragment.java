package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import client.menu.R;
import client.menu.bus.loader.RootCategoryListLoader;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.adapter.ExpandableCategoryAdapter3;
import client.menu.ui.view.ExpandableCategoryList;
import client.menu.ui.view.ExpandableCategoryList3;
import client.menu.ui.view.ExpandableCategoryList.OnCategoryClickListener;
import client.menu.ui.view.ExpandableCategoryView;

public class CategoryListFragment extends Fragment implements
        LoaderCallbacks<List<DanhMucDaNgonNguDTO>>, OnCategoryClickListener {
    private int mSelIndex;
    private boolean mIsDualPane;

    private ExpandableCategoryList3 mCategoryList;
    private ExpandableCategoryAdapter3 mListAdapter;

    public static class CategoryNode {
        public DanhMucDaNgonNguDTO danhMuc;
        public int indent;
    }

    private List<CategoryNode> mCategoryTree = new ArrayList<CategoryListFragment.CategoryNode>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return new ExpandableCategoryList3(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mSelIndex = savedInstanceState.getInt("mSelIndex", 0);
        }

        getView().setBackgroundResource(R.color._55f5f5f5);

        mListAdapter = new ExpandableCategoryAdapter3(getActivity(),
                new ArrayList<CategoryNode>());
        mCategoryList = (ExpandableCategoryList3) getView();
        mCategoryList.setAdapter(mListAdapter);
        // mCategoryList.setExpandableCategoryAdapter(mListAdapter);
        // mCategoryList.setOnCategoryClickListener3(this);

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
//        List<CategoryNode> adapterData = new ArrayList<CategoryListFragment.CategoryNode>();
        for (DanhMucDaNgonNguDTO d : result) {
            CategoryNode node = new CategoryNode();
            node.danhMuc = d;
            node.indent = 0;
            mCategoryTree.add(node);
            
//            node = new CategoryNode();
//            node.danhMuc = d;
//            node.indent = 0;
//            adapterData.add(node);
        }

        mListAdapter.clear();
        mListAdapter.addAll(mCategoryTree);
        mListAdapter.setTreeData(mCategoryTree);
        // mListAdapter.addGroupAll(result);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public Loader<List<DanhMucDaNgonNguDTO>> onCreateLoader(int id, Bundle args) {
        return new RootCategoryListLoader(getActivity());
    }

    @Override
    public void onCategoryItemClick(ExpandableCategoryList list,
            ExpandableCategoryView item) {
        showDetails(item.getDanhMuc().getMaDanhMuc());
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
}
