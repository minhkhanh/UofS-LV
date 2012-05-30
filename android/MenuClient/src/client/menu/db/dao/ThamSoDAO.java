package client.menu.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.ThamSoDTO;
import client.menu.db.util.MyDatabaseHelper;

public class ThamSoDAO extends AbstractDAO {

    public ThamSoDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public ThamSoDTO byTenThamSo(String tenThamSo) {
        ThamSoDTO obj = null;

        try {
            SQLiteDatabase db = open();
            String selection = ThamSoDTO.CL_PARAM_NAME + "=?";
            String[] selectionArgs = { tenThamSo };

            Cursor cursor = db.query(ThamSoDTO.TABLE_NAME, null, selection,
                    selectionArgs, null, null, null, null);

            cursor.moveToFirst();
            obj = ThamSoDTO.extractFrom(cursor);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return obj;
    }

    @Override
    public boolean syncAll() {
        return false;
    }

    @Override
    public String getSyncTaskName() {
        return null;
    }
}
