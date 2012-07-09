package emenu.client.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.util.MyDatabaseHelper;

public abstract class AbstractDAO {
//    private static MyHttpClient mHttpClient;
//
//    public static void createHttpClient(Context context) {
//        mHttpClient = new MyHttpClient(context);
//    }
//
//    public static MyHttpClient getHttpClient() {
//        if (mHttpClient == null)
//            throw new NullPointerException("Singleton instance not created yet.");
//
//        return mHttpClient;
//    }

    public static String SERVER_URL_SLASH = "https://192.168.56.1/RestService/LocalService.svc"
            + "/";

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
