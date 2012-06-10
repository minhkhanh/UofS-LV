package client.menu.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.DonViTinhDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;

import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class DonViTinhDAO extends AbstractDAO {
    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachDonViTinhJson";
    private static DonViTinhDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new DonViTinhDAO(dbHelper);
    }

    public static final DonViTinhDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private List<DonViTinhDTO> mCached;

    private DonViTinhDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(DonViTinhDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = DonViTinhDTO.toContentValues(jsonObj);
                db.insert(DonViTinhDTO.TABLE_NAME, null, values);
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            db.endTransaction();
        }

        return result;
    }

    public ContentValues contentByDonViTinhMonAn(Integer maNgonNgu, Integer maMonAn,
            Integer maDonViTinh) {
        ContentValues values = null;
        Cursor cursor;

        try {
            SQLiteDatabase db = open();
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

            queryBuilder.setTables(MonAnDaNgonNguDTO.TABLE_NAME + " INNER JOIN "
                    + DonViTinhMonAnDTO.TABLE_NAME + " ON ("
                    + MonAnDaNgonNguDTO.CL_MA_MON_QN + "="
                    + DonViTinhMonAnDTO.CL_MA_MON_AN_QN + ")" + "INNER JOIN "
                    + DonViTinhDaNgonNguDTO.TABLE_NAME + " ON ("
                    + DonViTinhMonAnDTO.CL_MA_DON_VI_QN + "="
                    + DonViTinhDaNgonNguDTO.CL_MA_DON_VI_QN + ")");

            String selection = DonViTinhDaNgonNguDTO.CL_MA_NGON_NGU_QN + "=? and "
                    + DonViTinhMonAnDTO.CL_MA_MON_AN_QN + "=? and "
                    + DonViTinhMonAnDTO.CL_MA_DON_VI_QN + "=?";
            String[] selectionArgs = { maNgonNgu.toString(), maMonAn.toString(),
                    maDonViTinh.toString() };

            cursor = queryBuilder.query(db, null, selection, selectionArgs, null, null,
                    null);
            cursor.moveToFirst();
            values = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(cursor, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return values;
    }

    public Cursor cursorByDonViTinhMonAn(Integer maMonAn, Integer maDonViTinh,
            Integer maNgonNgu) {
        Cursor cursor;
        try {
            SQLiteDatabase db = open();
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

            queryBuilder.setTables(MonAnDaNgonNguDTO.TABLE_NAME + " INNER JOIN "
                    + DonViTinhMonAnDTO.TABLE_NAME + " ON ("
                    + MonAnDaNgonNguDTO.CL_MA_MON_QN + "="
                    + DonViTinhMonAnDTO.CL_MA_MON_AN_QN + ")" + "INNER JOIN "
                    + DonViTinhDaNgonNguDTO.TABLE_NAME + " ON ("
                    + DonViTinhMonAnDTO.CL_MA_DON_VI_QN + "="
                    + DonViTinhDaNgonNguDTO.CL_MA_DON_VI_QN + ")");

            String selection = DonViTinhDaNgonNguDTO.CL_MA_NGON_NGU_QN + "=? and "
                    + DonViTinhMonAnDTO.CL_MA_MON_AN_QN + "=? and "
                    + DonViTinhMonAnDTO.CL_MA_DON_VI_QN + "=?";
            String[] selectionArgs = { maNgonNgu.toString(), maMonAn.toString(),
                    maDonViTinh.toString() };

            cursor = queryBuilder.query(db, null, selection, selectionArgs, null, null,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return cursor;
    }

    public List<ContentValues> contentByMaMonAn(Integer maNgonNgu, Integer maMonAn) {
        SQLiteDatabase db = open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(DonViTinhMonAnDTO.TABLE_NAME + " INNER JOIN "
                + DonViTinhDaNgonNguDTO.TABLE_NAME + " ON ("
                + DonViTinhMonAnDTO.TABLE_NAME + "." + DonViTinhMonAnDTO.CL_MA_DON_VI
                + " = " + DonViTinhDaNgonNguDTO.TABLE_NAME + "."
                + DonViTinhDaNgonNguDTO.CL_MA_DON_VI + ")");

        String selection = DonViTinhDaNgonNguDTO.CL_MA_NGON_NGU + "=? and "
                + DonViTinhMonAnDTO.CL_MA_MON_AN + "=?";
        String[] selectionArgs = { maNgonNgu.toString(), maMonAn.toString() };

        Cursor cursor = queryBuilder.query(db, null, selection, selectionArgs, null,
                null, null);

        List<ContentValues> list = new ArrayList<ContentValues>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ContentValues c = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor, c);
                list.add(c);
            }
        }
        cursor.close();

        return list;
    }

    public Cursor cursorByMonAn(Integer maMonAn, Integer maNgonNgu) {
        SQLiteDatabase db = open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(DonViTinhMonAnDTO.TABLE_NAME + " INNER JOIN "
                + DonViTinhDaNgonNguDTO.TABLE_NAME + " ON ("
                + DonViTinhMonAnDTO.TABLE_NAME + "." + DonViTinhMonAnDTO.CL_MA_DON_VI
                + " = " + DonViTinhDaNgonNguDTO.TABLE_NAME + "."
                + DonViTinhDaNgonNguDTO.CL_MA_DON_VI + ")");

        String selection = DonViTinhDaNgonNguDTO.CL_MA_NGON_NGU + "=? and "
                + DonViTinhMonAnDTO.CL_MA_MON_AN + "=?";
        String[] selectionArgs = { maNgonNgu.toString(), maMonAn.toString() };

        Cursor cursor = queryBuilder.query(db, null, selection, selectionArgs, null,
                null, null);

        return cursor;
    }

    public List<DonViTinhDaNgonNguDTO> byMaNgonNgu(Integer maNgonNgu) {
        List<DonViTinhDaNgonNguDTO> list = new ArrayList<DonViTinhDaNgonNguDTO>();

        try {
            SQLiteDatabase db = open();

            String selection = DonViTinhDaNgonNguDTO.CL_MA_NGON_NGU + "=?";
            String[] selectionArgs = { maNgonNgu.toString() };

            Cursor cursor = db.query(DonViTinhDaNgonNguDTO.TABLE_NAME, null, selection,
                    selectionArgs, null, null, null, null);

            while (cursor.moveToNext()) {
                DonViTinhDaNgonNguDTO obj = DonViTinhDaNgonNguDTO.fromCursor(cursor);
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }

    @Override
    public String getName() {
        return "Các đơn vị tính của món ăn";
    }

    @Override
    protected void createCache(Cursor cursor) {
        mCached = DonViTinhDTO.fromArrayCursor(cursor);
    }
}
