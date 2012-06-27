package emenu.client.bus.task;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import emenu.client.dao.AbstractDAO;
import emenu.client.menu.R;
import emenu.client.menu.util.C;
import emenu.client.menu.util.U;

public class SyncDbTask extends DataPreparingTask {

    public SyncDbTask(ProgressDialog dlg) {
        super(dlg);
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        for (AbstractDAO a : mDaoList) {
            publishProgress(a.getName());

            if (a.syncAll() == false) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (result) {
            U.toastText(mProgessDialog.getContext(), R.string.message_sync_succeed);

            SharedPreferences.Editor editor = mProgessDialog.getContext().getSharedPreferences(
                    C.SHARED_PREF_FILE, 0).edit();

            String key = mProgessDialog.getContext().getString(R.string.key_pref_sync_now);
            editor.putLong(key, System.currentTimeMillis());
            editor.commit();
        } else {
            new AlertDialog.Builder(mProgessDialog.getContext())
                    .setMessage(R.string.message_sync_failed).create().show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        getProgessDialog().setMessage(
                mProgessDialog.getContext().getString(R.string.text_loading) + " ...");
        getProgessDialog().show();
    }

    @Override
    protected boolean workCore(AbstractDAO dao) {
        return dao.syncAll();
    }
}
