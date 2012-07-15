package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.DialogFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import emenu.client.bus.loader.GetServingOrderListLoader;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetServingOrderItemsTask;
import emenu.client.dao.AbstractDAO;
import emenu.client.dao.BanDAO;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.dto.OrderDTO;
import emenu.client.menu.R;
import emenu.client.menu.activity.MainMenuActivity;
import emenu.client.menu.activity.TableMapActivity;
import emenu.client.menu.adapter.BriefOrderAdapter;
import emenu.client.menu.app.SessionManager;
import emenu.client.util.U;

public class BriefOrderListDlgFragment extends DialogFragment implements
        LoaderCallbacks<List<OrderDTO>>, OnClickListener {

    private Integer mGroupId;

    private BriefOrderAdapter mListAdapter;
    private ExpandableListView mOrderList;

    private TargetTableLoadingTask mTargetTableLoadingTask;
    private PostNewOrderTask mPostNewOrderTask;

    protected OnPostExecuteListener<Integer, Void, List<ContentValues>> mOnPostExecuteGroupExpanding = new OnPostExecuteListener<Integer, Void, List<ContentValues>>() {
        @Override
        public void onPostExecute(
                CustomAsyncTask<Integer, Void, List<ContentValues>> task,
                List<ContentValues> result) {
            Bundle extras = task.getExtras();
            int groupPosition = extras.getInt("groupPosition", -1);
            if (groupPosition != -1) {
                mListAdapter.clearChildren(groupPosition);
                mListAdapter.addChildAll(groupPosition, result);
                mListAdapter.notifyDataSetChanged();
            }
        }
    };

    OnGroupExpandListener mOnGroupExpandListener = new OnGroupExpandListener() {
        @Override
        public void onGroupExpand(int groupPosition) {
            if (mListAdapter.getGroup(groupPosition) != null
                    && mListAdapter.getChildrenCount(groupPosition) == 0) {
                SessionManager.getInstance().loadSession(
                        mListAdapter.getGroup(groupPosition).getMaOrder());
                GetServingOrderItemsTask task = new GetServingOrderItemsTask(
                        GetServingOrderItemsTask.OrderFlag.Both);
                task.setOnPostExecuteListener(mOnPostExecuteGroupExpanding);
                task.getExtras().putInt("groupPosition", groupPosition);
                task.execute(mListAdapter.getGroup(groupPosition).getMaOrder());
            }
        }
    };

    class PostNewOrderTask extends CustomAsyncTask<OrderDTO, Void, Integer> {
        @Override
        protected Integer doInBackground(OrderDTO... params) {
            String url = AbstractDAO.SERVER_URL_SLASH + "themOrderJson";
            Integer orderId = null;

            try {
                JSONObject jsonObj = params[0].toJson();
                String response = U.loadPostResponseJson(url, jsonObj.toString());

                orderId = Integer.valueOf(response);
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }

            return orderId;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            if (result != -1) {
                SessionManager.getInstance().loadSession(result);
                Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                startActivity(intent);

                dismiss();
            } else {
                U.showErrorDialog(getActivity(), R.string.message_create_new_order_failed);
            }
        }
    }

    class TargetTableLoadingTask extends CustomAsyncTask<Integer, Void, BanDTO> {
        @Override
        protected BanDTO doInBackground(Integer... params) {
            return BanDAO.getInstance().objByMaBan(params[0]);
        }

        @Override
        protected void onPostExecute(BanDTO result) {
            if (result != null) {
                getDialog().setTitle(
                        getString(R.string.sub_order_list_at) + " " + result.getTenBan());
            }
        }
    }

    public BriefOrderListDlgFragment() {
        mGroupId = 0;
    }

    public BriefOrderListDlgFragment(Integer groupId) {
        mGroupId = groupId;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        TableMapActivity host = (TableMapActivity) getActivity();
        if (host != null)
            host.refreshGrid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setCancelable(false);
        // getDialog().setOnDismissListener(mDismissListener);
        return inflater.inflate(R.layout.layout_brief_order_list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mOrderList = (ExpandableListView) getView().findViewById(R.id.elistOrder);
        mOrderList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        // mOrderList.setItemsCanFocus(false);

        mListAdapter = new BriefOrderAdapter(getActivity(), new ArrayList<OrderDTO>());
        mOrderList.setAdapter(mListAdapter);

        mOrderList.setOnGroupExpandListener(mOnGroupExpandListener);
        // mOrderList.setOnChildClickListener(mOnChildClickListener);
        // mOrderList.setOnGroupClickListener(mOnGroupClickListener);

        getView().findViewById(R.id.btnCancel).setOnClickListener(this);
        getView().findViewById(R.id.btnNewOrder).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        getLoaderManager().restartLoader(0, null, this);

        mTargetTableLoadingTask = new TargetTableLoadingTask();
        mTargetTableLoadingTask.execute(mGroupId);
    }

    @Override
    public void onPause() {
        super.onPause();

        U.cancelAsyncTask(mTargetTableLoadingTask);
    }

    @Override
    public Loader<List<OrderDTO>> onCreateLoader(int arg0, Bundle arg1) {
        return new GetServingOrderListLoader(getActivity(), mGroupId);
    }

    @Override
    public void onLoadFinished(Loader<List<OrderDTO>> arg0, List<OrderDTO> arg1) {
        mListAdapter.clearGroup();
        // mListAdapter.addGroup(0, null);
        mListAdapter.addGroupAll(arg1);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<OrderDTO>> arg0) {
        mListAdapter.clearGroup();
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewOrder:
                OrderDTO newOrder = new OrderDTO();
                newOrder.setMaBan(mGroupId);
                newOrder.setMaTaiKhoan(1);

                mPostNewOrderTask = new PostNewOrderTask();
                mPostNewOrderTask.execute(newOrder);
                break;

            case R.id.btnCancel:
                dismiss();
                break;
            default:
                break;
        }
    }
}
