package client.menu.fragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import client.menu.R;
import client.menu.db.contract.KhuVucContract;

public class AreaListFragment extends ListFragment implements LoaderCallbacks<Cursor> {
    public static final int LOADER_ID_AREA_LIST = 0;

    boolean mIsDualPane;
    int mSelIndex;

    SimpleCursorAdapter adapter;

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

        String[] from = new String[] { KhuVucContract.COL_AREA_NAME };
        int[] to = new int[] { android.R.id.text1 };

        adapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_activated_1, null, from, to, 0);
        setListAdapter(adapter);
        
        getLoaderManager().initLoader(LOADER_ID_AREA_LIST, null, this);

        View detailsFrame = getActivity().findViewById(R.id.RightPaneHolder);
        mIsDualPane = detailsFrame != null
                && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mSelIndex = savedInstanceState.getInt("mSelIndex", 0);
        }

        if (mIsDualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mSelIndex);
        }

//        Log.d(C.TAG, "AreaListFragment.onActivityCreated");
    }

    void showDetails(int index) {
        mSelIndex = index;

        // Object obj = getListAdapter().getItem(index);

        Cursor cursor = ((SimpleCursorAdapter) getListAdapter()).getCursor();
        if (!cursor.moveToPosition(index))
            return;

        int areaId = cursor.getInt(cursor.getColumnIndex(KhuVucContract.COL_SID));

        if (mIsDualPane) {
            getListView().setItemChecked(index, true);

            TableGridFragment tableGrid = (TableGridFragment) getFragmentManager()
                    .findFragmentById(R.id.RightPaneHolder);

            if (tableGrid == null || tableGrid.getAreaId() != areaId) {
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ID_AREA_LIST:
                String[] proj = new String[] { KhuVucContract._ID,
                        KhuVucContract.COL_SID, KhuVucContract.COL_AREA_NAME };
                CursorLoader loader = new CursorLoader(getActivity(),
                        KhuVucContract.CONTENT_URI, proj, null, null, null);

                return loader;
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        switch (arg0.getId()) {
            case LOADER_ID_AREA_LIST:
                adapter.swapCursor(arg1);
                break;
        }
        
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        switch (arg0.getId()) {
            case LOADER_ID_AREA_LIST:
                adapter.swapCursor(null);
                break;
        }
    }
}
