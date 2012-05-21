package client.menu.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.db.util.MyDatabaseHelper;

public final class MonAnDAO extends AbstractDAO {
    private static MonAnDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new MonAnDAO(dbHelper);
    }

    public static final MonAnDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    public MonAnDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public Cursor cursorByMaDanhMuc(Integer maDanhMuc, Integer maNgonNgu) {
        SQLiteDatabase db = open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(MonAnDTO.TABLE_NAME + " INNER JOIN "
                + MonAnDaNgonNguDTO.TABLE_NAME + " ON (" + MonAnDTO.TABLE_NAME + "."
                + MonAnDTO.CL_MA_MON_AN + " = " + MonAnDaNgonNguDTO.TABLE_NAME + "."
                + MonAnDaNgonNguDTO.CL_MA_MON + ")");

        String selection = MonAnDaNgonNguDTO.CL_MA_NGON_NGU + "=? and "
                + MonAnDTO.CL_MA_DANH_MUC + "=?";
        String[] selectionArgs = { maNgonNgu.toString(), maDanhMuc.toString() };

        Cursor cursor = queryBuilder.query(db, null, selection, selectionArgs, null,
                null, null);

        return cursor;
    }

    public List<MonAnDaNgonNguDTO> getByMaDanhMuc(Integer maDanhMuc, Integer maNgonNgu) {
        List<MonAnDaNgonNguDTO> list = new ArrayList<MonAnDaNgonNguDTO>();

        try {
            SQLiteDatabase db = open();

            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

            queryBuilder.setTables(MonAnDTO.TABLE_NAME + " INNER JOIN "
                    + MonAnDaNgonNguDTO.TABLE_NAME + " ON (" + MonAnDTO.TABLE_NAME + "."
                    + MonAnDTO.CL_MA_MON_AN + " = " + MonAnDaNgonNguDTO.TABLE_NAME + "."
                    + MonAnDaNgonNguDTO.CL_MA_MON + ")");

            String selection = MonAnDaNgonNguDTO.CL_MA_NGON_NGU + "=? and "
                    + MonAnDTO.CL_MA_DANH_MUC + "=?";
            String[] selectionArgs = { maNgonNgu.toString(), maDanhMuc.toString() };

            Cursor cursor = queryBuilder.query(db, null, selection, selectionArgs, null,
                    null, null);

            while (cursor.moveToNext()) {
                MonAnDaNgonNguDTO obj = MonAnDaNgonNguDTO.extractFrom(cursor);
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
