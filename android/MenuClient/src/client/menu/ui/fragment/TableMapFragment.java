package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.bus.loader.TableListLoader;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.GetTableGroupsTask;
import client.menu.bus.task.PostTableMergingTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.bus.task.PostTableSplitingTask;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.YeuCauGhepBan;
import client.menu.ui.activity.MainMenuActivity;
import client.menu.ui.adapter.TableGroupsAdapter;
import client.menu.ui.adapter.TableListAdapter;
import client.menu.util.U;

public class TableMapFragment extends TableInAreaFragment implements
        LoaderCallbacks<List<BanDTO>>,
        OnPostExecuteAsyncTaskListener<Void, Void, Boolean> {

    private boolean mGroupingMode = false;
    private TableListAdapter mGridAdapter;
    private GridView mTableGrid;
    private ActionMode mActionMode;

    private OnPostExecuteAsyncTaskListener<Void, Integer, Integer> mOnPostExecuteTableGroupingTask = new OnPostExecuteAsyncTaskListener<Void, Integer, Integer>() {
        @Override
        public void onPostExecuteAsyncTask(CustomAsyncTask<Void, Integer, Integer> task,
                Integer result) {
        }
    };

    protected OnPostExecuteAsyncTaskListener<Void, Integer, List<JSONObject>> mOnPostExecuteGetTableGroup = new OnPostExecuteAsyncTaskListener<Void, Integer, List<JSONObject>>() {
        @Override
        public void onPostExecuteAsyncTask(
                CustomAsyncTask<Void, Integer, List<JSONObject>> task,
                List<JSONObject> result) {
            TableGroupsAdapter adapter = new TableGroupsAdapter(getActivity(), result);
            // AlertDialog.Builder builder =
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.caption_select_table_group)
                    .setSingleChoiceItems(adapter, -1, new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();
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

            U.uncheckAllItems(mTableGrid);
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();

            if (mGroupingMode) {
                inflater.inflate(R.menu.context_table_grouping, menu);
            } else {
                inflater.inflate(R.menu.context_table, menu);
            }

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (mGroupingMode) {
                switch (item.getItemId()) {
                    case R.id.miGroup:
                        SparseBooleanArray chkArray = mTableGrid
                                .getCheckedItemPositions();
                        List<BanDTO> listBan = new ArrayList<BanDTO>();
                        for (int i = 0; i < chkArray.size(); ++i) {
                            if (chkArray.valueAt(i)) {
                                int pos = chkArray.keyAt(i);
                                BanDTO ban = mGridAdapter.getItem(pos);
                                listBan.add(ban);
                            }
                        }

                        if (listBan.size() >= 1) {
                            GetTableGroupsTask task = new GetTableGroupsTask(
                                    getActivity());
                            task.setOnPostExecuteListener(mOnPostExecuteGetTableGroup);
                            task.execute();
                            // DialogFragment dlg = new
                            // TableGroupingOptionsDlgFragment(
                            // listBan);
                            // U.showDlgFragment(getActivity(), dlg, "dlg");

                            // PostTableMergingTask task = new
                            // PostTableMergingTask(
                            // getActivity(), yc);
                            // task.setOnPostExecuteListener(mOnPostExecuteTableGroupingTask);
                            // task.execute();
                        }

                        break;

                    default:
                        break;
                }
            } else {
                int checkedPos = mTableGrid.getCheckedItemPosition();
                BanDTO ban = mGridAdapter.getItem(checkedPos);

                switch (item.getItemId()) {
                    case R.id.miSelectTable:
                        Integer maBan = ban.getMaBanChinh();
                        if (maBan == null) {
                            maBan = ban.getMaBan();
                        }
                        SessionManager.getInstance().loadSession(maBan);

                        ServingOrderListDlgFragment f = new ServingOrderListDlgFragment(
                                ban);
                        U.showDlgFragment(getActivity(), f, "dlg");

                        break;

                    case R.id.miSplitTable:
                        Integer maBanChinh = ban.getMaBanChinh();
                        if (maBanChinh != null) {
                            PostTableSplitingTask task = new PostTableSplitingTask(
                                    getActivity(), maBanChinh);
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
            }

            mode.finish();
            return true;
        }
    };

    private OnItemClickListener mOnItemClick = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            if (mActionMode != null) {
                return;
            }

            mActionMode = getActivity().startActionMode(mActionModeCallback);
        }
    };

    public TableMapFragment(Integer areaId, String tenKhuVuc) {
        super(areaId, tenKhuVuc);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.options_table, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miGroupTable:
                mGroupingMode = !mGroupingMode;
                if (mGroupingMode) {
                    item.setTitle(R.string.option_back);
                    mTableGrid.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    mActionMode = getActivity().startActionMode(mActionModeCallback);
                } else {
                    mTableGrid.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                    item.setTitle(R.string.option_group_table);
                    mActionMode.finish();
                }

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

        mGridAdapter = new TableListAdapter(getActivity(), new ArrayList<BanDTO>());
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

        setHasOptionsMenu(true);

        mTableGrid = (GridView) getView().findViewById(R.id.gridTable);
        mTableGrid.setAdapter(mGridAdapter);
        mTableGrid.setOnItemClickListener(mOnItemClick);
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
        mGridAdapter.clear();
        mGridAdapter.addAll(arg1);
        mGridAdapter.notifyDataSetChanged();
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
