package emenu.client.menu.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import emenu.client.menu.R;
import emenu.client.menu.fragment.AreaListFragment;
import emenu.client.menu.fragment.AuthDlgFragment.OnAuthDlgDismissedListener;
import emenu.client.util.U;

public class TableMapActivity extends Activity implements OnAuthDlgDismissedListener {
    public static final String KEY_MOVING_ORDER_ID = "KEY_MOVING_ORDER_ID";

    private Integer mMovingOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovingOrderId = getIntent().getIntExtra(KEY_MOVING_ORDER_ID, -1);

        setContentView(R.layout.layout_two_panes);

        View v = findViewById(android.R.id.content);
        v.setBackgroundResource(R.drawable.romantic_mood_wallpaper_070);

        FragmentManager fm = getFragmentManager();
        AreaListFragment f = (AreaListFragment) fm.findFragmentById(R.id.LeftPaneHolder);
        if (f == null) {
            f = new AreaListFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.LeftPaneHolder, f);
            ft.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mMovingOrderId == -1)
            U.showAuthDlg(this, getFragmentManager(), null);
    }

    @Override
    public void onAuthDlgDismissed(boolean authenticated) {
        if (!authenticated) {
            Intent intent = new Intent(this, SplashScreenActivity.class);
            startActivity(intent);
        }
    }

    public Integer getMovingOrderId() {
        return mMovingOrderId;
    }

    public void setMovingOrderId(Integer movingOrderId) {
        mMovingOrderId = movingOrderId;
    }
}
