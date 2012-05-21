package client.menu.ui.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import client.menu.R;
import client.menu.app.MyAppRepository;
import client.menu.db.contract.BanContract;
import client.menu.db.dao.BanDAO;
import client.menu.db.dto.BanDTO;
import client.menu.util.C;
import client.menu.util.U;

public class TableGridFragment extends Fragment {

    // public static final int LOADER_ID_TABLE_LIST = 0;

    private Integer mMaKhuVuc;

    private SimpleCursorAdapter mGridAdapter;
    private GridView mTableGrid;
    private ActionMode mActionMode;

    LoadTableListTask mLoadTableListTask;

    class LoadTableListTask extends AsyncTask<Integer, Integer, Cursor> {

        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);
            mGridAdapter.changeCursor(result);
        }

        @Override
        protected Cursor doInBackground(Integer... maKhuVuc) {
            return BanDAO.getInstance().cursorByMaKhuVuc(maKhuVuc[0]);
        }

    };

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_table, menu);

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            // Toast.makeText(getActivity(), "Đang xây dựng",
            // Toast.LENGTH_SHORT).show();
            switch (item.getItemId()) {
                case R.id.itemOrder:

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);

                    // Create and show the dialog.
                    DialogFragment newFragment = AuthDialogFragment.newInstance();
                    newFragment.show(ft, "dialog");
                    break;

                default:
                    break;
            }
            mode.finish();
            return true;
        }
    };

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            if (parent == mTableGrid) {
                if (mActionMode != null) {
                    return;
                }

                mActionMode = getActivity().startActionMode(mActionModeCallback);
            }
        }
    };

    // private LoaderCallbacks<Cursor> mLoaderCallbacks = new
    // LoaderCallbacks<Cursor>() {
    //
    // @Override
    // public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    // switch (id) {
    // case LOADER_ID_TABLE_LIST:
    // String[] proj = new String[] { BanContract.CL_ID, BanContract.CL_SID,
    // BanContract.CL_TEN_BAN };
    // CursorLoader loader = new CursorLoader(getActivity(),
    // BanContract.CONTENT_URI, proj, BanContract.CL_MA_KHU_VUC
    // + " = ?", new String[] { String.valueOf(mMaKhuVuc) },
    // null);
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
    // case LOADER_ID_TABLE_LIST:
    // mAdapter.swapCursor(cursor);
    // Log.d(C.TAG, "onLoadFinished : adapter.swapCursor(arg1);");
    // break;
    // }
    // }
    //
    // @Override
    // public void onLoaderReset(Loader<Cursor> loader) {
    // switch (loader.getId()) {
    // case LOADER_ID_TABLE_LIST:
    // mAdapter.swapCursor(null);
    // break;
    // }
    // }
    // };

    public int getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(int maKhuVuc) {
        mMaKhuVuc = maKhuVuc;
    }

    public static TableGridFragment newInstance(int areaId) {
        TableGridFragment f = new TableGridFragment();
        f.mMaKhuVuc = areaId;

        return f;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.options_table, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.OptItemMergeTable:
                Toast.makeText(getActivity(), "Đang xây dựng", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mLoadTableListTask = new LoadTableListTask();
        mLoadTableListTask.execute(mMaKhuVuc);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mLoadTableListTask.getStatus() != AsyncTask.Status.FINISHED) {
            mLoadTableListTask.cancel(true);
        } else {
            mGridAdapter.changeCursor(null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mMaKhuVuc", mMaKhuVuc);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mMaKhuVuc = savedInstanceState.getInt("mMaKhuVuc");
        }

        setHasOptionsMenu(true);

        String[] from = new String[] { BanDTO.CL_TEN_BAN };
        int[] to = new int[] { R.id.TableCaption };
        mGridAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item_table_grid,
                null, from, to, 0);

        // getLoaderManager().initLoader(LOADER_ID_TABLE_LIST, null,
        // mLoaderCallbacks);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // getActivity().getActionBar().setSubtitle(R.string.construct_merge_table);

        mTableGrid = (GridView) getView().findViewById(R.id.TableGrid);
        mTableGrid.setAdapter(mGridAdapter);
        mTableGrid.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        ViewGroup frame = (ViewGroup) inflater.inflate(R.layout.frame_table_grid, null);
        GridView grid = (GridView) U.extractViewFromParent(frame, R.id.TableGrid);

        return grid;
    }
}
