package emenu.client.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import emenu.client.db.dto.TaiKhoanDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public class TaiKhoanDAO extends AbstractDAO {

    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachTaiKhoanJson";
    
    private static TaiKhoanDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new TaiKhoanDAO(dbHelper);
    }

    public static final TaiKhoanDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }
    
    private List<TaiKhoanDTO> mCached;

    private TaiKhoanDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(TaiKhoanDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = TaiKhoanDTO.toContentValues(jsonObj);
                db.insert(TaiKhoanDTO.TABLE_NAME, null, values);
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
        return "Tài khoản";
    }

    @Override
    protected void createCache(Cursor cursor) {
        mCached = TaiKhoanDTO.fromArrayCursor(cursor);
    }
}
