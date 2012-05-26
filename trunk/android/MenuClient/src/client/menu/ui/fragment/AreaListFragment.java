package client.menu.ui.fragment;

import android.app.Activity;
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
import client.menu.bus.loader.CustomAsyncTaskLoader;
import client.menu.db.dao.KhuVucDAO;
import client.menu.db.dto.KhuVucDTO;
import client.menu.util.U;

public class AreaListFragment extends ListFragment implements LoaderCallbacks<Cursor> {

    public static final int MSG_LOADER_FINISHED_AREA_LIST = 0;

    private boolean mIsDualPane;
    private int mSelIndex;

    private SimpleCursorAdapter mAreaAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mIsDualPane) {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                showDetails(mSelIndex);
            }
        }
    };

    static class AreaListLoader extends CustomAsyncTaskLoader<Cursor> {

        public AreaListLoader(Activity context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            return KhuVucDAO.getInstance().cursorAll();
        }
    };

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

//        getView().setBackgroundResource(R.color._99f5f5f5);
        getView().setBackgroundResource(R.drawable.wood_white_oak);
//        getView().set

        String[] from = new String[] { KhuVucDTO.CL_TEN_KHU_VUC };
        int[] to = new int[] { R.id.textAreaName };
        mAreaAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.item_area_list, null, from, to, 0);
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
        String areaName = cursor.getString(cursor.getColumnIndex(KhuVucDTO.CL_TEN_KHU_VUC));

        if (mIsDualPane) {
            getListView().setItemChecked(index, true);
            // getListView().set

            TableGridFragment tableGrid = (TableGridFragment) getFragmentManager()
                    .findFragmentById(R.id.RightPaneHolder);

            if (tableGrid == null || tableGrid.getMaKhuVuc() != areaId) {
                tableGrid = TableGridFragment.newInstance(areaId, areaName);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.RightPaneHolder, tableGrid);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            TableGridFragment tableGrid = TableGridFragment.newInstance(areaId, areaName);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.LeftPaneHolder, tableGrid);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        U.logOwnTag("load create");
        return new AreaListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        mAreaAdapter.swapCursor(arg1);
        mHandler.sendEmptyMessage(0);
        
        U.logOwnTag("load finish");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAreaAdapter.swapCursor(null);
        
        U.logOwnTag("load reset");
    }
}
