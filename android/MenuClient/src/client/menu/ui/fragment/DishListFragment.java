package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import client.menu.R;
import client.menu.app.MyApplication;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceSession;
import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.contract.NgonNguContract;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDTO;
import client.menu.util.U;

public class DishListFragment extends ListFragment {

    private static final int LOADER_ID_DISH_LIST = 0;

    private int mMaDanhMuc;
    private SimpleCursorAdapter mAdapter;

    private boolean mHaveExtendPane;

    private class LoadDishUnitsAsyncTask extends
            AsyncTask<Void, Integer, SimpleCursorAdapter> {

        Integer mIndex;
        Spinner mSpinner;

        public LoadDishUnitsAsyncTask(Spinner spinner, int index) {
            mIndex = index;
            mSpinner = spinner;
        }

        @Override
        protected void onPostExecute(SimpleCursorAdapter result) {
            mSpinner.setAdapter(result);
            mSpinner.setFocusable(false); // does the magic!
        };

        @Override
        protected SimpleCursorAdapter doInBackground(Void... unused) {
            Cursor adaptCursor = mAdapter.getCursor();
            adaptCursor.moveToPosition(mIndex);
            Integer maMonAn = adaptCursor.getInt(adaptCursor
                    .getColumnIndex(MonAnContract.COL_SID));

            String[] projection = new String[] {
                    DonViTinhMonAnContract.TABLE_NAME + "." + DonViTinhMonAnContract._ID,
                    DonViTinhMonAnContract.COL_MA_MON_AN,
                    DonViTinhMonAnContract.TABLE_NAME + "."
                            + DonViTinhMonAnContract.COL_MA_DON_VI,
                    DonViTinhMonAnContract.COL_DON_GIA,
                    DonViTinhDaNgonNguContract.COL_TEN_DON_VI_TINH };
            String selection = DonViTinhMonAnContract.COL_MA_MON_AN + "=? and "
                    + DonViTinhDaNgonNguContract.COL_MA_NGON_NGU + "=?";
            String[] selArgs = new String[] {
                    maMonAn.toString(),
                    MyApplication.gSettings.getLocale().getLanguage().getMaNgonNgu()
                            .toString() };

            Cursor cursor = getActivity().getContentResolver().query(
                    DonViTinhMonAnContract.URI_DONVITINHMONAN_INNER_DANGONNGU,
                    projection, selection, selArgs, null);

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
                    android.R.layout.simple_spinner_item, cursor,
                    new String[] { DonViTinhDaNgonNguContract.COL_TEN_DON_VI_TINH },
                    new int[] { android.R.id.text1 }, 0);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            return adapter;
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
                            .getMaNgonNgu();

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

        // menu.add("check");
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

        View orderPreview = getActivity().findViewById(R.id.ExtendPaneHolder);
        mHaveExtendPane = orderPreview != null
                && orderPreview.getVisibility() == View.VISIBLE;
    }

    private DonViTinhMonAnDTO extractDonViTinhMonAnDTO(int pos) {
        ViewGroup row = (ViewGroup) getListView().getChildAt(pos);
        Spinner spinner = (Spinner) row.findViewById(R.id.spinDishPrices);

        SimpleCursorAdapter adapter = (SimpleCursorAdapter) spinner.getAdapter();
        Cursor cursor = adapter.getCursor();
        if (cursor != null && cursor.moveToPosition(pos)) {
            return DonViTinhMonAnDTO.extractFrom(cursor);
        }

        return null;
    }

    private MonAnDTO extractMonAnDTO(int pos) {
        Cursor cursor = mAdapter.getCursor();
        if (cursor != null && cursor.moveToPosition(pos)) {
            return MonAnDTO.extractFrom(cursor);
        }

        return null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // MonAnDTO monAn = extractMonAnDTO(position);
        // // DonViTinhMonAnDTO dvt = extractDonViTinhMonAnDTO(position);
        //
        // SessionManager sessionManager = ((MyApplication)
        // getActivity().getApplication())
        // .getSessionManager();
        // ServiceSession session = sessionManager.loadCurrentSession();
        //
        // session.addOrderItem(maMonAn, maDonViTinh);
        //
        // if (mHaveExtendPane) {
        // OrderPreviewFragment orderPreview = (OrderPreviewFragment)
        // getFragmentManager()
        // .findFragmentById(R.id.ExtendPaneHolder);
        //
        // if (orderPreview == null) {
        // orderPreview = new OrderPreviewFragment(monAn.getMaMonAn());
        //
        // FragmentTransaction ft = getFragmentManager().beginTransaction();
        // ft.replace(R.id.ExtendPaneHolder, orderPreview);
        // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // ft.commit();
        // } else {
        // orderPreview.updateList(monAn.getMaMonAn());
        // }
        //
        // // orderPreview.updateList(monAn);
        //
        // U.toastText(getActivity(), "item click ok");
        // }
    }
}
