package emenu.client.menu.ui.fragment;

import emenu.client.menu.R;
import emenu.client.menu.ui.activity.AppPreferenceActivity;
import emenu.client.menu.ui.activity.TableMapActivity;
import emenu.client.menu.util.U;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class HomeNavigationDlgFragment extends DialogFragment implements OnClickListener {
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTableMap:
                Intent intent = new Intent(getActivity(), TableMapActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPreferences:
                AuthDialogFragment dlg = new AuthDialogFragment(AuthDialogFragment.ACT_SELECTING_TABLE);
                U.showDlgFragment(getActivity(), dlg, "dlg");
                
//                intent = new Intent(getActivity(), AppPreferenceActivity.class);
//                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
