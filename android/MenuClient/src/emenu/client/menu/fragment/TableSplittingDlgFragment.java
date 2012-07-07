package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import emenu.client.menu.R;
import emenu.client.bus.loader.TableListLoader;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.GetTableInGroupTask;
import emenu.client.bus.task.GetTableSplittingAllTask;
import emenu.client.bus.task.GetTableSplittingTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.db.dto.BanDTO;
import emenu.client.menu.adapter.TableListAdapter;
import emenu.client.util.U;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;

public class TableSplittingDlgFragment extends DialogFragment implements
        OnItemClickListener, OnClickListener {
    private Integer mGroupId;
    private GridView mTableGrid;
    private TableListAdapter mGridAdapter;
    private GetTableInGroupTask mRefreshTask;

    private OnPostExecuteListener<Integer, Void, List<BanDTO>> mOnPostGetTableInGroup = new OnPostExecuteListener<Integer, Void, List<BanDTO>>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Integer, Void, List<BanDTO>> task,
                List<BanDTO> result) {
            mGridAdapter.clear();
            mGridAdapter.addAll(result);
            mGridAdapter.notifyDataSetChanged();
        }
    };

    private OnPostExecuteListener<Integer, Void, Boolean> mOnPostGetTableSplittingAll = new OnPostExecuteListener<Integer, Void, Boolean>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Integer, Void, Boolean> task,
                Boolean result) {
            if (result) {
                U.toastText(getActivity(), R.string.message_table_spliting_all_succeed);
                dismiss();
            } else {
                U.showErrorDialog(getActivity(),
                        R.string.message_table_spliting_all_failed);
            }
        }
    };

    private OnPostExecuteListener<Integer, Void, Boolean> mOnPostGetTableSplitting = new OnPostExecuteListener<Integer, Void, Boolean>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Integer, Void, Boolean> task,
                Boolean result) {
            if (result) {
                refreshGrid();
                U.toastText(getActivity(), R.string.message_table_spliting_succeed);
            } else {
                U.showErrorDialog(getActivity(), R.string.message_table_spliting_failed);
            }
        }
    };

    public TableSplittingDlgFragment(Integer groupId) {
        mGroupId = groupId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(R.string.text_select_table_to_split);
        return inflater.inflate(R.layout.layout_table_splitting, null);
    }

    private void refreshGrid() {
        U.cancelAsyncTask(mRefreshTask);
        mRefreshTask = new GetTableInGroupTask();
        mRefreshTask.setOnPostExecuteListener(mOnPostGetTableInGroup).execute(mGroupId);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mGridAdapter = new TableListAdapter(getActivity(), new ArrayList<BanDTO>());
        mTableGrid = (GridView) getView().findViewById(R.id.gridTable);
        mTableGrid.setAdapter(mGridAdapter);
        mTableGrid.setOnItemClickListener(this);

        getView().findViewById(R.id.btnExit).setOnClickListener(this);
        getView().findViewById(R.id.btnSplitAll).setOnClickListener(this);

        refreshGrid();
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        BanDTO clickTable = mGridAdapter.getItem(arg2);
        new GetTableSplittingTask().setOnPostExecuteListener(mOnPostGetTableSplitting)
                .execute(clickTable.getMaBan());
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btnExit:
                dismiss();
                break;

            case R.id.btnSplitAll:
                new GetTableSplittingAllTask().setOnPostExecuteListener(
                        mOnPostGetTableSplittingAll).execute(mGroupId);
                break;
            default:
                break;
        }
    }
}