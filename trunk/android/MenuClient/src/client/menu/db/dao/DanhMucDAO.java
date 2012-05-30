package client.menu.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.DanhMucDTO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;

import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class DanhMucDAO extends AbstractDAO {

    public static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachDanhMucJson";

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

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;
        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(DanhMucDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = DanhMucDTO.toContentValues(jsonObj);
                db.insert(DanhMucDTO.TABLE_NAME, null, values);
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

    @Override
    public String getSyncTaskName() {
        return "Danh muc món";
    }
}
