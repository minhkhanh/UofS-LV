package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetServingOrderItemsTask;
import emenu.client.bus.task.GetServingOrderItemsTask.OrderFlag;
import emenu.client.menu.adapter.OrderAdapter;
import emenu.client.menu.adapter.OrderedAdapter;
import emenu.client.menu.adapter.UnorderedAdapter;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceSession;
import emenu.client.util.U;

public class OrderFragment extends ListFragment implements
        OnPostExecuteListener<Integer, Void, List<ContentValues>> {

    private OrderedAdapter mOrderedAdapter;
    private UnorderedAdapter mUnorderedAdapter;

    private GetServingOrderItemsTask mGetOrderListTask;

    public void refreshList(OrderFlag flag) {
        setListShown(false);

        U.cancelAsyncTask(mGetOrderListTask);
        mGetOrderListTask = new GetServingOrderItemsTask(flag);
        mGetOrderListTask.setOnPostExecuteListener(this);

        if (flag == OrderFlag.UnorderedOnly) {
            mUnorderedAdapter = new UnorderedAdapter(getActivity(),
                    new ArrayList<ContentValues>());

            setListAdapter(mUnorderedAdapter);
            mGetOrderListTask.execute();
        } else if (flag == OrderFlag.OrderedOnly) {
            mOrderedAdapter = new OrderedAdapter(getActivity(),
                    new ArrayList<ContentValues>());

            ServiceSession session = SessionManager.getInstance().loadCurrentSession();
            setListAdapter(mOrderedAdapter);
            mGetOrderListTask.execute(session.getOrderId());
        }
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Integer, Void, List<ContentValues>> task,
            List<ContentValues> result) {
        GetServingOrderItemsTask customTask = (GetServingOrderItemsTask) task;
        OrderAdapter adapter;
        if (customTask.getFlag() == OrderFlag.UnorderedOnly)
            adapter = mUnorderedAdapter;
        else
            adapter = mOrderedAdapter;

        adapter.clear();
        adapter.addAll(result);
        adapter.notifyDataSetChanged();

        setListShown(true);
    }
}
