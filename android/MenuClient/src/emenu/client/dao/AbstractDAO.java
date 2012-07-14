package emenu.client.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.util.MyDatabaseHelper;

public abstract class AbstractDAO {

    public static String SERVER_URL_SLASH = "https://192.168.56.1/RestService/LocalService.svc"
            + "/"; // always has ended slash

    public static final String getServerDomain() {
        int i = SERVER_URL_SLASH.indexOf("://") + 3;
        int j = SERVER_URL_SLASH.indexOf('/', i);

        return SERVER_URL_SLASH.substring(i, j);
    }

    private MyDatabaseHelper mDbHelper = null;

    public AbstractDAO(MyDatabaseHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    protected SQLiteDatabase open() {
        return mDbHelper.getWritableDatabase();
    }

    // protected void close() {
    // mDbHelper.close();
    // }

    public Cursor cursorAll(String tableName) {
        SQLiteDatabase db = open();
        return db.query(tableName, null, null, null, null, null, null, null);
    }

    public abstract boolean syncAll();

    public abstract String getName();

}
