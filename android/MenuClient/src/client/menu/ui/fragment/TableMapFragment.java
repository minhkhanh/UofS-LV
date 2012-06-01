package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.DialogFragment;
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
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.bus.loader.TableListLoader;
import client.menu.db.dto.BanDTO;
import client.menu.ui.adapter.TableListAdapter;
import client.menu.util.U;

public class TableMapFragment extends TableInAreaFragment implements
        LoaderCallbacks<List<BanDTO>> {

    // private String mTenKhuVuc;
    // private Integer mMaKhuVuc;
    private TableListAdapter mTableAdapter;
    private GridView mTableGrid;
    private ActionMode mActionMode;

    private BanDTO mBan;

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

    public TableMapFragment(Integer areaId, String tenKhuVuc) {
        super(areaId, tenKhuVuc);
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

        mTableAdapter = new TableListAdapter(getActivity(), new ArrayList<BanDTO>());
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mActionMode != null) {
            mActionMode.finish();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().setBackgroundResource(R.color._55f5f5f5);

        mTableGrid = (GridView) getView().findViewById(R.id.gridTable);
        mTableGrid.setAdapter(mTableAdapter);
        mTableGrid.setOnItemClickListener(mOnItemClickListener);

        TextView areaName = (TextView) getView().findViewById(R.id.textAreaName);
        areaName.setText(mTenKhuVuc);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        ViewGroup frame = (ViewGroup) inflater.inflate(R.layout.frame_table_grid, null);

        return frame;
    }

    @Override
    public Loader<List<BanDTO>> onCreateLoader(int id, Bundle args) {
        return new TableListLoader(getActivity(), mMaKhuVuc);
    }

    @Override
    public void onLoadFinished(Loader<List<BanDTO>> arg0, List<BanDTO> arg1) {
        U.logOwnTag("table list finish : " + mTableAdapter.getCount());
        mTableAdapter.clear();
        mTableAdapter.addAll(arg1);
        mTableAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<BanDTO>> arg0) {
        U.logOwnTag("table list reset : " + mTableAdapter.getCount());
    }
}
