package emenu.client.menu.activity;

import org.apache.http.client.HttpClient;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetMoveOrderTask;
import emenu.client.db.dto.BanDTO;
import emenu.client.menu.R;
import emenu.client.menu.fragment.AreaListFragment;
import emenu.client.menu.fragment.AuthDlgFragment.OnAuthorizedListener;
import emenu.client.menu.fragment.TableMapFragment.OnTableClickedListener;
import emenu.client.util.U;

public class TableMapActivity extends Activity implements Callback,
        OnTableClickedListener, OnPostExecuteListener<Void, Void, Boolean>,
        OnAuthorizedListener {
    public static final String KEY_MOVING_ORDER_ID = "KEY_MOVING_ORDER_ID";
    public static final String KEY_DES_TAB_ID = "KEY_DES_TAB_ID";

    private static final int ACT_MOVE_ORDER = 0;

    private ActionMode mOrderMovingMode;
    private Integer mMovingOrderId;

    private GetMoveOrderTask mMoveOrderTask;

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
        AreaListFragment f = (AreaListFragment) fm.findFragmentById(R.id.LeftPaneHolder);
        if (f == null) {
            if (mOrderMovingMode != null)
                f = new AreaListFragment(this);
            else
                f = new AreaListFragment(null);
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.LeftPaneHolder, f);
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
            Bundle extras = new Bundle();
            extras.putInt(KEY_DES_TAB_ID, table.getMaBan());
            extras.putInt(KEY_MOVING_ORDER_ID, mMovingOrderId);
            U.showAuthDlg(this, getFragmentManager(), ACT_MOVE_ORDER, extras);
        }
    }

    @Override
    public boolean isInOrderMovingMode() {
        return mOrderMovingMode != null;
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task, Boolean result) {
        if (result) {
            U.toastText(TableMapActivity.this, R.string.message_move_order_succeed);
            mOrderMovingMode.finish();
        } else {
            U.toastText(TableMapActivity.this, R.string.message_move_order_failed);
        }
    }

    @Override
    public void onAuthorized(HttpClient client, Bundle extras, int action) {
        switch (action) {
            case ACT_MOVE_ORDER:
                U.cancelAsyncTask(mMoveOrderTask);

                mMoveOrderTask = new GetMoveOrderTask(client,
                        extras.getInt(KEY_MOVING_ORDER_ID), extras.getInt(KEY_DES_TAB_ID));
                mMoveOrderTask.setOnPostExecuteListener(this).execute();
                break;

            default:
                break;
        }
    }
}
