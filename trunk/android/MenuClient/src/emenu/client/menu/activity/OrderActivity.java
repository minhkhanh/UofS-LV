package emenu.client.menu.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import emenu.client.menu.R;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.GetServingOrderItemsTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.dao.OrderDAO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.menu.adapter.OrderAdapter;
import emenu.client.menu.adapter.OrderedAdapter;
import emenu.client.menu.adapter.UnorderedAdapter;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceOrder;
import emenu.client.menu.app.SessionManager.ServiceSession;
import emenu.client.menu.fragment.OrderedItemEditingDlgFragment;
import emenu.client.menu.fragment.OrderedItemEditingDlgFragment.OnItemUpdatedListener;
import emenu.client.util.U;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class OrderActivity extends Activity implements TabListener, OnItemClickListener,
        OnItemUpdatedListener {

    private Tab mUnorderedTab;
    private Tab mOrderedTab;
    private OrderAdapter mItemsAdapter;
    private ListView mItemsList;
    private ProgressDialog mWatingDlg;

    private DataSetObserver mOrderObserver = new DataSetObserver() {
        public void onChanged() {
            refreshList();
        }
    };

    private class PostOrderItemsTask extends CustomAsyncTask<Void, Void, Boolean> {
        List<ChiTietOrderDTO> mItems;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ServiceSession session = SessionManager.getInstance().loadCurrentSession();

            mItems = session.bindOrder().getOrderItems();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                return OrderDAO.getInstance().postArrayChiTietOrder(mItems);
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            ServiceSession session = SessionManager.getInstance().loadCurrentSession();

            mWatingDlg.cancel();
            if (result) {
                U.toastText(OrderActivity.this, OrderActivity.this.getResources()
                        .getString(R.string.message_order_sent));

                session.getOrder().clear();

                refreshList();
            } else {
                new AlertDialog.Builder(OrderActivity.this)
                        .setMessage(R.string.message_connect_server_failed).create()
                        .show();
            }
        }
    };

    private OnPostExecuteListener<Integer, Void, List<ContentValues>> mOnPostGetServingOrderItems = new OnPostExecuteListener<Integer, Void, List<ContentValues>>() {
        @Override
        public void onPostExecute(
                CustomAsyncTask<Integer, Void, List<ContentValues>> task,
                List<ContentValues> result) {
            mItemsAdapter.clear();
            mItemsAdapter.addAll(result);
            mItemsAdapter.notifyDataSetChanged();
        }
    };

    private void initViews() {
        mItemsList = (ListView) findViewById(R.id.listOrder);
        mItemsList.setOnItemClickListener(this);
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        mUnorderedTab = actionBar.newTab().setText(
                R.string.caption_tab_unordered_order_items);
        mOrderedTab = actionBar.newTab()
                .setText(R.string.caption_tab_ordered_order_items);

        mUnorderedTab.setTabListener(this);
        mOrderedTab.setTabListener(this);

        actionBar.addTab(mUnorderedTab);
        actionBar.addTab(mOrderedTab);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miConfirmOrder:
                U.showConfirmDialog(this, R.string.message_confirm_order,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mWatingDlg = ProgressDialog.show(OrderActivity.this, "",
                                        getString(R.string.message_waiting));
                                new PostOrderItemsTask().execute();
                            }
                        });

                break;

            default:
                break;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_order, menu);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_order);

        initViews();
        initActionBar();

        ServiceSession session = SessionManager.getInstance().loadCurrentSession();
        ServiceOrder order = session.getOrder();
        order.registerObserver(mOrderObserver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }

    private Tab getCurrentTab() {
        ActionBar bar = getActionBar();
        return bar.getSelectedTab();
    }

    private void refreshList() {
        Tab selTab = getCurrentTab();

        if (selTab == mUnorderedTab) {
            mItemsAdapter = new UnorderedAdapter(this, new ArrayList<ContentValues>());

            new GetServingOrderItemsTask(GetServingOrderItemsTask.FLAG_UNORDERED_ONLY)
                    .setOnPostExecuteListener(mOnPostGetServingOrderItems).execute();
        } else {
            mItemsAdapter = new OrderedAdapter(this, new ArrayList<ContentValues>());

            ServiceSession session = SessionManager.getInstance().loadCurrentSession();
            new GetServingOrderItemsTask(GetServingOrderItemsTask.FLAG_ORDERED_ONLY)
                    .setOnPostExecuteListener(mOnPostGetServingOrderItems).execute(
                            session.getOrderId());
        }

        mItemsList.setAdapter(mItemsAdapter);
    }

    // ACTION BAR TABS CALLBACKS
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        refreshList();
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    // END OF ACTION BAR TABS CALLBACKS

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Tab tab = getCurrentTab();
        if (tab == mOrderedTab) {
            OrderedItemEditingDlgFragment f = new OrderedItemEditingDlgFragment(
                    mItemsAdapter.getItem(arg2));
            U.showDlgFragment(this, f, true);
        }
    }

    @Override
    public void onItemUpdated() {
        refreshList();
    }
}
