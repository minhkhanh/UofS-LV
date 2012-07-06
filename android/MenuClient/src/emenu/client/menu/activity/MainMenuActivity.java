package emenu.client.menu.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import emenu.client.menu.R;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceSession;
import emenu.client.menu.fragment.CategoryListFragment;
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
//            case R.id.miPayment:
//                if (session.isFinished())
//                    U.toastText(this, R.string.message_your_service_session_finished);
//                else {
//                    Intent intent = new Intent(MainMenuActivity.this, BillActivity.class);
//                    startActivity(intent);
//                }
//                break;
        }

        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_two_panes);

        View v = findViewById(android.R.id.content);
        v.setBackgroundResource(R.drawable.french_food_photo_eu030);

        FragmentManager fm = getFragmentManager();
        CategoryListFragment f = (CategoryListFragment) fm
                .findFragmentByTag("CategoryListFragment");
        if (f == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.LeftPaneHolder, new CategoryListFragment(),
                    "CategoryListFragment");
            ft.commit();
        }
    }
}
