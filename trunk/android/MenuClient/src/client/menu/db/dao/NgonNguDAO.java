package client.menu.db.dao;

import client.menu.db.util.MyDatabaseHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;

public final class NgonNguDAO extends AbstractDAO {
    
    
    
    public NgonNguDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);        
    }

    public final Cursor layDanhSachNgonNgu() {
        Cursor cursor = null;
        
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        
        
        return cursor;
    }
}
