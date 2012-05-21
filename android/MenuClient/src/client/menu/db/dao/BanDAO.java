package client.menu.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.db.util.MyDatabaseHelper;

public class BanDAO extends AbstractDAO {
    private static BanDAO mInstance;

    private BanDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new BanDAO(dbHelper);
    }

    public static final BanDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    public Cursor cursorByMaKhuVuc(Integer maKhuVuc) {
        Cursor cursor = null;
        SQLiteDatabase db = open();
        String selection = BanDTO.CL_MA_KHU_VUC + "=?";
        String[] selectionArgs = { maKhuVuc.toString() };
        cursor = db.query(BanDTO.TABLE_NAME, null, selection, selectionArgs, null, null,
                null, null);

        return cursor;
    }

    public List<BanDTO> all() {
        List<BanDTO> list = new ArrayList<BanDTO>();

        try {
            SQLiteDatabase db = open();

            Cursor cursor = db.query(BanDTO.TABLE_NAME, null, null, null, null, null,
                    null, null);

            while (cursor.moveToNext()) {
                BanDTO ngonNgu = BanDTO.extractFrom(cursor);
                list.add(ngonNgu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }
}
