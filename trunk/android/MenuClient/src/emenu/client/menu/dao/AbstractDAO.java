package emenu.client.menu.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.util.MyDatabaseHelper;

public abstract class AbstractDAO {
    public static String LOCAL_SERVER_URL = "http://192.168.56.1/RestService/LocalService.svc/";
//    public static final String LOCAL_SERVER_URL = "http://10.0.2.2:5252/LocalService.svc/";

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

    public Cursor cursorAll(String tableName) {
        SQLiteDatabase db = open();
        return db.query(tableName, null, null, null, null, null, null, null);
    }

    public abstract boolean syncAll();

    public abstract String getName();

    protected abstract void createCache(Cursor cursor);

    public boolean loadCachedData() {
        boolean flag = true;
        SQLiteDatabase db = null;
        try {
            db = open();
            Cursor cursorAll = db.query(BanDTO.TABLE_NAME, null, null, null, null, null,
                    null, null);
            createCache(cursorAll);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return flag;
    }
}
