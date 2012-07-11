package emenu.client.menu.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import emenu.client.menu.R;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceSession;
import emenu.client.menu.fragment.CategoryListFragment;
import emenu.client.menu.fragment.DishListFragment;
import emenu.client.util.U;

public class MainMenuActivity extends Activity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_main_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        U.showConfirmDialog(this, R.string.message_you_are_leaving_main_menu,
                new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainMenuActivity.super.onBackPressed();
                    }
                });
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        ServiceSession session = SessionManager.getInstance().loadCurrentSession();

        switch (item.getItemId()) {
            case R.id.miViewOrder:
                if (session.isFinished())
                    U.toastText(this, R.string.message_your_service_session_finished);
                else {
                    Intent intent = new Intent(this, OrderActivity.class);
                    startActivity(intent);
                }
                break;
        }

        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_two_panes);

        View v = findViewById(android.R.id.content);
        v.setBackgroundResource(R.drawable.french_food_photo_eu030);

//        ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        FragmentManager fm = getFragmentManager();
        CategoryListFragment catList = (CategoryListFragment) fm
                .findFragmentById(R.id.LeftPaneHolder);
        if (catList == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.LeftPaneHolder, new CategoryListFragment());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        DishListFragment dishList = (DishListFragment) fm
                .findFragmentById(R.id.RightPaneHolder);
        if (dishList == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.RightPaneHolder, new DishListFragment(0));
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }
}
