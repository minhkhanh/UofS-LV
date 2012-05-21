package client.menu.ui.fragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import client.menu.R;
import client.menu.app.MyApplication;
import client.menu.db.contract.DanhMucContract;
import client.menu.db.contract.DanhMucDaNgonNguContract;
import client.menu.db.dao.DanhMucDAO;
import client.menu.db.dto.DanhMucDTO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.db.dto.NgonNguDTO;

public class CategoryListFragment extends ListFragment {

    public static final int LOADER_ID_CAT_LIST = 0;

    public static final int MSG_LOADER_FINISHED_CAT_LIST = 0;

    private boolean mIsDualPane;
    private int mSelIndex;
    private SimpleCursorAdapter mAdapter;

    LoadCategoryListTask mLoadCategoryListTask;

    class LoadCategoryListTask extends AsyncTask<Void, Integer, Cursor> {

        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);

            mAdapter.changeCursor(result);
            if (mIsDualPane) {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                showDetails(mSelIndex);
            }
        }

        @Override
        protected Cursor doInBackground(Void... params) {
            NgonNguDTO ngonNgu = MyApplication.getSettings(getActivity()).getLocale()
                    .getLanguage();
            return DanhMucDAO.getInstance().cursorByMaNgonNgu(
                    ngonNgu.getMaNgonNgu());
        }

    };

    // private Handler handler = new Handler() {
    // public void handleMessage(Message msg) {
    // if (msg.what == MSG_LOADER_FINISHED_CAT_LIST) {
    // if (mIsDualPane) {
    // getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    // showDetails(mSelIndex);
    // }
    // }
    // };
    // };
    //
    // private LoaderCallbacks<Cursor> mLoaderCallbacks = new
    // LoaderCallbacks<Cursor>() {
    //
    // @Override
    // public void onLoaderReset(Loader<Cursor> loader) {
    // switch (loader.getId()) {
    // case LOADER_ID_CAT_LIST:
    // mAdapter.swapCursor(null);
    // break;
    // }
    // }
    //
    // @Override
    // public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
    // switch (loader.getId()) {
    // case LOADER_ID_CAT_LIST:
    // mAdapter.swapCursor(cursor);
    // handler.sendEmptyMessage(MSG_LOADER_FINISHED_CAT_LIST);
    // break;
    // }
    // }
    //
    // @Override
    // public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    // switch (id) {
    // case LOADER_ID_CAT_LIST:
    // String[] proj = new String[] {
    // DanhMucContract.TABLE_NAME + "." + DanhMucContract.CL_ID,
    // DanhMucContract.TABLE_NAME + "."
    // + DanhMucContract.CL_MA_DANH_MUC,
    // DanhMucDaNgonNguContract.CL_TEN_DANH_MUC };
    // Integer sid = MyApplication.getSettings(getActivity()).getLocale()
    // .getLanguage().getMaNgonNgu();
    // CursorLoader loader = new CursorLoader(getActivity(),
    // DanhMucContract.URI_DANHMUC_INNER_DANGONNGU, proj,
    // DanhMucDaNgonNguContract.CL_MA_NGON_NGU + "=?",
    // new String[] { sid.toString() }, null);
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

        mLoadCategoryListTask = new LoadCategoryListTask();
        mLoadCategoryListTask.execute();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mLoadCategoryListTask.getStatus() != AsyncTask.Status.FINISHED) {
            mLoadCategoryListTask.cancel(true);
        } else {
            mAdapter.changeCursor(null);
        }
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

        String[] from = new String[] { DanhMucDaNgonNguDTO.CL_TEN_DANH_MUC };
        int[] to = new int[] { android.R.id.text1 };

        mAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_activated_1, null, from, to, 0);
        setListAdapter(mAdapter);

        // getLoaderManager().initLoader(LOADER_ID_CAT_LIST, null,
        // mLoaderCallbacks);

        View dishList = getActivity().findViewById(R.id.ContentPaneHolder);
        mIsDualPane = dishList != null && dishList.getVisibility() == View.VISIBLE;
    }

    void showDetails(int index) {
        mSelIndex = index;

        Cursor cursor = ((SimpleCursorAdapter) getListAdapter()).getCursor();
        if (cursor == null || // in case of loader not finished yet
                !cursor.moveToPosition(index))
            return;

        int maDanhMuc = cursor.getInt(cursor
                .getColumnIndex(DanhMucDTO.CL_MA_DANH_MUC));

        if (mIsDualPane) {
            getListView().setItemChecked(index, true);

            DishListFragment dishList = (DishListFragment) getFragmentManager()
                    .findFragmentById(R.id.ContentPaneHolder);

            if (dishList == null || dishList.getMaDanhMuc() != maDanhMuc) {
                dishList = DishListFragment.newInstance(maDanhMuc);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.ContentPaneHolder, dishList);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            DishListFragment dishList = DishListFragment.newInstance(maDanhMuc);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.GroupPaneHolder, dishList);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }
}
