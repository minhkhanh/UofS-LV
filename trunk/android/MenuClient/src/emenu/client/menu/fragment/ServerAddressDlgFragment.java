package emenu.client.menu.fragment;

import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetTestServerTask;
import emenu.client.dao.AbstractDAO;
import emenu.client.menu.R;
import emenu.client.util.C;
import emenu.client.util.U;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class ServerAddressDlgFragment extends DialogFragment implements OnClickListener,
        OnPostExecuteListener<String, Void, String> {
    public static final String KEY_PREF_CONNECTION = "KEY_PREF_CONNECTION";

    private EditText mServAddrEdit;
    private GetTestServerTask mTestServerTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(R.string.text_server_address);
        return inflater.inflate(R.layout.layout_connection_dlg, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mServAddrEdit = (EditText) getView().findViewById(R.id.editServerAddr);

        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                C.SHARED_PREF_FILE, 0);
        String servAddr = sharedPref.getString(KEY_PREF_CONNECTION,
                AbstractDAO.SERVER_URL_SLASH);

        mServAddrEdit.setText(servAddr);

        getView().findViewById(R.id.btnOK).setOnClickListener(this);
        getView().findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                String servAddr = mServAddrEdit.getText().toString();
                if (servAddr != null) {
                    if (!servAddr.endsWith("/")) {
                        servAddr += "/";
                    }
                    if (!servAddr.startsWith(C.SERVER_PREFIX)) {
                        servAddr = C.SERVER_PREFIX + servAddr;
                    }
                    mServAddrEdit.setText(servAddr);

                    U.cancelAsyncTask(mTestServerTask);
                    mTestServerTask = new GetTestServerTask();
                    mTestServerTask.setOnPostExecuteListener(this);
                    mTestServerTask
                            .setWaitingDialog(U.createWaitingDialog(getActivity()));
                    mTestServerTask.execute(servAddr);

                    AbstractDAO.SERVER_URL_SLASH = servAddr;
                }
                break;

            case R.id.btnCancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onPostExecute(CustomAsyncTask<String, Void, String> task, String result) {
        if (result != null) {
            new AlertDialog.Builder(getActivity()).setMessage(result)
                    .setOnCancelListener(new OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface arg0) {
                            dismiss();
                        }
                    }).create().show();

            SharedPreferences sharedPref = getActivity().getSharedPreferences(
                    C.SHARED_PREF_FILE, 0);
            SharedPreferences.Editor editor = sharedPref.edit();

            String servAddr = mServAddrEdit.getText().toString();

            editor.putString(KEY_PREF_CONNECTION, servAddr);
            editor.commit();

            AbstractDAO.SERVER_URL_SLASH = servAddr;

        } else {
            U.showErrorDialog(getActivity(), R.string.message_connect_server_failed);
        }
    }
}
