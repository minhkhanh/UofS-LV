package emenu.client.menu.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;
import emenu.client.dao.AbstractDAO;
import emenu.client.dao.BanDAO;
import emenu.client.dao.DanhMucDAO;
import emenu.client.dao.DanhMucDaNgonNguDAO;
import emenu.client.dao.DonViTinhDAO;
import emenu.client.dao.DonViTinhDaNgonNguDAO;
import emenu.client.dao.DonViTinhMonAnDAO;
import emenu.client.dao.HoaDonDAO;
import emenu.client.dao.KhuVucDAO;
import emenu.client.dao.KhuyenMaiDAO;
import emenu.client.dao.KhuyenMaiMonDAO;
import emenu.client.dao.MonAnDAO;
import emenu.client.dao.MonAnDaNgonNguDAO;
import emenu.client.dao.MonLienQuanDAO;
import emenu.client.dao.NgonNguDAO;
import emenu.client.dao.NhomTaiKhoanDAO;
import emenu.client.dao.OrderDAO;
import emenu.client.dao.PhuThuDAO;
import emenu.client.dao.PhuThuKhuVucDAO;
import emenu.client.dao.TaiKhoanDAO;
import emenu.client.dao.TiGiaDAO;
import emenu.client.dao.VoucherDAO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.menu.R;
import emenu.client.menu.fragment.ServerAddressDlgFragment;
import emenu.client.util.C;
import emenu.client.util.MyHttpClient;

public class MenuApplication extends Application {
    private static MenuApplication mInstance;

    public static final MenuApplication getInstance() {
        return mInstance;
    }

    public CustomerLocale customerLocale;
    public MyDatabaseHelper dbOpener;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(C.TAG, "config changed");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        SessionManager.createInstance();

        initDAOs();

        customerLocale = CustomerLocale.getDefaultLocale();
        customerLocale.apply(getApplicationContext());
    }

    private void initDAOs() {
        dbOpener = new MyDatabaseHelper(this);

        SharedPreferences sharedPref = getSharedPreferences(C.SHARED_PREF_FILE, 0);

        String key = getString(R.string.key_pref_server_address);
        String servAddr = sharedPref.getString(key, AbstractDAO.SERVER_URL_SLASH);
        AbstractDAO.SERVER_URL_SLASH = servAddr;

        BanDAO.createInstance(dbOpener);
        DanhMucDaNgonNguDAO.createInstance(dbOpener);
        DanhMucDAO.createInstance(dbOpener);
        DonViTinhDaNgonNguDAO.createInstance(dbOpener);
        DonViTinhDAO.createInstance(dbOpener);
        DonViTinhMonAnDAO.createInstance(dbOpener);
        KhuVucDAO.createInstance(dbOpener);
        MonAnDaNgonNguDAO.createInstance(dbOpener);
        MonAnDAO.createInstance(dbOpener);
        MonLienQuanDAO.createInstance(dbOpener);
        NgonNguDAO.createInstance(dbOpener);
        NhomTaiKhoanDAO.createInstance(dbOpener);
        TaiKhoanDAO.createInstance(dbOpener);
        TiGiaDAO.createInstance(dbOpener);
        OrderDAO.createInstance(dbOpener);
        HoaDonDAO.createInstance(dbOpener);
        VoucherDAO.createInstance(dbOpener);
        KhuyenMaiDAO.createInstance(dbOpener);
        KhuyenMaiMonDAO.createInstance(dbOpener);
        PhuThuDAO.createInstance(dbOpener);
        PhuThuKhuVucDAO.createInstance(dbOpener);

        // AbstractDAO.createHttpClient(getApplicationContext())
        MyHttpClient.createKeyStore(getApplicationContext());
    }
}
