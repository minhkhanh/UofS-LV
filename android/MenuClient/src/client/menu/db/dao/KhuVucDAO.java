package client.menu.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.KhuVucDTO;
import client.menu.db.util.MyDatabaseHelper;

public class KhuVucDAO extends AbstractDAO {
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
        Cursor cursor = null;
        SQLiteDatabase db = open();
        cursor = db.query(KhuVucDTO.TABLE_NAME, null, null, null, null, null, null, null);

        return cursor;
    }

    public List<KhuVucDTO> all() {
        List<KhuVucDTO> list = new ArrayList<KhuVucDTO>();

        try {
            SQLiteDatabase db = open();

            Cursor cursor = db.query(KhuVucDTO.TABLE_NAME, null, null, null, null, null,
                    null, null);

            while (cursor.moveToNext()) {
                KhuVucDTO obj = KhuVucDTO.extractFrom(cursor);
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }
}
