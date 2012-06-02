package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import client.menu.R;
import client.menu.bus.loader.RootCategoryListLoader;
import client.menu.db.dto.DanhMucDTO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.adapter.ExpandableCategoryListAdapter;

public class CategoryListFragment extends ListFragment implements
        LoaderCallbacks<List<DanhMucDaNgonNguDTO>> {

    private boolean mIsDualPane;
    private int mSelIndex;
    private ExpandableCategoryListAdapter mAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mIsDualPane) {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                showDetails(mSelIndex);
            }
        };
    };

    @Override
    public void onLoaderReset(Loader<List<DanhMucDaNgonNguDTO>> loader) {
        // mAdapter.swapCursor(null);
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadFinished(Loader<List<DanhMucDaNgonNguDTO>> loader,
            List<DanhMucDaNgonNguDTO> result) {
        // mAdapter.swapCursor(cursor);
        mAdapter.clear();
        mAdapter.addAll(result);
        mAdapter.notifyDataSetChanged();
        // handler.sendEmptyMessage(0);
    }

    @Override
    public Loader<List<DanhMucDaNgonNguDTO>> onCreateLoader(int id, Bundle args) {
        return new RootCategoryListLoader(getActivity());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mSelIndex", mSelIndex);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mSelIndex = savedInstanceState.getInt("mSelIndex", 0);
        }

        getView().setBackgroundResource(R.color._55f5f5f5);
        // getView().setAlpha(0.35f);

        // String[] from = new String[] { DanhMucDaNgonNguDTO.CL_TEN_DANH_MUC };
        // int[] to = new int[] { android.R.id.text1 };
        // mAdapter = new SimpleCursorAdapter(getActivity(),
        // android.R.layout.simple_list_item_activated_1, null, from, to, 0);
        mAdapter = new ExpandableCategoryListAdapter(getActivity(),
                new ArrayList<DanhMucDaNgonNguDTO>());
        setListAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);

        View dishList = getActivity().findViewById(R.id.RightPaneHolder);
        mIsDualPane = dishList != null && dishList.getVisibility() == View.VISIBLE;
    }

    void showDetails(int index) {
        mSelIndex = index;

        Cursor cursor = ((SimpleCursorAdapter) getListAdapter()).getCursor();
        if (cursor == null || // in case of loader not finished yet
                !cursor.moveToPosition(index))
            return;

        int maDanhMuc = cursor.getInt(cursor.getColumnIndex(DanhMucDTO.CL_MA_DANH_MUC));

        if (mIsDualPane) {
            getListView().setItemChecked(index, true);

            DishListFragment dishList = (DishListFragment) getFragmentManager()
                    .findFragmentById(R.id.RightPaneHolder);

            if (dishList == null || dishList.getMaDanhMuc() != maDanhMuc) {
                dishList = DishListFragment.newInstance(maDanhMuc);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.RightPaneHolder, dishList);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            DishListFragment dishList = DishListFragment.newInstance(maDanhMuc);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.LeftPaneHolder, dishList);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }
}
