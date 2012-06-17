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
        
        mInstance = this;

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
        VoucherDAO.createInstance(dbHelper);
    }

    public static final MyApplication getInstance() {
        return mInstance;
    }

    public MyAppSettings getSettings() {
        return mSettings;
    }
}
