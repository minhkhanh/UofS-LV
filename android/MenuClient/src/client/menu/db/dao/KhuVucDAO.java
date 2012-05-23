package client.menu.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.KhuVucDTO;
import client.menu.db.util.MyDatabaseHelper;

public class KhuVucDAO extends AbstractDAO {
    private Cursor mCached;

    private static KhuVucDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new KhuVucDAO(dbHelper);
    }

    public static final KhuVucDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private KhuVucDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public Cursor cursorAll() {
        if (mCached == null || mCached.isClosed()) {
            SQLiteDatabase db = open();
            mCached = db.query(KhuVucDTO.TABLE_NAME, null, null, null, null, null, null,
                    null);
        }

        return mCached;
    }
}
