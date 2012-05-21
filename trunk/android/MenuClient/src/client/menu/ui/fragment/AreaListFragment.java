package client.menu.ui.fragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import client.menu.R;
import client.menu.app.MyAppRepository;
import client.menu.db.contract.KhuVucContract;
import client.menu.db.dao.KhuVucDAO;
import client.menu.db.dto.KhuVucDTO;

public class AreaListFragment extends ListFragment {

    public static final int LOADER_ID_AREA_LIST = 0;

    public static final int MSG_LOADER_FINISHED_AREA_LIST = 0;

    private boolean mIsDualPane;
    private int mSelIndex;

    private SimpleCursorAdapter mAdapter;

    LoadAreaListTask mLoadAreaListTask;

    class LoadAreaListTask extends AsyncTask<Void, Integer, Cursor> {

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
            return KhuVucDAO.getInstance().cursorAll();
        }

    };

    // private Handler handler = new Handler() {
    // public void handleMessage(Message msg) {
    // if (msg.what == MSG_LOADER_FINISHED_AREA_LIST) {
    // if (mIsDualPane) {
    // getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    // showDetails(mSelIndex);
    // }
    // }
    // };
    // };

    // private LoaderCallbacks<Cursor> mLoaderCallbacks = new
    // LoaderCallbacks<Cursor>() {
    //
    // @Override
    // public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    // switch (id) {
    // case LOADER_ID_AREA_LIST:
    // String[] proj = new String[] { KhuVucContract.CL_ID,
    // KhuVucContract.CL_MA_KHU_VUC, KhuVucContract.CL_TEN_KHU_VUC };
    // CursorLoader loader = new CursorLoader(getActivity(),
    // KhuVucContract.CONTENT_URI, proj, null, null, null);
    //
    // return loader;
    // }
    //
    // return null;
    // }
    //
    // @Override
    // public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
    // switch (loader.getId()) {
    // case LOADER_ID_AREA_LIST:
    // mAdapter.swapCursor(cursor);
    // handler.sendEmptyMessage(MSG_LOADER_FINISHED_AREA_LIST);
    //
    // break;
    // }
    //
    // }
    //
    // @Override
    // public void onLoaderReset(Loader<Cursor> arg0) {
    // switch (arg0.getId()) {
    // case LOADER_ID_AREA_LIST:
    // mAdapter.swapCursor(null);
    // break;
    // }
    // }
    // };

    @Override
    public void onPause() {
        super.onPause();

        if (mLoadAreaListTask.getStatus() != AsyncTask.Status.FINISHED) {
            mLoadAreaListTask.cancel(true);
        } else {
            mAdapter.changeCursor(null);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mLoadAreaListTask = new LoadAreaListTask();
        mLoadAreaListTask.execute();
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

        String[] from = new String[] { KhuVucDTO.CL_TEN_KHU_VUC };
        int[] to = new int[] { android.R.id.text1 };

        mAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_activated_1, null, from, to, 0);
        setListAdapter(mAdapter);

        // getLoaderManager().initLoader(LOADER_ID_AREA_LIST, null,
        // mLoaderCallbacks);

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

        if (mIsDualPane) {
            getListView().setItemChecked(index, true);
            // getListView().set

            TableGridFragment tableGrid = (TableGridFragment) getFragmentManager()
                    .findFragmentById(R.id.RightPaneHolder);

            if (tableGrid == null || tableGrid.getMaKhuVuc() != areaId) {
                tableGrid = TableGridFragment.newInstance(areaId);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.RightPaneHolder, tableGrid);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            TableGridFragment tableGrid = TableGridFragment.newInstance(areaId);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.LeftPaneHolder, tableGrid);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }
}
