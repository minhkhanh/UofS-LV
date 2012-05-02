package client.menu.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import client.menu.R;
import client.menu.db.contract.BanContract;
import client.menu.util.C;
import client.menu.util.Utilitiy;

public class TableGridFragment extends Fragment implements LoaderCallbacks<Cursor>,
        OnItemClickListener {

    public static final int LOADER_ID_TABLE_LIST = 1;

    int mAreaId;

    public int getAreaId() {
        return mAreaId;
    }

    public void setAreaId(int areaId) {
        mAreaId = areaId;
    }

    SimpleCursorAdapter adapter;
    GridView mTableGrid;

    public static TableGridFragment newInstance(int areaId) {
        TableGridFragment f = new TableGridFragment();
        f.mAreaId = areaId;

        return f;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mAreaId", mAreaId);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] from = new String[] { BanContract.COL_TABLE_NAME };
        int[] to = new int[] { R.id.TableCaption };
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.item_table_grid, null,
                from, to, 0);

        mTableGrid = (GridView) getActivity().findViewById(R.id.TableGrid);
        mTableGrid.setAdapter(adapter);
        mTableGrid.setOnItemClickListener(this);

        getLoaderManager().initLoader(LOADER_ID_TABLE_LIST, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        ViewGroup frame = (ViewGroup) inflater.inflate(R.layout.frame_table_grid, null);
        GridView grid = (GridView) Utilitiy.extractViewFromParent(frame, R.id.TableGrid);

        return grid;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ID_TABLE_LIST:
                String[] proj = new String[] { BanContract._ID, BanContract.COL_SID,
                        BanContract.COL_TABLE_NAME };
                CursorLoader loader = new CursorLoader(getActivity(),
                        BanContract.CONTENT_URI, proj, BanContract.COL_AREA_ID + " = ?",
                        new String[] { String.valueOf(mAreaId) }, null);

                return loader;
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID_TABLE_LIST:
                adapter.swapCursor(cursor);
                Log.d(C.TAG, "onLoadFinished : adapter.swapCursor(arg1);");
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case LOADER_ID_TABLE_LIST:
                adapter.swapCursor(null);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
        if (parent == mTableGrid) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dlg");
            if (prev != null) {
                ft.remove(prev);
            }

            DialogFragment newFragment = TableDialogFragment.newInstance();
            newFragment.show(ft, "dlg");
        }
    }
}
