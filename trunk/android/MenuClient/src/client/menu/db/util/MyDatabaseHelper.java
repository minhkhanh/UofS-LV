package client.menu.db.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import client.menu.db.contract.BanContract;
import client.menu.db.contract.DanhMucContract;
import client.menu.db.contract.DanhMucDaNgonNguContract;
import client.menu.db.contract.DonViTinhContract;
import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.KhuVucContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.contract.NgonNguContract;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    
    private static final String DB_NAME = "ThucDonDienTu.db";
    private static final int DB_VERSION = 1;
    
    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NgonNguContract.SQL_CREATE);
        
        db.execSQL(DonViTinhContract.SQL_CREATE);
        db.execSQL(DanhMucContract.SQL_CREATE);        
        db.execSQL(DanhMucDaNgonNguContract.SQL_CREATE);
        db.execSQL(DonViTinhDaNgonNguContract.SQL_CREATE);
        
        db.execSQL(MonAnContract.SQL_CREATE);        
        db.execSQL(DonViTinhMonAnContract.SQL_CREATE);
        db.execSQL(MonAnDaNgonNguContract.SQL_CREATE);
        
        db.execSQL(KhuVucContract.SQL_CREATE);
        db.execSQL(BanContract.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbHelper", "onUpgrade");
    }

}
