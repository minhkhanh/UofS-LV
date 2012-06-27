package client.menu.app;

import client.menu.bus.SessionManager;
import client.menu.db.dao.BanDAO;
import client.menu.db.dao.DanhMucDAO;
import client.menu.db.dao.DanhMucDaNgonNguDAO;
import client.menu.db.dao.DonViTinhDAO;
import client.menu.db.dao.DonViTinhDaNgonNguDAO;
import client.menu.db.dao.DonViTinhMonAnDAO;
import client.menu.db.dao.HoaDonDAO;
import client.menu.db.dao.KhuVucDAO;
import client.menu.db.dao.MonAnDAO;
import client.menu.db.dao.MonAnDaNgonNguDAO;
import client.menu.db.dao.MonLienQuanDAO;
import client.menu.db.dao.NgonNguDAO;
import client.menu.db.dao.NhomTaiKhoanDAO;
import client.menu.db.dao.OrderDAO;
import client.menu.db.dao.TaiKhoanDAO;
import client.menu.db.dao.TiGiaDAO;
import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.C;
import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
    private static final String EX_MSG_01 = "Can not get MyApplication object from the activity parameter.";

    private MyAppSettings mSettings;

    public static final MyAppSettings getSettings(Activity activity) {
        MyApplication app = (MyApplication) activity.getApplication();
        if (app != null) {
            return app.mSettings;
        }

        throw new IllegalArgumentException(C.TAG + EX_MSG_01);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initDAOs();
        
        mSettings = new MyAppSettings(this);
        SessionManager.createInstance();
        
    }
    
    private void initDAOs() {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);

        BanDAO.createInstance(dbHelper);
        DanhMucDaNgonNguDAO.createInstance(dbHelper);
        DanhMucDAO.createInstance(dbHelper);
        DonViTinhDaNgonNguDAO.createInstance(dbHelper);
        DonViTinhDAO.createInstance(dbHelper);
        DonViTinhMonAnDAO.createInstance(dbHelper);
        KhuVucDAO.createInstance(dbHelper);
        MonAnDaNgonNguDAO.createInstance(dbHelper);
        MonAnDAO.createInstance(dbHelper);
        MonLienQuanDAO.createInstance(dbHelper);
        NgonNguDAO.createInstance(dbHelper);
        NhomTaiKhoanDAO.createInstance(dbHelper);
        TaiKhoanDAO.createInstance(dbHelper);
        TiGiaDAO.createInstance(dbHelper);
        OrderDAO.createInstance(dbHelper);
        HoaDonDAO.createInstance(dbHelper);
    }
}
