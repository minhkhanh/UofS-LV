package client.menu.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.NgonNguDTO;
import client.menu.db.util.MyDatabaseHelper;

public final class NgonNguDAO extends AbstractDAO {

    private static NgonNguDAO mInstance;

    private NgonNguDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new NgonNguDAO(dbHelper);
    }

    public static final NgonNguDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    public NgonNguDTO getNgonNguMacDinh() {
        NgonNguDTO obj = null;

        try {
            SQLiteDatabase db = open();
            String limit = "1";
            String orderBy = NgonNguDTO.CL_MA_NGON_NGU + " asc";

            Cursor cursor = db.query(NgonNguDTO.TABLE_NAME, null, null, null, null, null,
                    orderBy, limit);

            if (cursor.moveToFirst()) {
                obj = NgonNguDTO.extractFrom(cursor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return obj;
    }

    public Cursor cursorAll() {
        Cursor cursor = null;
        SQLiteDatabase db = open();
        cursor = db
                .query(NgonNguDTO.TABLE_NAME, null, null, null, null, null, null, null);

        return cursor;
    }

    public List<NgonNguDTO> all() {
        List<NgonNguDTO> list = new ArrayList<NgonNguDTO>();

        try {
            SQLiteDatabase db = open();

            Cursor cursor = db.query(NgonNguDTO.TABLE_NAME, null, null, null, null, null,
                    null, null);

            while (cursor.moveToNext()) {
                NgonNguDTO ngonNgu = NgonNguDTO.extractFrom(cursor);
                list.add(ngonNgu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }

    public NgonNguDTO getByMaNgonNgu(Integer maNgonNgu) {
        NgonNguDTO obj = null;

        try {
            SQLiteDatabase db = open();
            String selection = NgonNguDTO.CL_MA_NGON_NGU + "=?";
            String[] selectionArgs = { maNgonNgu.toString() };

            Cursor cursor = db.query(NgonNguDTO.TABLE_NAME, null, selection,
                    selectionArgs, null, null, null, null);

            cursor.moveToFirst();
            obj = NgonNguDTO.extractFrom(cursor);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return obj;
    }
}
