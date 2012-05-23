package client.menu.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

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

    public int updateByMaMonAn(Integer maMonAn, MonAnDTO monAn) {
        int nRow = 0;

        try {
            SQLiteDatabase db = open();

            ContentValues values = new ContentValues();
            monAn.to(values);

            String where = MonAnDTO.CL_MA_MON_AN + "=?";
            String[] whereArgs = { maMonAn.toString() };

            nRow = db.update(MonAnDTO.TABLE_NAME, values, where, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
            nRow = -1;
        } finally {
            close();
        }

        return nRow;
    }

    public int updateDiemDanhGia(Integer maMonAn, Float diemDanhGia) {
        MonAnDTO monAn = getByMaMonAn(maMonAn);
        float total = monAn.getDiemDanhGia() * monAn.getSoLuotDanhGia() + diemDanhGia;
        int count = monAn.getSoLuotDanhGia() + 1;
        monAn.setDiemDanhGia(total / count);
        monAn.setSoLuotDanhGia(count);

        U.logOwnTag(String.valueOf(total / count));

        return updateByMaMonAn(maMonAn, monAn);
    }

    public MonAnDTO getByMaMonAn(Integer maMonAn) {
        MonAnDTO monAn = null;

        try {
            SQLiteDatabase db = open();

            String selection = MonAnDTO.CL_MA_MON_AN + "=?";
            String[] selectionArgs = { maMonAn.toString() };

            Cursor cursor = db.query(MonAnDTO.TABLE_NAME, null, selection, selectionArgs,
                    null, null, null);
            if (cursor.moveToNext()) {
                monAn = MonAnDTO.from(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return monAn;
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