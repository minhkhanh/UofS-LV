package emenu.client.menu.fragment;

import org.apache.http.client.HttpClient;

import emenu.client.dao.AbstractDAO;
import emenu.client.menu.R;
import emenu.client.menu.activity.ConfigActivity;
import emenu.client.menu.activity.TableMapActivity;
import emenu.client.menu.fragment.AuthDlgFragment.OnAuthDlgDismissedListener;
import emenu.client.util.C;
import emenu.client.util.U;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class HomeNavigationDlgFragment extends DialogFragment implements OnClickListener,
        OnAuthDlgDismissedListener {

    public static final int ACT_CONFIG = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(R.string.hello);
        return inflater.inflate(R.layout.layout_home_navigation, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.btnTableMap).setOnClickListener(this);
        getView().findViewById(R.id.btnPreferences).setOnClickListener(this);
        getView().findViewById(R.id.btnConnection).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTableMap:
                Intent intent = new Intent(getActivity(), TableMapActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPreferences:
                // AuthDlgFragment authDlg = new AuthDlgFragment(this,
                // ACT_CONFIG);
                // U.showDlgFragment(getActivity(), authDlg, true);

                intent = new Intent(getActivity(), ConfigActivity.class);
                startActivity(intent);
                break;
            case R.id.btnConnection:
                ServerAddressDlgFragment d = new ServerAddressDlgFragment();
                U.showDlgFragment(getActivity(), d, true);
                break;

            default:
                break;
        }
    }

    @Override
    public void onAuthDlgDismissed(boolean authenticated) {
//        switch (action) {
//            case ACT_CONFIG:
////                Intent intent = new Intent(getActivity(), AppPreferenceActivity.class);
////                startActivity(intent);
//                break;
//
//            default:
//                break;
//        }
    }
}
