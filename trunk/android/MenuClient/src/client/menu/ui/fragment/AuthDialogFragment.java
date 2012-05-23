package client.menu.ui.fragment;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import client.menu.R;
import client.menu.ui.activity.MainMenuActivity;
import client.menu.ui.activity.WelcomeActivity;
import client.menu.util.U;

public class AuthDialogFragment extends DialogFragment {

    private Button mBtnOK;
    private Button mBtnCancel;

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnOK:
                    U.toastText(getActivity(), "Đang xây dựng!");

                    Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                    startActivity(intent);

                    dismiss();
                    break;
                case R.id.btnCancel:
                    dismiss();
                    break;
            }
        }
    };

    public static AuthDialogFragment newInstance() {
        AuthDialogFragment f = new AuthDialogFragment();

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.title_dialog_auth));
        getDialog().setCanceledOnTouchOutside(false);

        View layout = inflater.inflate(R.layout.dialog_authentication, container, false);

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBtnOK = (Button) getView().findViewById(R.id.btnOK);
        mBtnCancel = (Button) getView().findViewById(R.id.btnCancel);

        mBtnOK.setOnClickListener(mOnClickListener);
        mBtnCancel.setOnClickListener(mOnClickListener);
    }
}
