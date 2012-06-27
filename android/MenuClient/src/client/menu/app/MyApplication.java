package client.menu.app;

import client.menu.bus.SessionManager;
import client.menu.dao.BanDAO;
import client.menu.dao.DanhMucDAO;
import client.menu.dao.DanhMucDaNgonNguDAO;
import client.menu.dao.DonViTinhDAO;
import client.menu.dao.DonViTinhDaNgonNguDAO;
import client.menu.dao.DonViTinhMonAnDAO;
import client.menu.dao.HoaDonDAO;
import client.menu.dao.KhuVucDAO;
import client.menu.dao.MonAnDAO;
import client.menu.dao.MonAnDaNgonNguDAO;
import client.menu.dao.MonLienQuanDAO;
import client.menu.dao.NgonNguDAO;
import client.menu.dao.NhomTaiKhoanDAO;
import client.menu.dao.OrderDAO;
import client.menu.dao.TaiKhoanDAO;
import client.menu.dao.TiGiaDAO;
import client.menu.dao.VoucherDAO;
import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.C;
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
