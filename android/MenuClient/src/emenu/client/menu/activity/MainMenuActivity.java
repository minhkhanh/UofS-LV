package emenu.client.menu.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.LoadAllLanguageTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.R;
import emenu.client.menu.adapter.LanguageListAdapter;
import emenu.client.menu.app.CustomerLocale;
import emenu.client.menu.app.MenuApplication;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceSession;
import emenu.client.menu.fragment.CategoryListFragment;
import emenu.client.menu.fragment.DishListFragment;
import emenu.client.util.U;

public class MainMenuActivity extends Activity implements OnItemSelectedListener {

    private LanguageListAdapter mLangAdapter;
    private LoadAllLanguageTask mLoadLangTask;
    private long mBackFirstTime = 0;

    private OnPostExecuteListener<Void, Void, List<NgonNguDTO>> mOnPostLoadLang = new OnPostExecuteListener<Void, Void, List<NgonNguDTO>>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Void, Void, List<NgonNguDTO>> task,
                List<NgonNguDTO> result) {
            if (result.size() > 0) {
                mLangAdapter.clear();
                mLangAdapter.addAll(result);
                mLangAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_main_menu, menu);

        MenuItem item = menu.findItem(R.id.miDishSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setIconifiedByDefault(true);

        return true;
    }

    @Override
    public void onBackPressed() {
        long current = System.currentTimeMillis();
        if (mBackFirstTime == 0 || current - mBackFirstTime > 3000) {
            U.toastText(this, R.string.message_press_back_one_more_time, 3000);
            mBackFirstTime = current;
        } else {
            super.onBackPressed();
        }
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
        MenuApplication.getInstance().customerLocale.apply(this);
        setContentView(R.layout.layout_main_menu);

        mLangAdapter = new LanguageListAdapter(this, new ArrayList<NgonNguDTO>());
        Spinner s = (Spinner) findViewById(R.id.languageSpinner1);
        s.setAdapter(mLangAdapter);
        s.setOnItemSelectedListener(this);

        mLoadLangTask = new LoadAllLanguageTask();
        mLoadLangTask.setOnPostExecuteListener(mOnPostLoadLang).execute();

        View v = findViewById(android.R.id.content);
        v.setBackgroundResource(R.drawable.french_food_photo_eu030);

        createFragments();
    }

    private void createFragments() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = fm.findFragmentById(R.id.paneCatList);
        if (f == null) {
            ft.replace(R.id.paneCatList, new CategoryListFragment());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        CustomerLocale locale = MenuApplication.getInstance().customerLocale;
        NgonNguDTO currLang = locale.getLanguage();
        NgonNguDTO newLang = mLangAdapter.getItem(arg2);
        if (currLang.getKiHieu().compareTo(newLang.getKiHieu()) != 0) {
            locale.setLanguage(newLang);
            U.restartActivity(this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}
