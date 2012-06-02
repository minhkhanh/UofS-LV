package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.DialogInterface;
import android.content.Intent;
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
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.bus.task.PostTableSplitingTask;
import client.menu.db.dto.BanDTO;
import client.menu.ui.activity.MainMenuActivity;
import client.menu.ui.adapter.TableListAdapter;
import client.menu.util.U;

public class TableMapFragment extends TableInAreaFragment implements
        LoaderCallbacks<List<BanDTO>>,
        OnPostExecuteAsyncTaskListener<Void, Void, Boolean> {

    private TableListAdapter mTableAdapter;
    private GridView mTableGrid;
    private ActionMode mActionMode;

    // private Integer mMaBanChinh;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            
            U.uncheckAllItems(mTableGrid);
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_table, menu);

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int pos = mTableGrid.getCheckedItemPosition();
            BanDTO ban = mTableAdapter.getItem(pos);

            switch (item.getItemId()) {
                case R.id.miSelectTable:
                    Integer maBan = ban.getMaBan();
                    SessionManager.getInstance().loadSession(maBan);

                    Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                    startActivity(intent);
                    break;

                case R.id.miSplitTable:
                    Integer maBanChinh = ban.getMaBanChinh();
                    if (maBanChinh != null) {
                        PostTableSplitingTask task = new PostTableSplitingTask(
                                getActivity(), 0, maBanChinh);
                        task.setOnPostExecuteListener(TableMapFragment.this);
                        task.execute();
                    } else {
                        U.toastText(getActivity(),
                                R.string.message_single_table_spliting_failed);
                    }
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
        mTableGrid.setChoiceMode(GridView.CHOICE_MODE_SINGLE);

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
        mTableAdapter.clear();
        mTableAdapter.addAll(arg1);
        mTableAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<BanDTO>> arg0) {
    }

    @Override
    public void onPostExecuteAsyncTask(CustomAsyncTask<Void, Void, Boolean> task,
            Boolean result) {
        if (result) {
            getLoaderManager().restartLoader(0, null, this);
        } else {
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.message_table_spliting_failed)
                    .setNeutralButton(R.string.caption_ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).create().show();
        }
    }
}
