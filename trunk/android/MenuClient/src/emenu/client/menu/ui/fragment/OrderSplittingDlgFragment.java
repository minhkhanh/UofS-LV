package emenu.client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import emenu.client.menu.R;
import emenu.client.menu.bus.task.CustomAsyncTask;
import emenu.client.menu.bus.task.GetServingOrderItemsTask;
import emenu.client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.menu.ui.adapter.SplittingOrderAdapter;
import emenu.client.menu.util.U;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class OrderSplittingDlgFragment extends DialogFragment implements
        OnItemClickListener, OnPostExecuteListener<Integer, Void, List<ContentValues>> {

    private SplittingOrderAdapter mSrcListAdapter;
    private SplittingOrderAdapter mDesListAdapter;

    private Integer mOrderId;

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

        ListView listSrc = (ListView) getView().findViewById(R.id.listSrc);
        mSrcListAdapter = new SplittingOrderAdapter(getActivity(),
                new ArrayList<ContentValues>());
        listSrc.setAdapter(mSrcListAdapter);
        listSrc.setOnItemClickListener(this);

        ListView listDes = (ListView) getView().findViewById(R.id.listDes);
        mDesListAdapter = new SplittingOrderAdapter(getActivity(),
                new ArrayList<ContentValues>());
        listDes.setAdapter(mDesListAdapter);
        listDes.setOnItemClickListener(this);

        new GetServingOrderItemsTask(GetServingOrderItemsTask.FLAG_ORDERED_ONLY)
                .setOnPostExecuteListener(this).execute(mOrderId);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        switch (arg0.getId()) {
            case R.id.listSrc:
                break;

            default:
                break;
        }
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Integer, Void, List<ContentValues>> task,
            List<ContentValues> result) {
        if (result.size() > 0) {
            mSrcListAdapter.clear();
            mSrcListAdapter.addAll(result);
            mSrcListAdapter.notifyDataSetChanged();

            mDesListAdapter.add(result.get(0));
            mDesListAdapter.add(result.get(1));
        } else {
            U.toastText(getActivity(), R.string.message_connect_server_failed);
        }
    }
}
