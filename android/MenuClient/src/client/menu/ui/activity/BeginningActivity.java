package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

import client.menu.R;
import client.menu.db.dao.AbstractDAO;
import client.menu.db.dao.BanDAO;
import client.menu.db.dao.DanhMucDAO;
import client.menu.db.dao.DanhMucDaNgonNguDAO;
import client.menu.db.dao.DonViTinhDAO;
import client.menu.db.dao.DonViTinhDaNgonNguDAO;
import client.menu.db.dao.DonViTinhMonAnDAO;
import client.menu.db.dao.KhuVucDAO;
import client.menu.db.dao.MonAnDAO;
import client.menu.db.dao.MonAnDaNgonNguDAO;
import client.menu.db.dao.MonLienQuanDAO;
import client.menu.db.dao.NgonNguDAO;
import client.menu.db.dao.NhomTaiKhoanDAO;
import client.menu.db.dao.TaiKhoanDAO;
import client.menu.db.dao.TiGiaDAO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.svc.SyncService;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class BeginningActivity extends Activity {

    ProgressDialog mDlg;

    class SyncDataTask extends AsyncTask<Void, String, Boolean> {
        List<AbstractDAO> mDaoList = new ArrayList<AbstractDAO>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDaoList.add(KhuVucDAO.getInstance());
            mDaoList.add(BanDAO.getInstance());
            mDaoList.add(NgonNguDAO.getInstance());
            mDaoList.add(DanhMucDAO.getInstance());
            mDaoList.add(DonViTinhDAO.getInstance());
            mDaoList.add(DonViTinhDaNgonNguDAO.getInstance());
            mDaoList.add(DanhMucDaNgonNguDAO.getInstance());
            mDaoList.add(MonAnDAO.getInstance());
            mDaoList.add(DonViTinhMonAnDAO.getInstance());
            mDaoList.add(MonAnDaNgonNguDAO.getInstance());
            mDaoList.add(TiGiaDAO.getInstance());
            mDaoList.add(MonLienQuanDAO.getInstance());
            mDaoList.add(NhomTaiKhoanDAO.getInstance());
            mDaoList.add(TaiKhoanDAO.getInstance());
            
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            mDlg.cancel();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            mDlg.setMessage(values[0] + " ...");
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            for (AbstractDAO a : mDaoList) {
                publishProgress(a.getSyncTaskName());

                if (a.syncAll() == false) {
                    return false;
                }
            }

            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_beginning);

    }

    public void onChildClick(View v) {
        SyncDataTask task = new SyncDataTask();
        mDlg = new ProgressDialog(this);
        mDlg.setCancelable(false);
        mDlg.setMessage("Loading ...");
        mDlg.show();
        task.execute();
    }
}
