package emenu.client.menu.app;

import emenu.client.dao.BanDAO;
import emenu.client.dao.DanhMucDAO;
import emenu.client.dao.DanhMucDaNgonNguDAO;
import emenu.client.dao.DonViTinhDAO;
import emenu.client.dao.DonViTinhDaNgonNguDAO;
import emenu.client.dao.DonViTinhMonAnDAO;
import emenu.client.dao.HoaDonDAO;
import emenu.client.dao.KhuVucDAO;
import emenu.client.dao.MonAnDAO;
import emenu.client.dao.MonAnDaNgonNguDAO;
import emenu.client.dao.MonLienQuanDAO;
import emenu.client.dao.NgonNguDAO;
import emenu.client.dao.NhomTaiKhoanDAO;
import emenu.client.dao.OrderDAO;
import emenu.client.dao.TaiKhoanDAO;
import emenu.client.dao.TiGiaDAO;
import emenu.client.dao.VoucherDAO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.menu.bus.SessionManager;
import emenu.client.menu.util.C;
import android.app.Activity;
import android.app.Application;

public class MenuApplication extends Application {
    private static final String EX_MSG_01 = "Can not get MyApplication object from the activity parameter.";

    private static MenuApplication mInstance;

    public MyAppSettings settings;
    public MyDatabaseHelper dbOpener;

    public static final MyAppSettings getSettings(Activity activity) {
        MenuApplication app = (MenuApplication) activity.getApplication();
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

    public static final MenuApplication getInstance() {
        return mInstance;
    }

    public MyAppSettings getSettings() {
        return settings;
    }
}
