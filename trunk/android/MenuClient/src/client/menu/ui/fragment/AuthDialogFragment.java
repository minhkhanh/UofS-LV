package client.menu.ui.fragment;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.dao.BanDAO;
import client.menu.db.dto.BanDTO;
import client.menu.ui.activity.WelcomeActivity;
import client.menu.util.U;

public class AuthDialogFragment extends DialogFragment {

    public static final int ACT_SELECTING_TABLE = 0;

    private Button mBtnOK;
    private Button mBtnCancel;
    private int mAction;

    private ProgressDialog mWatingDlg;

    class PutTableTask extends AsyncTask<BanDTO, Void, Void> {

        boolean mResult;

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            mWatingDlg.dismiss();

            if (mResult) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                dismiss();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(getActivity().getResources().getString(
                        R.string.message_connect_server_failed));

                AlertDialog alert = builder.create();
                alert.show();
            }
        }

        @Override
        protected Void doInBackground(BanDTO... params) {
            mResult = BanDAO.getInstance().putUpdate(params[0]);
            return null;
        }

    };

    public AuthDialogFragment(int action) {
        mAction = action;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnOK:
                    U.toastText(getActivity(), "Xác thực thành công!");
                    if (mAction == ACT_SELECTING_TABLE) {
//                        BanDTO ban = SessionManager.getInstance().loadCurrentSession()
//                                .getMaBanChinh();
//                        ban.setActive(false);
//
//                        mWatingDlg = ProgressDialog.show(getActivity(), "", "Wating...");
//                        new PutTableTask().execute(ban);
                    }

                    break;
                case R.id.btnCancel:
                    dismiss();
                    break;
            }
        }
    };

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
