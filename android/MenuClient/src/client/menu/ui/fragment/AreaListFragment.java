package client.menu.ui.fragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import client.menu.R;
import client.menu.bus.loader.AreaListLoader;
import client.menu.db.dto.KhuVucDTO;

public class AreaListFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    public static final int MSG_LOADER_FINISHED_AREA_LIST = 0;

    private boolean mIsDualPane;
    private int mSelIndex;

    private SimpleCursorAdapter mAreaAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mIsDualPane) {
                showDetails(mSelIndex);
            }
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mSelIndex", mSelIndex);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showDetails(position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().setBackgroundResource(R.drawable.wood_white_oak);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        String[] from = new String[] { KhuVucDTO.CL_TEN_KHU_VUC };
        int[] to = new int[] { R.id.textItemTitle };
        mAreaAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.item_center_aligned_text, null, from, to, 0);
        setListAdapter(mAreaAdapter);

        getLoaderManager().initLoader(0, null, this);

        View detailsFrame = getActivity().findViewById(R.id.RightPaneHolder);
        mIsDualPane = detailsFrame != null
                && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mSelIndex = savedInstanceState.getInt("mSelIndex", 0);
        }
    }

    void showDetails(int index) {
        mSelIndex = index;

        Cursor cursor = ((SimpleCursorAdapter) getListAdapter()).getCursor();
        if (cursor == null || // in case of loader not finished yet
                !cursor.moveToPosition(index))
            return;

        int areaId = cursor.getInt(cursor.getColumnIndex(KhuVucDTO.CL_MA_KHU_VUC));
        String areaName = cursor.getString(cursor
                .getColumnIndex(KhuVucDTO.CL_TEN_KHU_VUC));

        if (mIsDualPane) {
            getListView().setItemChecked(index, true);
        }

        TableMapFragment f;
        if (mIsDualPane) {
            f = (TableMapFragment) getFragmentManager().findFragmentById(
                    R.id.RightPaneHolder);

            if (f == null || f.getMaKhuVuc() != areaId) {
                f = new TableMapFragment(areaId, areaName);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.RightPaneHolder, f);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            f = new TableMapFragment(areaId, areaName);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.LeftPaneHolder, f);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AreaListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        mAreaAdapter.swapCursor(arg1);
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAreaAdapter.swapCursor(null);
    }
}
