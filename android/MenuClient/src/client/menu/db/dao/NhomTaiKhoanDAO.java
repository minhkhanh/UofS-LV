package client.menu.db.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.NhomTaiKhoanDTO;

import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class NhomTaiKhoanDAO extends AbstractDAO {

    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachNhomTaiKhoanJson";
    
    private static NhomTaiKhoanDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new NhomTaiKhoanDAO(dbHelper);
    }

    public static final NhomTaiKhoanDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private NhomTaiKhoanDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(NhomTaiKhoanDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = NhomTaiKhoanDTO.toContentValues(jsonObj);
                db.insert(NhomTaiKhoanDTO.TABLE_NAME, null, values);
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
        return "Nhóm tài khoản";
    }
}
