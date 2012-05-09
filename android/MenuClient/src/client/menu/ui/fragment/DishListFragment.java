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
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import client.menu.R;
import client.menu.application.MyApplication;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.contract.NgonNguContract;
import client.menu.util.C;

public class DishListFragment extends ListFragment {

    private static final int LOADER_ID_DISH_LIST = 0;

    private int mMaDanhMuc;
    private SimpleCursorAdapter mAdapter;

    private class LoadDishUnitsAsyncTask extends
            AsyncTask<Integer, Integer, List<ArrayAdapter<String>>> {
        
        List<Spinner> mSpinnerList = new ArrayList<Spinner>();

        @Override
        protected void onPostExecute(List<ArrayAdapter<String>> result) {
            for (int i = 0; i < result.size(); ++i) {;
                Spinner spinner = mSpinnerList.get(i);
                spinner.setAdapter(result.get(i));
                // spinner.setBackgroundColor(R.color.red);
//                Log.d("onPostExecute", result.get(i) == null ? "null" : "not null");
            }
        };

        @Override
        protected List<ArrayAdapter<String>> doInBackground(Integer... params) {
            List<ArrayAdapter<String>> result = new ArrayList<ArrayAdapter<String>>();

            Cursor adaptCursor = mAdapter.getCursor();
            for (int i = 0; i < params.length; ++i) {
                
                adaptCursor.moveToPosition(params[i]);
                Integer maMonAn = adaptCursor.getInt(adaptCursor
                        .getColumnIndex(MonAnContract.COL_SID));

                String[] projection = new String[] { DonViTinhMonAnContract._ID,
                        DonViTinhMonAnContract.COL_MA_MON_AN,
                        DonViTinhMonAnContract.COL_MA_DON_VI,
                        DonViTinhMonAnContract.COL_DON_GIA };
                String selection = DonViTinhMonAnContract.COL_MA_MON_AN + "=?";
                String[] selArgs = new String[] { maMonAn.toString() };

                Cursor cursor = getActivity().getContentResolver().query(
                        DonViTinhMonAnContract.CONTENT_URI, projection, selection,
                        selArgs, null);

                if (cursor != null) {
                    List<String> items = new ArrayList<String>();
                    while (cursor.moveToNext()) {
                        // Integer _id = cursor.getInt(0);
                        Float price = cursor.getFloat(3);

                        items.add(price.toString());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getActivity(), android.R.layout.simple_spinner_item,
                            android.R.id.text1, items);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    result.add(adapter);
                    
                    ViewGroup frame = (ViewGroup) getListView().getChildAt(params[i]);
                    Spinner spinner = (Spinner) frame.findViewById(R.id.spinDishPrices);
                    mSpinnerList.add(spinner);
                }
            }

            return result;
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
                    for (int i = 0; i < getListView().getCount(); ++i) {
                        new LoadDishUnitsAsyncTask().execute(i);
                    }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        for (int i = 0; i < getListAdapter().getCount(); ++i) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, android.R.id.text1,
                    new String[] { "10" });
            
            

            final ViewGroup frame = (ViewGroup) getListView().getChildAt(i);
            final Spinner spinner = (Spinner) frame.findViewById(R.id.spinDishPrices);
            spinner.setAdapter(adapter);

            Log.d("onOptionsItemSelected", spinner.getAdapter() == null ? "null"
                    : "not null");
        }
        return true;
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

        getLoaderManager().initLoader(LOADER_ID_DISH_LIST, null, mLoaderCallbacks);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
