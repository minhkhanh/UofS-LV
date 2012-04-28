package client.menu.db.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import client.menu.db.contract.DanhMucContract;
import client.menu.db.contract.DonViTinhContract;
import client.menu.db.contract.MonAnContract;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    
    private static final String DB_NAME = "ThucDonDienTu.db";
    private static final int DB_VERSION = 1;
    
    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DonViTinhContract.SQL_CREATE);
        db.execSQL(DanhMucContract.SQL_CREATE);
        db.execSQL(MonAnContract.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbHelper", "onUpgrade");
    }

}
