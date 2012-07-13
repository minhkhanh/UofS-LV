package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.PostOrderSplittingTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetServingOrderItemsTask;
import emenu.client.menu.R;
import emenu.client.menu.adapter.MiniOrderAdapter;
import emenu.client.menu.fragment.AuthDlgFragment.OnAuthorizedListener;
import emenu.client.menu.view.DishSwappableListView;
import emenu.client.util.U;

public class OrderSplittingDlgFragment extends DialogFragment implements
        OnPostExecuteListener<Integer, Void, List<ContentValues>>, OnClickListener,
        OnAuthorizedListener {

    public static final int ACT_SPLIT_ORDER = 0;

    private MiniOrderAdapter mSrcListAdapter;
    private MiniOrderAdapter mDesListAdapter;
    private Integer mOrderId;

    private PostOrderSplittingTask mSplitOrderTask;

    public interface OnOrderSplittedListener {
        void onOrderSplitted();
    }

    private OnPostExecuteListener<Void, Void, Boolean> mOnPostOrderSplitting = new OnPostExecuteListener<Void, Void, Boolean>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task,
                Boolean result) {
            if (result) {
                U.toastText(getActivity(), R.string.message_request_completed);
                dismiss();
            } else {
                U.showErrorDialog(getActivity(), R.string.message_order_splitting_failed);
            }
        }
    };
    
    public OrderSplittingDlgFragment() {
        mOrderId = 0;
    }

    public OrderSplittingDlgFragment(Integer orderId) {
        mOrderId = orderId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(R.string.caption_order_splitting);
        return inflater.inflate(R.layout.layout_order_splitting, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DishSwappableListView listSrc = (DishSwappableListView) getView().findViewById(
                R.id.listSrc);
        mSrcListAdapter = new MiniOrderAdapter(getActivity(),
                new ArrayList<ContentValues>());
        listSrc.setAdapter(mSrcListAdapter);

        DishSwappableListView listDes = (DishSwappableListView) getView().findViewById(
                R.id.listDes);
        mDesListAdapter = new MiniOrderAdapter(getActivity(),
                new ArrayList<ContentValues>());
        listDes.setAdapter(mDesListAdapter);

        listSrc.pair(listDes);

        new GetServingOrderItemsTask(GetServingOrderItemsTask.OrderFlag.OrderedOnly)
                .setOnPostExecuteListener(this).execute(mOrderId);

        getView().findViewById(R.id.btnSplitOrder).setOnClickListener(this);
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Integer, Void, List<ContentValues>> task,
            List<ContentValues> result) {
        if (result.size() > 0) {
            mSrcListAdapter.clear();
            mSrcListAdapter.addAll(result);
            mSrcListAdapter.notifyDataSetChanged();
        } else {
            U.toastText(getActivity(), R.string.message_connect_server_failed);
        }
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btnSplitOrder:
                if (mSrcListAdapter.getCount() == 0 || mDesListAdapter.getCount() == 0) {
                    U.toastText(getActivity(), R.string.message_splitting_orders_empty);
                } else {
                    U.showAuthDlg(this, getFragmentManager(), ACT_SPLIT_ORDER, null);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onAuthorized(HttpClient client, Bundle extras, int action) {
        switch (action) {
            case ACT_SPLIT_ORDER:
                U.cancelAsyncTask(mSplitOrderTask);

                List<ContentValues> desContent = mDesListAdapter.getData();
                mSplitOrderTask = new PostOrderSplittingTask(client, desContent);
                mSplitOrderTask.setOnPostExecuteListener(mOnPostOrderSplitting).execute();
                break;

            default:
                break;
        }
    }
}
