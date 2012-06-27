package client.menu.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.KhuVucDTO;

import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class KhuVucDAO extends AbstractDAO {
    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachKhuVucJson";

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

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(KhuVucDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = KhuVucDTO.toContentValues(jsonObj);
                db.insert(KhuVucDTO.TABLE_NAME, null, values);
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

    public Cursor cursorAll() {
        if (mCached == null || mCached.isClosed()) {
            SQLiteDatabase db = open();
            mCached = db.query(KhuVucDTO.TABLE_NAME, null, null, null, null, null, null,
                    null);
        }

        return mCached;
    }

    @Override
    public String getSyncTaskName() {
        return "Danh sách khu vực";
    }
}
