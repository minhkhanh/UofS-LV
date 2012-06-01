package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.DialogInterface;
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
import client.menu.bus.loader.TableListLoader;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.PostTableMergingTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.YeuCauGhepBan;
import client.menu.ui.adapter.TableListAdapter;

public class TableMergingFragment extends TableInAreaFragment implements
        LoaderCallbacks<List<BanDTO>>, OnItemClickListener,
        OnPostExecuteAsyncTaskListener<YeuCauGhepBan, Integer, Boolean> {
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
            mTableGrid.setSelection(-1);
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_table_merging, menu);

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.miMerge:
                    SparseBooleanArray boolArray = mTableGrid.getCheckedItemPositions();
                    List<Integer> listBanPhu = new ArrayList<Integer>();
                    for (int i = 0; i < boolArray.size(); ++i) {
                        if (boolArray.valueAt(i)) {
                            int pos = boolArray.keyAt(i);
                            BanDTO ban = mTableAdapter.getItem(pos);
                            listBanPhu.add(ban.getMaBan());
                        }
                    }
                    YeuCauGhepBan yc = new YeuCauGhepBan();
                    yc.setMaBanChinh(listBanPhu.get(0));
                    yc.setMaBanPhuList(listBanPhu);

                    PostTableMergingTask task = new PostTableMergingTask(getActivity(), 0);
                    task.setOnPostExecuteListener(TableMergingFragment.this);
                    task.execute(yc);

                    break;

                default:
                    break;
            }
            mode.finish();
            return true;
        }
    };

    public TableMergingFragment(int areaId, String areaName) {
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
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        BanDTO ban = mTableAdapter.getItem(arg2);
        if (!ban.getActive()) {
            mTableGrid.setItemChecked(arg2, false);
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.message_select_table_merging_failed)
                    .setNeutralButton(R.string.caption_ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).create().show();
        } else {
            if (mActionMode == null && mTableGrid.getCheckedItemCount() == 2) {
                mActionMode = getActivity().startActionMode(mActionModeCallback);
            }

            if (mActionMode != null && mTableGrid.getCheckedItemCount() == 1) {
                mActionMode.finish();
            }
        }
    }

    @Override
    public void onPostExecuteAsyncTask(
            CustomAsyncTask<YeuCauGhepBan, Integer, Boolean> task, Boolean result) {
        getLoaderManager().restartLoader(0, null, this);
    }
}
