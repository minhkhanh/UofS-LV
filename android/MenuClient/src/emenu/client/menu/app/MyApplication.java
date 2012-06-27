package emenu.client.menu.app;

import emenu.client.menu.bus.SessionManager;
import emenu.client.menu.dao.BanDAO;
import emenu.client.menu.dao.DanhMucDAO;
import emenu.client.menu.dao.DanhMucDaNgonNguDAO;
import emenu.client.menu.dao.DonViTinhDAO;
import emenu.client.menu.dao.DonViTinhDaNgonNguDAO;
import emenu.client.menu.dao.DonViTinhMonAnDAO;
import emenu.client.menu.dao.HoaDonDAO;
import emenu.client.menu.dao.KhuVucDAO;
import emenu.client.menu.dao.MonAnDAO;
import emenu.client.menu.dao.MonAnDaNgonNguDAO;
import emenu.client.menu.dao.MonLienQuanDAO;
import emenu.client.menu.dao.NgonNguDAO;
import emenu.client.menu.dao.NhomTaiKhoanDAO;
import emenu.client.menu.dao.OrderDAO;
import emenu.client.menu.dao.TaiKhoanDAO;
import emenu.client.menu.dao.TiGiaDAO;
import emenu.client.menu.dao.VoucherDAO;
import emenu.client.menu.db.util.MyDatabaseHelper;
import emenu.client.menu.util.C;
import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
    private static final String EX_MSG_01 = "Can not get MyApplication object from the activity parameter.";

    private static MyApplication mInstance;

    public MyAppSettings settings;
    public MyDatabaseHelper dbOpener;

    public static final MyAppSettings getSettings(Activity activity) {
        MyApplication app = (MyApplication) activity.getApplication();
        if (app != null) {
            return app.settings;
        }

        throw new IllegalArgumentException(C.TAG + EX_MSG_01);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        initDAOs();

        settings = new MyAppSettings(this);
        SessionManager.createInstance();

    }

    private void initDAOs() {
        dbOpener = new MyDatabaseHelper(this);

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
    }

    public static final MyApplication getInstance() {
        return mInstance;
    }

    public MyAppSettings getSettings() {
        return settings;
    }
}
