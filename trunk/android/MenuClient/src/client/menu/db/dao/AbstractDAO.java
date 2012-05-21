package client.menu.db.dao;

import android.database.sqlite.SQLiteDatabase;
import client.menu.db.util.MyDatabaseHelper;

public abstract class AbstractDAO {
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
}
