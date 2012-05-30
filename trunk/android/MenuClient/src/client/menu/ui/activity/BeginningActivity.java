package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.List;

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
