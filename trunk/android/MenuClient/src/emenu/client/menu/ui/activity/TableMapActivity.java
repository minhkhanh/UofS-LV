package emenu.client.menu.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import emenu.client.menu.R;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.dao.OrderDAO;
import emenu.client.db.dto.BanDTO;
import emenu.client.menu.ui.fragment.AreaListFragment;
import emenu.client.menu.ui.fragment.TableMapFragment.OnTableClickedListener;
import emenu.client.util.U;

public class TableMapActivity extends Activity implements Callback,
        OnTableClickedListener {
    public static final String KEY_MOVING_ORDER_ID = "MovingOrderId";

    private ActionMode mOrderMovingMode;
    private Integer mMovingOrderId;

    class GetMoveOrderTask extends CustomAsyncTask<Void, Void, Boolean> {

        private Integer mOrderId;
        private Integer mTableId;

        public GetMoveOrderTask(Integer orderId, Integer tableId) {
            mOrderId = orderId;
            mTableId = tableId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                return OrderDAO.getInstance().getMoveOrder(mOrderId, mTableId);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                U.toastText(TableMapActivity.this, R.string.message_move_order_succeed);
                mOrderMovingMode.finish();
            } else {
                U.toastText(TableMapActivity.this, R.string.message_move_order_failed);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_two_panes);

        mMovingOrderId = getIntent().getIntExtra(KEY_MOVING_ORDER_ID, -1);
        if (mMovingOrderId != -1) {
            mOrderMovingMode = startActionMode(this);
        }

        View v = findViewById(android.R.id.content);
        v.setBackgroundResource(R.drawable.romantic_mood_wallpaper_070);

        FragmentManager fm = getFragmentManager();
        AreaListFragment f = (AreaListFragment) fm.findFragmentByTag("AreaListFragment");
        if (f == null) {
            if (mOrderMovingMode != null)
                f = new AreaListFragment(this);
            else
                f = new AreaListFragment(null);
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.LeftPaneHolder, f, "AreaListFragment");
            ft.commit();
        }
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return true;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mOrderMovingMode = null;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public void onTableClicked(BanDTO table) {
        if (mOrderMovingMode != null && mMovingOrderId != -1) {
            new GetMoveOrderTask(mMovingOrderId, table.getMaBan()).execute();
        }
    }

    @Override
    public boolean isInOrderMovingMode() {
        return mOrderMovingMode != null;
    }
}
