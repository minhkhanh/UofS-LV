package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.application.MyApplication;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.contract.NgonNguContract;

public class DishListFragment extends ListFragment {

    private static final int LOADER_ID_DISH_LIST = 0;

    private int mMaDanhMuc;
    private SimpleCursorAdapter mAdapter;

    private class LoadDishUnitsAsyncTask extends
            AsyncTask<Void, Integer, ArrayAdapter<String>> {

        Integer mIndex;
        Spinner mSpinner;

        public LoadDishUnitsAsyncTask(Spinner spinner, int index) {
            mIndex = index;
            mSpinner = spinner;
        }

        @Override
        protected void onPostExecute(ArrayAdapter<String> result) {
            mSpinner.setAdapter(result);
        };

        @Override
        protected ArrayAdapter<String> doInBackground(Void... unused) {
            Cursor adaptCursor = mAdapter.getCursor();
            adaptCursor.moveToPosition(mIndex);
            Integer maMonAn = adaptCursor.getInt(adaptCursor
                    .getColumnIndex(MonAnContract.COL_SID));

            String[] projection = new String[] { DonViTinhMonAnContract._ID,
                    DonViTinhMonAnContract.COL_MA_MON_AN,
                    DonViTinhMonAnContract.COL_MA_DON_VI,
                    DonViTinhMonAnContract.COL_DON_GIA };
            String selection = DonViTinhMonAnContract.COL_MA_MON_AN + "=?";
            String[] selArgs = new String[] { maMonAn.toString() };

            Cursor cursor = getActivity().getContentResolver().query(
                    DonViTinhMonAnContract.CONTENT_URI, projection, selection, selArgs,
                    null);

            if (cursor != null) {
                List<String> data = new ArrayList<String>();
                while (cursor.moveToNext()) {
                    // Integer _id = cursor.getInt(0);
                    Float price = cursor.getFloat(3);

                    data.add(Float.toString(price));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, android.R.id.text1, data);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                return adapter;
            }

            return null;
        }
    };

    private OnHierarchyChangeListener mOnListItemChange = new OnHierarchyChangeListener() {

        @Override
        public void onChildViewRemoved(View parent, View child) {
        }

        @Override
        public void onChildViewAdded(View parent, View child) {
            if (child.getId() == R.id.itemDishList) {
                int pos = getListView().getPositionForView(child);

                Spinner spinner = (Spinner) ((ViewGroup) child)
                        .findViewById(R.id.spinDishPrices);

                new LoadDishUnitsAsyncTask(spinner, pos).execute();
            }
        }
    };

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
        public void onLoadFinished(Loader<Cursor> loader, Cursor loadedCursor) {
            switch (loader.getId()) {
                case LOADER_ID_DISH_LIST:
                    mAdapter.swapCursor(loadedCursor);
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
                            MonAnDaNgonNguContract.COL_TEN_MON,
                            MonAnDaNgonNguContract.COL_MO_TA_MON };

                    String selection = MonAnContract.COL_CATEGORY_ID + "=? and "
                            + MonAnDaNgonNguContract.COL_LANGUAGE_ID + "=?";

                    Integer sid = MyApplication.gSettings.getLocale().getLanguage()
                            .getAsInteger(NgonNguContract.COL_SID);

                    CursorLoader loader = new CursorLoader(getActivity(),
                            MonAnContract.URI_MONAN_INNER_DANGONNGU, proj, selection,
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.add("check");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mMaDanhMuc = savedInstanceState.getInt("mMaDanhMuc");
        }

        setHasOptionsMenu(true);

        String[] from = new String[] { MonAnDaNgonNguContract.COL_TEN_MON,
                MonAnDaNgonNguContract.COL_MO_TA_MON };
        int[] to = new int[] { R.id.textDishName, R.id.textDishDescription };
        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item_dish_list, null,
                from, to, 0);

        setListAdapter(mAdapter);
        getListView().setOnHierarchyChangeListener(mOnListItemChange);

        getLoaderManager().initLoader(LOADER_ID_DISH_LIST, null, mLoaderCallbacks);
    }
}
