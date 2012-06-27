package emenu.client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import client.menu.R;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.dto.OrderDTO;
import emenu.client.menu.bus.SessionManager;
import emenu.client.menu.bus.loader.GetServingOrderListLoader;
import emenu.client.menu.bus.task.CustomAsyncTask;
import emenu.client.menu.bus.task.GetServingOrderItemsTask;
import emenu.client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.menu.dao.AbstractDAO;
import emenu.client.menu.dao.BanDAO;
import emenu.client.menu.ui.activity.MainMenuActivity;
import emenu.client.menu.ui.adapter.BriefOrderAdapter;
import emenu.client.menu.util.U;

public class ServingOrderListDlgFragment extends DialogFragment implements
        LoaderCallbacks<List<OrderDTO>>, OnClickListener {

    private Integer mGroupId;

    private BriefOrderAdapter mListAdapter;
    private ExpandableListView mOrderList;

    private int mSelectedGroup;

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
            mSelectedGroup = groupPosition;
            if (mListAdapter.getGroup(groupPosition) != null
                    && mListAdapter.getChildrenCount(groupPosition) == 0) {
                Bundle extras = new Bundle();
                extras.putInt("groupPosition", groupPosition);

                SessionManager.getInstance().loadSession(
                        mListAdapter.getGroup(groupPosition).getMaOrder());
                GetServingOrderItemsTask task = new GetServingOrderItemsTask(
                        GetServingOrderItemsTask.FLAG_BOTH);
                task.setOnPostExecuteListener(mOnPostExecuteGroupExpanding);
                task.setExtras(extras);
                task.execute(mListAdapter.getGroup(groupPosition).getMaOrder());
            }
        }
    };

//    private OnGroupClickListener mOnGroupClickListener = new OnGroupClickListener() {
//        @Override
//        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition,
//                long id) {
//            mSelectedGroup = groupPosition;
//            return false;
//        }
//    };

    class PostNewOrderTask extends CustomAsyncTask<OrderDTO, Void, Integer> {
        @Override
        protected Integer doInBackground(OrderDTO... params) {
            String url = AbstractDAO.LOCAL_SERVER_URL + "themOrderJson";
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
                new AlertDialog.Builder(getActivity())
                        .setMessage(R.string.message_create_new_order_failed).create()
                        .show();
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

    public ServingOrderListDlgFragment(Integer groupId) {
        mGroupId = groupId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setCancelable(false);
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
//        mOrderList.setOnGroupClickListener(mOnGroupClickListener);

        getView().findViewById(R.id.btnSelectOrder).setOnClickListener(this);
        getView().findViewById(R.id.btnCancel).setOnClickListener(this);
        getView().findViewById(R.id.btnNewOrder).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        getLoaderManager().initLoader(0, null, this);

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
            case R.id.btnSelectOrder:
                OrderDTO order = mListAdapter.getGroup(mSelectedGroup);
                SessionManager.getInstance().loadSession(order.getMaOrder());
                Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                startActivity(intent);
                break;

            case R.id.btnCancel:
                dismiss();
                break;
            default:
                break;
        }
    }
}
