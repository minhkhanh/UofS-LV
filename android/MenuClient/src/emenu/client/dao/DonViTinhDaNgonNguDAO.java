package emenu.client.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import emenu.client.db.dto.DonViTinhDaNgonNguDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public class DonViTinhDaNgonNguDAO extends AbstractDAO {
    private static final String GET_ALL_JSON_URL = SERVER_URL_SLASH
            + "layDanhSachDonViTinhDaNgonNguJson";
    
    private static DonViTinhDaNgonNguDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new DonViTinhDaNgonNguDAO(dbHelper);
    }

    public static final DonViTinhDaNgonNguDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }
    
    private List<DonViTinhDaNgonNguDTO> mCached;

    private DonViTinhDaNgonNguDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(DonViTinhDaNgonNguDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = DonViTinhDaNgonNguDTO.toContentValues(jsonObj);
                db.insert(DonViTinhDaNgonNguDTO.TABLE_NAME, null, values);
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
//            close();
        }

        return true;
    }

    @Override
    public String getName() {
        return "Các đơn vị tính của món ăn";
    }
}
