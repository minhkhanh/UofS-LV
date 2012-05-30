package client.menu.db.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.DanhMucDaNgonNguDTO;

import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class DanhMucDaNgonNguDAO extends AbstractDAO {

    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachDanhMucDaNgonNguJson";

    private static DanhMucDaNgonNguDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new DanhMucDaNgonNguDAO(dbHelper);
    }

    public static final DanhMucDaNgonNguDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private DanhMucDaNgonNguDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(DanhMucDaNgonNguDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = DanhMucDaNgonNguDTO.toContentValues(jsonObj);
                db.insert(DanhMucDaNgonNguDTO.TABLE_NAME, null, values);
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
    public String getSyncTaskName() {
        return "Danh mục món";
    }
}
