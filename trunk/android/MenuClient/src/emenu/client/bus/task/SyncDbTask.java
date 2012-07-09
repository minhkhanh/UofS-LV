package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import emenu.client.dao.AbstractDAO;
import emenu.client.dao.BanDAO;
import emenu.client.dao.DanhMucDAO;
import emenu.client.dao.DanhMucDaNgonNguDAO;
import emenu.client.dao.DonViTinhDAO;
import emenu.client.dao.DonViTinhDaNgonNguDAO;
import emenu.client.dao.DonViTinhMonAnDAO;
import emenu.client.dao.KhuVucDAO;
import emenu.client.dao.KhuyenMaiDAO;
import emenu.client.dao.KhuyenMaiMonDAO;
import emenu.client.dao.MonAnDAO;
import emenu.client.dao.MonAnDaNgonNguDAO;
import emenu.client.dao.MonLienQuanDAO;
import emenu.client.dao.NgonNguDAO;
import emenu.client.dao.NhomTaiKhoanDAO;
import emenu.client.dao.PhuThuDAO;
import emenu.client.dao.PhuThuKhuVucDAO;
import emenu.client.dao.TaiKhoanDAO;
import emenu.client.dao.TiGiaDAO;
import emenu.client.menu.R;
import emenu.client.util.C;
import emenu.client.util.U;

public class SyncDbTask extends CustomAsyncTask<Void, String, Boolean> {

    private List<AbstractDAO> mDaoList = new ArrayList<AbstractDAO>();
    private Context mContext;

    public SyncDbTask(Context context) {
        mContext = context;
        setWaitingDialog(U.createWaitingDialog(context));
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
        mDaoList.add(KhuyenMaiDAO.getInstance());
        mDaoList.add(KhuyenMaiMonDAO.getInstance());
        mDaoList.add(PhuThuDAO.getInstance());
        mDaoList.add(PhuThuKhuVucDAO.getInstance());
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        getWaitingDialog().setMessage(values[0] + " ...");
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            for (AbstractDAO a : mDaoList) {
                publishProgress(a.getName());

                if (a.syncAll() == false) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (result) {
            U.toastText(mContext, R.string.message_sync_succeed);

            SharedPreferences.Editor editor = mContext.getSharedPreferences(
                    C.SHARED_PREF_FILE, 0).edit();

            String key = mContext.getString(R.string.key_pref_sync_now);
            editor.putLong(key, System.currentTimeMillis());
            editor.commit();
        } else {
            U.showErrorDialog(mContext, R.string.message_sync_failed);
        }
    }
}
