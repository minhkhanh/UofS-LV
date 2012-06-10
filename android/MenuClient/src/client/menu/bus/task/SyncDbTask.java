package client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import client.menu.R;
import client.menu.dao.AbstractDAO;
import client.menu.dao.BanDAO;
import client.menu.dao.DanhMucDAO;
import client.menu.dao.DanhMucDaNgonNguDAO;
import client.menu.dao.DonViTinhDAO;
import client.menu.dao.DonViTinhDaNgonNguDAO;
import client.menu.dao.DonViTinhMonAnDAO;
import client.menu.dao.KhuVucDAO;
import client.menu.dao.MonAnDAO;
import client.menu.dao.MonAnDaNgonNguDAO;
import client.menu.dao.MonLienQuanDAO;
import client.menu.dao.NgonNguDAO;
import client.menu.dao.NhomTaiKhoanDAO;
import client.menu.dao.TaiKhoanDAO;
import client.menu.dao.TiGiaDAO;
import client.menu.util.C;
import client.menu.util.U;

public class SyncDbTask extends DataPreparingTask {

    public SyncDbTask(Context context) {
        super(context);
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
            U.toastText(getContext(), R.string.message_sync_succeed);

            SharedPreferences.Editor editor = getContext().getSharedPreferences(
                    C.SHARED_PREF_FILE, 0).edit();

            String key = getContext().getString(R.string.key_pref_sync_now);
            editor.putLong(key, System.currentTimeMillis());
            editor.commit();
        } else {
            new AlertDialog.Builder(getContext())
                    .setMessage(R.string.message_sync_failed).create().show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        getProgessDialog().setMessage(
                getContext().getString(R.string.text_loading) + " ...");
        getProgessDialog().show();
    }

    @Override
    protected boolean workCore(AbstractDAO dao) {
        return dao.syncAll();
    }
}
