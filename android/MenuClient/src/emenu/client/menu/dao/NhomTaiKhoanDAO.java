package emenu.client.menu.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import emenu.client.menu.db.dto.NhomTaiKhoanDTO;
import emenu.client.menu.db.util.MyDatabaseHelper;
import emenu.client.menu.util.U;

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

    private List<NhomTaiKhoanDTO> mCached;

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
    public String getName() {
        return "Nhóm tài khoản";
    }

    @Override
    protected void createCache(Cursor cursor) {
        mCached = NhomTaiKhoanDTO.fromArrayCursor(cursor);
    }
}