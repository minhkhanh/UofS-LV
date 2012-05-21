package client.menu.db.dao;

import client.menu.db.util.MyDatabaseHelper;

public class OrderDAO extends AbstractDAO {
    private static OrderDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new OrderDAO(dbHelper);
    }

    public static final OrderDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private OrderDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    
}
