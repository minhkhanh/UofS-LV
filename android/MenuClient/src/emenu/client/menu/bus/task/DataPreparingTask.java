package emenu.client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import emenu.client.menu.dao.AbstractDAO;
import emenu.client.menu.dao.BanDAO;
import emenu.client.menu.dao.DanhMucDAO;
import emenu.client.menu.dao.DanhMucDaNgonNguDAO;
import emenu.client.menu.dao.DonViTinhDAO;
import emenu.client.menu.dao.DonViTinhDaNgonNguDAO;
import emenu.client.menu.dao.DonViTinhMonAnDAO;
import emenu.client.menu.dao.KhuVucDAO;
import emenu.client.menu.dao.MonAnDAO;
import emenu.client.menu.dao.MonAnDaNgonNguDAO;
import emenu.client.menu.dao.MonLienQuanDAO;
import emenu.client.menu.dao.NgonNguDAO;
import emenu.client.menu.dao.NhomTaiKhoanDAO;
import emenu.client.menu.dao.TaiKhoanDAO;
import emenu.client.menu.dao.TiGiaDAO;

public abstract class DataPreparingTask extends CustomAsyncTask<Void, String, Boolean> {

    List<AbstractDAO> mDaoList = new ArrayList<AbstractDAO>();
    ProgressDialog mProgessDialog;

    public DataPreparingTask(ProgressDialog dlg) {
        mProgessDialog = dlg;
    }

    protected abstract boolean workCore(AbstractDAO dao);

    @Override
    protected Boolean doInBackground(Void... params) {
        for (AbstractDAO a : mDaoList) {
            publishProgress(a.getName());

            if (workCore(a) == false) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        mProgessDialog.cancel();
    }

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

        mProgessDialog.setCancelable(false);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        mProgessDialog.setMessage(values[0] + " ...");
    }

    public ProgressDialog getProgessDialog() {
        return mProgessDialog;
    }

}