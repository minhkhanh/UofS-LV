package client.menu.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import client.menu.app.MyAppLocale;
import client.menu.bus.loader.CustomAsyncTaskLoader;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.db.util.MyDatabaseHelper;

public class DanhMucDAO extends AbstractDAO {

    private Cursor mCached;

    private static DanhMucDAO mInstance;

    private DanhMucDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new DanhMucDAO(dbHelper);
    }

    public static final DanhMucDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    public Cursor cursorAll(Integer maNgonNgu) {
        Cursor cursor = null;
        SQLiteDatabase db = open();
        String selection = DanhMucDaNgonNguDTO.CL_MA_NGON_NGU + "=?";
        String[] selectionArgs = { maNgonNgu.toString() };
        cursor = db.query(DanhMucDaNgonNguDTO.TABLE_NAME, null, selection, selectionArgs,
                null, null, null, null);

        return cursor;
    }

    public List<DanhMucDaNgonNguDTO> byMaNgonNgu(Integer maNgonNgu) {
        List<DanhMucDaNgonNguDTO> list = new ArrayList<DanhMucDaNgonNguDTO>();

        try {
            SQLiteDatabase db = open();

            String selection = DanhMucDaNgonNguDTO.CL_MA_NGON_NGU + "=?";
            String[] selectionArgs = { maNgonNgu.toString() };

            Cursor cursor = db.query(DanhMucDaNgonNguDTO.TABLE_NAME, null, selection,
                    selectionArgs, null, null, null, null);

            while (cursor.moveToNext()) {
                DanhMucDaNgonNguDTO obj = DanhMucDaNgonNguDTO.extractFrom(cursor);
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
