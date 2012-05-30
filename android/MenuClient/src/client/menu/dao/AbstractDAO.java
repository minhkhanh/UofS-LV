package client.menu.dao;

import android.database.sqlite.SQLiteDatabase;
import client.menu.db.util.MyDatabaseHelper;

public abstract class AbstractDAO {
    public static final String LOCAL_SERVER_URL = "http://192.168.56.1/RestService/LocalService.svc/";

    private MyDatabaseHelper mDbHelper = null;

    public AbstractDAO(MyDatabaseHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    protected SQLiteDatabase open() {
        return mDbHelper.getWritableDatabase();
    }

    protected void close() {
        mDbHelper.close();
    }

    public abstract boolean syncAll();

    public abstract String getSyncTaskName();
}
