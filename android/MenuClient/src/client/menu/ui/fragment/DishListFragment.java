package client.menu.ui.fragment;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import client.menu.R;
import client.menu.application.MyApplication;
import client.menu.db.contract.BanContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.contract.NgonNguContract;
import client.menu.db.dto.DanhMucDTO;
import client.menu.db.dto.MonAnDTO;
import client.menu.ui.adapter.DishListAdapter;
import client.menu.util.C;

public class DishListFragment extends ListFragment {

    private static final int LOADER_ID_DISH_LIST = 0;

    private int mMaDanhMuc;
    private SimpleCursorAdapter mAdapter;

    private LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderCallbacks<Cursor>() {

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            switch (loader.getId()) {
                case LOADER_ID_DISH_LIST:
                    mAdapter.swapCursor(null);
                    break;
            }
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            switch (loader.getId()) {
                case LOADER_ID_DISH_LIST:
                    mAdapter.swapCursor(cursor);
//                    while (cursor.moveToNext()) {
//                        ContentValues values = MonAnContract.extractData(cursor);
//                        int a = 2;
//                    }
                    break;
            }
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            switch (id) {
                case LOADER_ID_DISH_LIST:
                    String[] proj = new String[] {
                            MonAnContract.TABLE_NAME + "." + MonAnContract._ID,
                            MonAnContract.TABLE_NAME + "." + MonAnContract.COL_SID,
                            MonAnDaNgonNguContract.COL_DISH_NAME };
                    Integer sid = MyApplication.gSettings.getLocale().getLanguage()
                            .getAsInteger(NgonNguContract.COL_SID);
                    CursorLoader loader = new CursorLoader(getActivity(),
                            MonAnContract.URI_MONAN_INNER_DANGONNGU, proj,
                            MonAnContract.COL_CATEGORY_ID + " = ? and "
                                    + MonAnDaNgonNguContract.COL_LANGUAGE_ID + "=?",
                            new String[] { String.valueOf(mMaDanhMuc), sid.toString() },
                            null);

                    return loader;
            }

            return null;
        }
    };

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
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mMaDanhMuc = savedInstanceState.getInt("mMaDanhMuc");
        }

        setHasOptionsMenu(true);

        String[] from = new String[] { MonAnDaNgonNguContract.COL_DISH_NAME };
        int[] to = new int[] { R.id.DishName };
        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.tmp, null,
                from, to, 0);
        
        setListAdapter(mAdapter);

        getLoaderManager().initLoader(LOADER_ID_DISH_LIST, null, mLoaderCallbacks);
    }
}
