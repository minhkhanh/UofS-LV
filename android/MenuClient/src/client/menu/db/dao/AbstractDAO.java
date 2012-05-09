package client.menu.db.dao;

import client.menu.db.util.MyDatabaseHelper;

public abstract class AbstractDAO {
    protected MyDatabaseHelper mDbHelper = null;
    
    protected AbstractDAO(MyDatabaseHelper dbHelper) {
        mDbHelper = dbHelper;
    }
}
