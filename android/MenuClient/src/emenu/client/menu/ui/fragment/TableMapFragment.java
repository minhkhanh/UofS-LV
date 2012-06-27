package emenu.client.menu.ui.fragment;

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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import client.menu.R;
import emenu.client.db.dto.BanDTO;
import emenu.client.menu.bus.loader.TableListLoader;
import emenu.client.menu.bus.task.CustomAsyncTask;
import emenu.client.menu.bus.task.PostTableSelectionTask;
import emenu.client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.menu.ui.adapter.TableListAdapter;
import emenu.client.menu.ui.fragment.TableMapFragment.OnTableClickedListener;
import emenu.client.menu.util.U;

public class TableMapFragment extends TableInAreaFragment implements
        LoaderCallbacks<List<BanDTO>>, OnPostExecuteListener<Void, Void, Boolean> {

    public static final int SEL_STATE_SINGLE_FREE = 0;
    public static final int SEL_STATE_SINGLE_BUSY = 1;
    public static final int SEL_STATE_MANY_FREE = 2;
    public static final int SEL_STATE_MIXED = 3;
    public static final int SEL_STATE_GROUP_BUSY = 4;

    int mCurrSelectionState;

    private TableListAdapter mGridAdapter;
    private GridView mTableGrid;
    private ActionMode mActionMode;

    private OnTableClickedListener mTableClickedListener;

    public interface OnTableClickedListener {
        boolean isInOrderMovingMode();

        void onTableClicked(BanDTO table);
    }

    protected OnPostExecuteListener<Void, Void, Boolean> mOnPostExecuteTableSelecting = new OnPostExecuteListener<Void, Void, Boolean>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task,
                Boolean result) {
            if (!result) {
                new AlertDialog.Builder(getActivity())
                        .setMessage(R.string.message_select_table_failed).create().show();
            } else {
                Integer groupId = task.getExtras().getInt("groupId");
                ServingOrderListDlgFragment dlg = new ServingOrderListDlgFragment(groupId);
                U.showDlgFragment(getActivity(), dlg, "dlg");

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
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            List<BanDTO> tabSelection = getTableSelection();
            Integer groupId = findGroupId(tabSelection);

            switch (item.getItemId()) {
                case R.id.miGroupTable:
                case R.id.miSelectTable:
                    Bundle ext = new Bundle();
                    ext.putInt("groupId", groupId);
                    PostTableSelectionTask task = new PostTableSelectionTask(tabSelection);
                    task.setExtras(ext);
                    task.setOnPostExecuteListener(mOnPostExecuteTableSelecting);
                    task.execute();

                    break;

                case R.id.miSplitTable:
                    TableSplittingDlgFragment dlg = new TableSplittingDlgFragment(groupId);
                    U.showDlgFragment(getActivity(), dlg, "dlg");
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
                            mTableGrid.setItemChecked(i, mTableGrid.isItemChecked(pos));
                        } else if (!ban.getActive()) {
                            mTableGrid.setItemChecked(i, false);
                        }
                    }
                }

                List<BanDTO> tabSelection = getTableSelection();
                mCurrSelectionState = getSelectionState(tabSelection);

                int checkedCount = mTableGrid.getCheckedItemCount();
                if (checkedCount == 0) {
                    mActionMode.finish();
                } else if (mActionMode == null) {
                    mActionMode = getActivity().startActionMode(mActionModeCallback);
                }

                if (mActionMode != null) {
                    Menu menu = mActionMode.getMenu();
                    menu.clear();
                    switch (mCurrSelectionState) {
                        case SEL_STATE_SINGLE_FREE:
                        case SEL_STATE_SINGLE_BUSY:
                            menu.add(Menu.NONE, R.id.miSelectTable, 0,
                                    R.string.context_select_table);
                            break;
                        case SEL_STATE_MIXED:
                        case SEL_STATE_MANY_FREE:
                            menu.add(Menu.NONE, R.id.miGroupTable, 0,
                                    R.string.option_group_table);
                            break;
                        case SEL_STATE_GROUP_BUSY:
                            menu.add(Menu.NONE, R.id.miSelectTable, 0,
                                    R.string.context_select_table);
                            menu.add(Menu.NONE, R.id.miSplitTable, 0,
                                    R.string.context_table_item_split);
                            break;
                    }
                }
            }
        }
    };

    public TableMapFragment(OnTableClickedListener onTableClickedListener,
            Integer areaId, String tenKhuVuc) {
        super(areaId, tenKhuVuc);

        mTableClickedListener = onTableClickedListener;
    }

    private Integer findGroupId(List<BanDTO> tabSelection) {
        for (BanDTO b : tabSelection) {
            if (b.getMaBanChinh() != null) {
                return b.getMaBanChinh();
            }
        }

        return tabSelection.get(0).getMaBan();
    }

    private int getSelectionState(List<BanDTO> tabSelection) {
        if (tabSelection.size() == 1) {
            if (tabSelection.get(0).getMaBanChinh() == null) {
                return SEL_STATE_SINGLE_FREE;
            }

            return SEL_STATE_SINGLE_BUSY;
        }

        boolean hasFree = false;
        for (BanDTO b : tabSelection) {
            if (b.getMaBanChinh() == null)
                hasFree = true;
            else if (hasFree)
                return SEL_STATE_MIXED;
        }

        if (hasFree)
            return SEL_STATE_MANY_FREE;

        return SEL_STATE_GROUP_BUSY;
    }

    private List<BanDTO> getTableSelection() {
        SparseBooleanArray chkArray = mTableGrid.getCheckedItemPositions();
        final List<BanDTO> tableSelection = new ArrayList<BanDTO>();
        for (int i = 0; i < chkArray.size(); ++i) {
            if (chkArray.valueAt(i)) {
                int pos = chkArray.keyAt(i);
                BanDTO ban = mGridAdapter.getItem(pos);
                tableSelection.add(ban);
            }
        }

        return tableSelection;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mMaKhuVuc", mAreaId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mAreaId = savedInstanceState.getInt("mMaKhuVuc");
        }

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
    public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task, Boolean result) {
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
