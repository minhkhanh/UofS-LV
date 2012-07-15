package emenu.client.menu.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import emenu.client.menu.fragment.AuthDlgFragment.OnAuthDlgDismissedListener;
import emenu.client.menu.fragment.TableMapFragment.OnTableClickedListener;
import emenu.client.util.U;

public class TableMapActivity extends Activity implements Callback,
        OnTableClickedListener, OnPostExecuteListener<Void, Void, Boolean>,
        OnAuthDlgDismissedListener {
    public static final String KEY_MOVING_ORDER_ID = "KEY_MOVING_ORDER_ID";
    public static final String KEY_DES_TAB_ID = "KEY_DES_TAB_ID";

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
    protected void onResume() {
        super.onResume();
        U.showAuthDlg(this, getFragmentManager(), null);
    }

    @Override
    protected void onPause() {
        super.onPause();
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
        FragmentManager fm = getFragmentManager();
        AreaListFragment f = (AreaListFragment) fm.findFragmentById(R.id.LeftPaneHolder);
        if (f != null)
            f.showSelection();
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public void onTableClicked(BanDTO table) {
        if (mOrderMovingMode != null && mMovingOrderId != -1) {
            U.cancelAsyncTask(mMoveOrderTask);
            mMoveOrderTask = new GetMoveOrderTask(mMovingOrderId, table.getMaBan());
            mMoveOrderTask.setOnPostExecuteListener(this).execute();
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
    public void onAuthDlgDismissed(boolean authenticated) {
        if (!authenticated) {
            Intent intent = new Intent(this, SplashScreenActivity.class);
            startActivity(intent);
        }
    }
}
