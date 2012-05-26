package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.bus.loader.CustomAsyncTaskLoader;
import client.menu.db.dao.BanDAO;
import client.menu.db.dto.BanDTO;
import client.menu.ui.adapter.TableListAdapter;
import client.menu.util.U;

public class TableGridFragment extends Fragment implements LoaderCallbacks<List<BanDTO>> {

    private String mTenKhuVuc;
    private Integer mMaKhuVuc;
    private TableListAdapter mTableAdapter;
    private GridView mTableGrid;
    private ActionMode mActionMode;

    private BanDTO mBan;

    static class TableListLoader extends CustomAsyncTaskLoader<List<BanDTO>> {

        private Integer mAreaId;

        public TableListLoader(Activity context, Integer maKhuVuc) {
            super(context);
            mAreaId = maKhuVuc;
        }

        @Override
        public List<BanDTO> loadInBackground() {
            return BanDAO.getInstance().getByKhuVuc(mAreaId);
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
            switch (item.getItemId()) {
                case R.id.miSelectTable:

                    SessionManager.getInstance().loadSession(mBan);

                    DialogFragment newFragment = new AuthDialogFragment(
                            AuthDialogFragment.ACT_SELECTING_TABLE);
                    U.showDlgFragment(getActivity(), newFragment, "dialog");

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

                mBan = (BanDTO) parent.getItemAtPosition(pos);

                mActionMode = getActivity().startActionMode(mActionModeCallback);
            }
        }
    };

    public int getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(int maKhuVuc) {
        mMaKhuVuc = maKhuVuc;
    }

    public static TableGridFragment newInstance(int areaId, String areaName) {
        TableGridFragment f = new TableGridFragment();
        f.mMaKhuVuc = areaId;
        f.mTenKhuVuc = areaName;

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

        mTableAdapter = new TableListAdapter(getActivity(), new ArrayList<BanDTO>());

//        getLoaderManager().initLoader(0, null, this);
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        getView().setBackgroundResource(R.color._55f5f5f5);

        mTableGrid = (GridView) getView().findViewById(R.id.TableGrid);
        mTableGrid.setAdapter(mTableAdapter);
        mTableGrid.setOnItemClickListener(mOnItemClickListener);
        
        TextView areaName = (TextView) getView().findViewById(R.id.textAreaName);
        areaName.setText(mTenKhuVuc);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        ViewGroup frame = (ViewGroup) inflater.inflate(R.layout.frame_table_grid, null);
//        GridView grid = (GridView) U.extractViewFromParent(frame, R.id.TableGrid);

        return frame;
    }

    @Override
    public Loader<List<BanDTO>> onCreateLoader(int id, Bundle args) {
        return new TableListLoader(getActivity(), mMaKhuVuc);
    }

    @Override
    public void onLoadFinished(Loader<List<BanDTO>> arg0, List<BanDTO> arg1) {
        mTableAdapter.addAll(arg1);
        mTableAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<BanDTO>> arg0) {
    }
}
