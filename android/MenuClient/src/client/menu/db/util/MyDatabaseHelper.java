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
        Log.d(C.TAG, "MyDatabaseHelper.onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(C.TAG, "MyDatabaseHelper.onUpgrade");
    }

}
