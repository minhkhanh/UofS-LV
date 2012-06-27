package client.menu.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.MonAnDaNgonNguDTO;

import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class MonAnDaNgonNguDAO extends AbstractDAO {
    
    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachMonAnDaNgonNguJson";
    
    private static MonAnDaNgonNguDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new MonAnDaNgonNguDAO(dbHelper);
    }

    public static final MonAnDaNgonNguDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }
    
    List<MonAnDaNgonNguDTO> mCached;
    
    private MonAnDaNgonNguDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(MonAnDaNgonNguDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = MonAnDaNgonNguDTO.toContentValues(jsonObj);
                db.insert(MonAnDaNgonNguDTO.TABLE_NAME, null, values);
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

    @Override
    public String getName() {
        return "Danh sách món ăn";
    }

    @Override
    protected void createCache(Cursor cursor) {
        mCached = MonAnDaNgonNguDTO.fromArrayCursor(cursor);
    }
}
