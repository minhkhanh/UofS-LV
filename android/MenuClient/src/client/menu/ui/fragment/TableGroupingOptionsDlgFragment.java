package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import client.menu.R;
import client.menu.bus.loader.GetTableGroupsLoader;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import client.menu.bus.task.PostTableSelectionTask;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.ChiTietNhomBan;
import client.menu.ui.adapter.TableGroupsAdapter;
import client.menu.util.U;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

public class TableGroupingOptionsDlgFragment extends DialogFragment implements
        LoaderCallbacks<List<ChiTietNhomBan>> {

    private Integer mAreaId;
    private List<BanDTO> mTableList;

    private TableGroupsAdapter mListAdapter;
    private ListView mGroupList;

    private Runnable mRefreshTableMap;

    protected OnPostExecuteListener<Void, Void, Boolean> mOnPostExecutePostTableGrouping = new OnPostExecuteListener<Void, Void, Boolean>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task,
                Boolean result) {
            if (!result) {
                new AlertDialog.Builder(getActivity())
                        .setMessage(R.string.message_table_grouping_failed).create()
                        .show();
            } else {
                mRefreshTableMap.run();
                dismiss();
            }
        }
    };

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View arg0) {
            switch (arg0.getId()) {
                case R.id.btnCancel:
                    dismiss();
                    break;

                case R.id.btnOK:
                    int pos = mGroupList.getCheckedItemPosition();
                    if (pos == AbsListView.INVALID_POSITION) {
                        U.toastText(getActivity(),
                                R.string.message_please_choose_one_table_group);
                    } else {
                        if (mListAdapter.getItemViewType(pos) == TableGroupsAdapter.VIEW_TYPE_NEW_GROUP) {
                            if (mTableList.size() < 2) {
                                U.toastText(getActivity(),
                                        R.string.message_must_least_2_tables_in_new_group);
                            } else {
                                // tao nhom ban moi
                                PostTableSelectionTask task = new PostTableSelectionTask(mTableList);
                                task.setOnPostExecuteListener(mOnPostExecutePostTableGrouping);
                                task.execute();
                            }
                        } else {
                            if (mTableList.size() < 1) {
                                U.toastText(getActivity(),
                                        R.string.message_must_least_1_table_to_append);
                            } else {
                                // them ban vao nhom
                                BanDTO banChinh = mListAdapter.getItem(pos).getBanChinh();
                                mTableList.add(0, banChinh);
                                PostTableSelectionTask task = new PostTableSelectionTask(mTableList);
                                task.setOnPostExecuteListener(mOnPostExecutePostTableGrouping);
                                task.execute();
                            }
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    };

    public TableGroupingOptionsDlgFragment(Integer areaId, List<BanDTO> tableList,
            Runnable onRefreshTableMap) {
        mTableList = tableList;
        mAreaId = areaId;
        mRefreshTableMap = onRefreshTableMap;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_table_grouping_options, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().setCancelable(false);
        getDialog().setTitle(R.string.caption_select_table_group);

        mGroupList = (ListView) getView().findViewById(R.id.listTableGroups);
        mListAdapter = new TableGroupsAdapter(getActivity(),
                new ArrayList<ChiTietNhomBan>());
        mGroupList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        mGroupList.setAdapter(mListAdapter);

        Button btnCancel = (Button) getView().findViewById(R.id.btnCancel);
        Button btnOK = (Button) getView().findViewById(R.id.btnOK);
        btnCancel.setOnClickListener(mOnClickListener);
        btnOK.setOnClickListener(mOnClickListener);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<List<ChiTietNhomBan>> onCreateLoader(int id, Bundle args) {
        return new GetTableGroupsLoader(getActivity(), mAreaId);
    }

    @Override
    public void onLoadFinished(Loader<List<ChiTietNhomBan>> arg0,
            List<ChiTietNhomBan> arg1) {
        mListAdapter.clear();
        mListAdapter.addAll(arg1);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<ChiTietNhomBan>> arg0) {
        mListAdapter.clear();
        mListAdapter.notifyDataSetChanged();
    }
}
