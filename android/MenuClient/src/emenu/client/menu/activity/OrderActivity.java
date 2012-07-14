package emenu.client.menu.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetServingOrderItemsTask.OrderFlag;
import emenu.client.bus.task.PostOrderTask;
import emenu.client.menu.R;
import emenu.client.menu.adapter.OrderedAdapter;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceOrder;
import emenu.client.menu.app.SessionManager.ServiceSession;
import emenu.client.menu.fragment.OrderFragment;
import emenu.client.menu.fragment.EditOrderedItemDlgFragment;
import emenu.client.menu.fragment.EditOrderedItemDlgFragment.OnItemUpdatedListener;
import emenu.client.util.U;

public class OrderActivity extends ListActivity implements TabListener,
        OnItemUpdatedListener, OnPostExecuteListener<Void, Void, Boolean> {

    private static final int ACT_CONFIRM_ORDER = 0;

    private Tab mUnorderedTab;
    private Tab mOrderedTab;
    private PostOrderTask mPostOrderTask;
    private Menu mMenu;
    private OrderFragment mOrderFragment;

    private DataSetObserver mOrderObserver = new DataSetObserver() {
        public void onChanged() {
            refreshList(getActionBar().getSelectedTab());
        }
    };

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
                ServiceSession session = SessionManager.getInstance()
                        .loadCurrentSession();
                ServiceOrder order = session.getOrder();
                if (order.getCount() > 0) {
                    U.cancelAsyncTask(mPostOrderTask);

                    mPostOrderTask = new PostOrderTask();
                    mPostOrderTask.setOnPostExecuteListener(this).execute();
                }
                else
                    U.toastText(this, R.string.message_null_order_not_allowed);
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

        mMenu = menu;

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServiceSession session = SessionManager.getInstance().loadCurrentSession();
        ServiceOrder order = session.getOrder();
        order.registerObserver(mOrderObserver);

        mOrderFragment = new OrderFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, mOrderFragment);
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initActionBar();
    }

    // ACTION BAR TABS CALLBACKS
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        refreshList(tab);

        if (mMenu != null)
            if (tab == mOrderedTab)
                mMenu.findItem(R.id.miConfirmOrder).setEnabled(false);
            else
                mMenu.findItem(R.id.miConfirmOrder).setEnabled(true);
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    // END OF ACTION BAR TABS CALLBACKS

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Tab tab = getActionBar().getSelectedTab();
        if (tab == mOrderedTab) {
            OrderedAdapter adapter = (OrderedAdapter) mOrderFragment.getListAdapter();
            EditOrderedItemDlgFragment f = new EditOrderedItemDlgFragment(
                    adapter.getItem(position));
            U.showDlgFragment(this, f, true);
        }
    }

    @Override
    public void onItemUpdated() {
        refreshList(getActionBar().getSelectedTab());
    }

    private void refreshList(Tab selTab) {
        if (selTab == mUnorderedTab)
            mOrderFragment.refreshList(OrderFlag.UnorderedOnly);
        else
            mOrderFragment.refreshList(OrderFlag.OrderedOnly);
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task, Boolean result) {
        ServiceSession session = SessionManager.getInstance().loadCurrentSession();

        if (result) {
            U.toastText(OrderActivity.this,
                    OrderActivity.this.getString(R.string.message_order_sent));

            session.getOrder().clear();

            refreshList(getActionBar().getSelectedTab());
        } else {
            new AlertDialog.Builder(OrderActivity.this)
                    .setMessage(R.string.message_connect_server_failed).create().show();
        }
    }
}
