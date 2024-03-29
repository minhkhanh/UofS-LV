package emenu.client.menu.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.PostLogInTask;
import emenu.client.menu.R;
import emenu.client.util.U;

public class AuthDlgFragment extends DialogFragment implements OnClickListener {

    public static final String KEY_ACTION = "key_action";

    private EditText mNameEdit;
    private EditText mPassEdit;
    private OnAuthDlgDismissedListener mOnAuthorizedListener;
    private PostLogInTask mLogInTask;
    private boolean mAuthenticated;

    private OnPostExecuteListener<Void, Void, Boolean> mOnPostLogIn = new OnPostExecuteListener<Void, Void, Boolean>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task,
                Boolean result) {
            mAuthenticated = result;
            if (result) {
                U.toastText(getActivity(), R.string.message_auth_succeed);
                dismiss();
            } else {
                U.toastText(getActivity(), R.string.message_auth_failed);
            }
        }
    };

    public interface OnAuthDlgDismissedListener {
        void onAuthDlgDismissed(boolean authenticated);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mOnAuthorizedListener = (OnAuthDlgDismissedListener) activity;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        mOnAuthorizedListener.onAuthDlgDismissed(mAuthenticated);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.title_dialog_auth));
        getDialog().setCanceledOnTouchOutside(false);
        setCancelable(false);

        View layout = inflater.inflate(R.layout.layout_auth_dlg, container, false);

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.btnOK).setOnClickListener(this);;
        getView().findViewById(R.id.btnCancel).setOnClickListener(this);

        mNameEdit = (EditText) getView().findViewById(R.id.editUsername);
        mPassEdit = (EditText) getView().findViewById(R.id.editPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                U.cancelAsyncTask(mLogInTask);

                String name = mNameEdit.getText().toString();
                String pass = mPassEdit.getText().toString();
                mLogInTask = new PostLogInTask(name, pass);
                mLogInTask.setOnPostExecuteListener(mOnPostLogIn);
                mLogInTask.setWaitingDialog(U.createWaitingDialog(getActivity()));
                mLogInTask.execute();

                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
