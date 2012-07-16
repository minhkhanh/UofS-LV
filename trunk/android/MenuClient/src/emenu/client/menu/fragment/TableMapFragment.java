package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import emenu.client.bus.loader.TableListLoader;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.GetMoveOrderTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.PostTableSelectionTask;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.dto.TableSelection;
import emenu.client.db.dto.TableSelection.SelectionState;
import emenu.client.menu.R;
import emenu.client.menu.activity.TableMapActivity;
import emenu.client.menu.adapter.TableListAdapter;
import emenu.client.util.U;

public class TableMapFragment extends Fragment implements LoaderCallbacks<List<BanDTO>>,
        OnItemClickListener {

    private String mTenKhuVuc;
    private Integer mAreaId;

    private TableListAdapter mGridAdapter;
    private GridView mTableGrid;

    private TableSelection mCurrTabSel;

    private PostTableSelectionTask mPostTabSelTask;

    private GetMoveOrderTask mMoveOrderTask;

    private ActionMode mBrowseTableMode;
    protected ActionMode mMoveOrderMode;

    protected OnPostExecuteListener<TableSelection.TableIdSelection, Void, Boolean> mOnPostSelectTable = new OnPostExecuteListener<TableSelection.TableIdSelection, Void, Boolean>() {
        @Override
        public void onPostExecute(
                CustomAsyncTask<TableSelection.TableIdSelection, Void, Boolean> task,
                Boolean result) {
            if (!result) {
                U.showErrorDialog(getActivity(), R.string.message_select_table_failed);
            } else {
                Integer groupId = task.getExtras().getInt("groupId");
                BriefOrderListDlgFragment dlg = new BriefOrderListDlgFragment(groupId);
                U.showDlgFragment(getFragmentManager(), dlg, false);

                getLoaderManager().restartLoader(0, null, TableMapFragment.this);
            }
        }
    };

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (mode == mBrowseTableMode)
                mBrowseTableMode = null;
            else if (mode == mMoveOrderMode) {
                mMoveOrderMode = null;
            }

            U.uncheckAllItems(mTableGrid);
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (getMovingOrderId() == -1) {
                mode.getMenuInflater().inflate(R.menu.context_table, menu);
            } else {
                mode.setSubtitle(R.string.text_select_des_table);
            }

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (mode == mBrowseTableMode) {
                switch (item.getItemId()) {
                    case R.id.miGroupTable:
                        postTableSelection();
                        break;

                    case R.id.miSelectTable:
                        postTableSelection();
                        break;

                    case R.id.miSplitTable:
                        SplitTableDlgFragment dlg = new SplitTableDlgFragment(mCurrTabSel
                                .getMainTab().getMaBan());
                        U.showDlgFragment(getFragmentManager(), dlg, false);
                        break;

                    default:
                        break;
                }

                mode.finish();
            }

            return true;
        }
    };

    protected OnPostExecuteListener<Void, Void, Boolean> mOnPostMoveOrder = new OnPostExecuteListener<Void, Void, Boolean>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task,
                Boolean result) {
            if (result) {
                U.toastText(getActivity(), R.string.message_move_order_succeed);
                mMoveOrderMode.finish();
                setMovingOrderId(-1);

                getLoaderManager().restartLoader(0, null, TableMapFragment.this);
            } else {
                U.toastText(getActivity(), R.string.message_move_order_failed);
            }
        }
    };

    public TableMapFragment() {
        mTenKhuVuc = "";
        mAreaId = 0;
    }

    public TableMapFragment(Integer areaId, String tenKhuVuc) {
        mAreaId = areaId;
        mTenKhuVuc = tenKhuVuc;
    }

    private Integer getMovingOrderId() {
        TableMapActivity host = (TableMapActivity) getActivity();
        if (host == null)
            return -1;

        return host.getMovingOrderId();
    }

    private void setMovingOrderId(Integer id) {
        TableMapActivity host = (TableMapActivity) getActivity();
        if (host != null)
            host.setMovingOrderId(id);
    }

    private void postTableSelection() {
        U.cancelAsyncTask(mPostTabSelTask);

        mPostTabSelTask = new PostTableSelectionTask();
        mPostTabSelTask.getExtras()
                .putInt("groupId", mCurrTabSel.getMainTab().getMaBan());
        mPostTabSelTask.setOnPostExecuteListener(mOnPostSelectTable);
        mPostTabSelTask.setWaitingDialog(U.createWaitingDialog(getActivity()));
        mPostTabSelTask.execute(mCurrTabSel.createIdSelection());
    }

    private TableSelection getTableSelection() {
        TableSelection c = new TableSelection();

        SparseBooleanArray chkArray = mTableGrid.getCheckedItemPositions();
        List<BanDTO> tableSelection = new ArrayList<BanDTO>();
        for (int i = 0; i < chkArray.size(); ++i) {
            if (chkArray.valueAt(i)) {
                int pos = chkArray.keyAt(i);
                BanDTO ban = mGridAdapter.getItem(pos);
                tableSelection.add(ban);
            }
        }

        c.setTabList(tableSelection);

        return c;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mMaKhuVuc", mAreaId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGridAdapter = new TableListAdapter(getActivity(), new ArrayList<BanDTO>());
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mBrowseTableMode != null) {
            mBrowseTableMode.finish();
        }
        if (mMoveOrderMode != null) {
            mMoveOrderMode.finish();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().setBackgroundResource(R.color._55f5f5f5);

        setHasOptionsMenu(true);

        mTableGrid = (GridView) getView().findViewById(R.id.gridTable);
        mTableGrid.setAdapter(mGridAdapter);
        mTableGrid.setOnItemClickListener(this);
        mTableGrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);

        TextView areaName = (TextView) getView().findViewById(R.id.textAreaName);
        areaName.setText(mTenKhuVuc);

        if (getMovingOrderId() != -1)
            mMoveOrderMode = getActivity().startActionMode(mActionModeCallback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frame_table_grid, null);
    }

    @Override
    public Loader<List<BanDTO>> onCreateLoader(int id, Bundle args) {
        return new TableListLoader(getActivity(), mAreaId);
    }

    @Override
    public void onLoadFinished(Loader<List<BanDTO>> arg0, List<BanDTO> arg1) {
        mGridAdapter.clear();
        mGridAdapter.addAll(arg1);
        mGridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<BanDTO>> arg0) {
        mGridAdapter.clear();
        mGridAdapter.notifyDataSetChanged();
    }

    public int getMaKhuVuc() {
        return mAreaId;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        BanDTO clickTable = mGridAdapter.getItem(pos);

        if (getMovingOrderId() == -1) {
            if (mCurrTabSel != null) {
                switch (mCurrTabSel.getState()) {
                    case SingleFree:
                    case SingleBusy:
                    case GroupBusy:
                        if (mCurrTabSel.findTableById(clickTable.getMaBan()) != null) {
                            postTableSelection();
                            if (mBrowseTableMode != null)
                                mBrowseTableMode.finish();
                            return;
                        }
                        break;

                    default:
                        break;
                }
            }

            if (!clickTable.getActive()) {
                for (int i = 0; i < mTableGrid.getCount(); ++i) {
                    BanDTO ban = mGridAdapter.getItem(i);
                    if (ban.getMaBanChinh() == clickTable.getMaBanChinh()) {
                        boolean flag = mTableGrid.isItemChecked(pos);
                        mTableGrid.setItemChecked(i, flag);
                    } else if (!ban.getActive()) {
                        mTableGrid.setItemChecked(i, false);
                    }
                }
            }

            int checkedCount = mTableGrid.getCheckedItemCount();
            // end selecting
            if (checkedCount == 0) {
                mBrowseTableMode.finish();
            } else {
                // start selecting
                if (mBrowseTableMode == null)
                    mBrowseTableMode = getActivity().startActionMode(mActionModeCallback);

                // selecting
                mCurrTabSel = getTableSelection();
                SelectionState currState = mCurrTabSel.getState();
                Menu menu = mBrowseTableMode.getMenu();
                switch (currState) {
                    case SingleFree:
                    case SingleBusy:
                        menu.findItem(R.id.miSelectTable).setEnabled(true);
                        menu.findItem(R.id.miGroupTable).setEnabled(false);
                        menu.findItem(R.id.miSplitTable).setEnabled(false);
                        break;
                    case Mixed:
                    case MultiFree:
                        menu.findItem(R.id.miSelectTable).setEnabled(true);
                        menu.findItem(R.id.miGroupTable).setEnabled(true);
                        menu.findItem(R.id.miSplitTable).setEnabled(false);
                        break;
                    case GroupBusy:
                        menu.findItem(R.id.miSelectTable).setEnabled(true);
                        menu.findItem(R.id.miGroupTable).setEnabled(false);
                        menu.findItem(R.id.miSplitTable).setEnabled(true);
                        break;
                }
            }
        } else {
            U.cancelAsyncTask(mMoveOrderTask);
            mMoveOrderTask = new GetMoveOrderTask(getMovingOrderId(),
                    clickTable.getMaBan());
            mMoveOrderTask.setOnPostExecuteListener(mOnPostMoveOrder).execute();
        }
    }
}
