package client.menu.db.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import client.menu.db.contract.BanContract;
import client.menu.db.contract.ChiTietOrderContract;
import client.menu.db.contract.DanhMucContract;
import client.menu.db.contract.DanhMucDaNgonNguContract;
import client.menu.db.contract.DonViTinhContract;
import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.KhuVucContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.contract.NgonNguContract;
import client.menu.db.contract.NhomTaiKhoanContract;
import client.menu.db.contract.OrderContract;
import client.menu.db.contract.TaiKhoanContract;
import client.menu.db.contract.ThamSoContract;
import client.menu.util.C;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    
    private static final String DB_NAME = "ttdt.db";
    private static final int DB_VERSION = 1;
    
    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NgonNguContract.SQL_CREATE);        
        db.execSQL(DonViTinhContract.SQL_CREATE);
        db.execSQL(DanhMucContract.SQL_CREATE);        
        db.execSQL(KhuVucContract.SQL_CREATE);
        db.execSQL(BanContract.SQL_CREATE);        
        db.execSQL(MonAnContract.SQL_CREATE);
        db.execSQL(ThamSoContract.SQL_CREATE);        
        db.execSQL(DanhMucDaNgonNguContract.SQL_CREATE);
        db.execSQL(DonViTinhDaNgonNguContract.SQL_CREATE);        
        db.execSQL(MonAnDaNgonNguContract.SQL_CREATE);
        db.execSQL(DonViTinhMonAnContract.SQL_CREATE);        
        db.execSQL(NhomTaiKhoanContract.SQL_CREATE);
        db.execSQL(OrderContract.SQL_CREATE);
        db.execSQL(TaiKhoanContract.SQL_CREATE);
        db.execSQL(ChiTietOrderContract.SQL_CREATE);
        
        Log.d(C.TAG, "MyDatabaseHelper.onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(C.TAG, "MyDatabaseHelper.onUpgrade");
    }

}
