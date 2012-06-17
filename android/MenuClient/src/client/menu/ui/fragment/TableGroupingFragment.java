package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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
import client.menu.bus.task.PostTableSelectionTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.YeuCauGhepBan;
import client.menu.ui.activity.MainMenuActivity;
import client.menu.ui.adapter.TableListAdapter;
import client.menu.util.U;

public class TableGroupingFragment extends TableInAreaFragment implements
        LoaderCallbacks<List<BanDTO>>, OnItemClickListener,
        OnPostExecuteListener<Void, Integer, Integer> {
    private GridView mTableGrid;
    private TableListAdapter mTableAdapter;

    private ActionMode mActionMode;

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
            inflater.inflate(R.menu.context_table_grouping, menu);

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.miGroupTable:
                    SparseBooleanArray chkArray = mTableGrid.getCheckedItemPositions();
                    List<Integer> listBan = new ArrayList<Integer>();
                    for (int i = 0; i < chkArray.size(); ++i) {
                        if (chkArray.valueAt(i)) {
                            int pos = chkArray.keyAt(i);
                            BanDTO ban = mTableAdapter.getItem(pos);
                            listBan.add(ban.getMaBan());
                        }
                    }

                    if (listBan.size() >= 2) {
                        YeuCauGhepBan yc = new YeuCauGhepBan();
                        yc.setMaBanChinh(listBan.get(0));
                        listBan.remove(0);
                        yc.setMaBanPhuList(listBan);

//                        PostTableGroupingTask task = new PostTableGroupingTask(
//                                getActivity(), yc);
//                        task.setOnPostExecuteListener(TableGroupingFragment.this);
//                        task.execute();
                    }

                    break;

                default:
                    break;
            }
            mode.finish();
            return true;
        }
    };

    public TableGroupingFragment(int areaId, String areaName) {
        super(areaId, areaName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup frame = (ViewGroup) inflater.inflate(R.layout.frame_table_grid, null);

        return frame;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().setBackgroundResource(R.color._55f5f5f5);

        mTableAdapter = new TableListAdapter(getActivity(), new ArrayList<BanDTO>());

        mTableGrid = (GridView) getView().findViewById(R.id.gridTable);
        mTableGrid.setAdapter(mTableAdapter);
        mTableGrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        mTableGrid.setOnItemClickListener(this);

        TextView areaName = (TextView) getView().findViewById(R.id.textAreaName);
        areaName.setText(mTenKhuVuc);
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
    public Loader<List<BanDTO>> onCreateLoader(int id, Bundle args) {
        return new TableListLoader(getActivity(), mAreaId);
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
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        BanDTO ban = mTableAdapter.getItem(arg2);
        if (!ban.getActive()) {
            mTableGrid.setItemChecked(arg2, false);
            U.toastText(getActivity(), R.string.message_busy_table_grouping_failed);
        } else {
            if (mActionMode == null && mTableGrid.getCheckedItemCount() == 1) {
                mActionMode = getActivity().startActionMode(mActionModeCallback);
            }

            if (mActionMode != null && mTableGrid.getCheckedItemCount() == 0) {
                mActionMode.finish();
            }
        }
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Void, Integer, Integer> task,
            Integer result) {

        if (result != -1) {
            // getLoaderManager().restartLoader(0, null, this);

            SessionManager.getInstance().loadSession(result);
            Intent intent = new Intent(getActivity(), MainMenuActivity.class);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.message_table_grouping_failed)
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
