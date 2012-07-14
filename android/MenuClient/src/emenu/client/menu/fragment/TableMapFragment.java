package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;

import android.app.Fragment;
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
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.PostTableSelectionTask;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.dto.TableSelection;
import emenu.client.db.dto.TableSelection.SelectionState;
import emenu.client.menu.R;
import emenu.client.menu.adapter.TableListAdapter;
import emenu.client.menu.fragment.AuthDlgFragment.OnAuthDlgDismissedListener;
import emenu.client.util.U;

public class TableMapFragment extends Fragment implements LoaderCallbacks<List<BanDTO>>,
        OnPostExecuteListener<List<Integer>, Void, Boolean>, OnAuthDlgDismissedListener {

    public static final int ACT_GROUP_TABLE = 0;
    public static final int ACT_SPLIT_TABLE = 1;

    private String mTenKhuVuc;
    private Integer mAreaId;

    private TableListAdapter mGridAdapter;
    private GridView mTableGrid;
    private ActionMode mActionMode;

    private TableSelection mCurrTabSel;

    private PostTableSelectionTask mPostTabSelTask;
    private OnTableClickedListener mTableClickedListener;

    public interface OnTableClickedListener {
        boolean isInOrderMovingMode();

        void onTableClicked(BanDTO table);
    }

    protected OnPostExecuteListener<TableSelection.TableIdSelection, Void, Boolean> mOnPostExecuteTableSelecting = new OnPostExecuteListener<TableSelection.TableIdSelection, Void, Boolean>() {
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
            mActionMode = null;

            U.uncheckAllItems(mTableGrid);
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context_table, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.miGroupTable:
                    U.showAuthDlg(TableMapFragment.this, getFragmentManager(),
                            ACT_GROUP_TABLE, null);
                    break;

                case R.id.miSelectTable:
                    postTableSelection(null);

                    break;

                case R.id.miSplitTable:
                    U.showAuthDlg(TableMapFragment.this, getFragmentManager(),
                            ACT_SPLIT_TABLE, null);
                    break;

                default:
                    break;
            }

            mode.finish();
            return true;
        }
    };

    private OnItemClickListener mOnItemClick = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            BanDTO clickTable = mGridAdapter.getItem(pos);

            if (mTableClickedListener != null)
                mTableClickedListener.onTableClicked(clickTable);

            if (mTableClickedListener == null
                    || !mTableClickedListener.isInOrderMovingMode()) {
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
                if (checkedCount == 0) { // end selecting
                    mActionMode.finish();
                } else {
                    if (mActionMode == null) // start selecting
                        mActionMode = getActivity().startActionMode(mActionModeCallback);

                    // selecting
                    mCurrTabSel = getTableSelection();
                    SelectionState currState = mCurrTabSel.getState();
                    Menu menu = mActionMode.getMenu();
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
            }
        }
    };

    public TableMapFragment() {
        mTenKhuVuc = "";
        mAreaId = 0;
    }

    private void postTableSelection(HttpClient client) {
        U.cancelAsyncTask(mPostTabSelTask);

        mPostTabSelTask = new PostTableSelectionTask(client);
        mPostTabSelTask.getExtras()
                .putInt("groupId", mCurrTabSel.getMainTab().getMaBan());
        mPostTabSelTask.setOnPostExecuteListener(mOnPostExecuteTableSelecting);
        mPostTabSelTask.execute(mCurrTabSel.createIdSelection());
    }

    public TableMapFragment(OnTableClickedListener onTableClickedListener,
            Integer areaId, String tenKhuVuc) {
        mAreaId = areaId;
        mTenKhuVuc = tenKhuVuc;

        mTableClickedListener = onTableClickedListener;
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
        mTableGrid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);

        TextView areaName = (TextView) getView().findViewById(R.id.textAreaName);
        areaName.setText(mTenKhuVuc);
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

    @Override
    public void onPostExecute(CustomAsyncTask<List<Integer>, Void, Boolean> task,
            Boolean result) {
        if (result) {
            getLoaderManager().restartLoader(0, null, this);
        } else {
            U.showErrorDialog(getActivity(), R.string.message_table_spliting_failed);
        }
    }

    @Override
    public void onAuthDlgDismissed(boolean authenticated) {
//        switch (action) {
//            case ACT_GROUP_TABLE:
//                postTableSelection(client);
//                break;
//
//            case ACT_SPLIT_TABLE:
//                TableSplittingDlgFragment dlg = new TableSplittingDlgFragment(mCurrTabSel
//                        .getMainTab().getMaBan());
//                U.showDlgFragment(getFragmentManager(), dlg, false);
//                break;
//
//            default:
//                break;
//        }
    }

    public int getMaKhuVuc() {
        return mAreaId;
    }
}
