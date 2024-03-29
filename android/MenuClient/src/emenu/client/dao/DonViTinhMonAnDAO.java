package emenu.client.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import emenu.client.db.dto.DonViTinhMonAnDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public class DonViTinhMonAnDAO extends AbstractDAO {

    private static final String GET_ALL_JSON_URL = SERVER_URL_SLASH
            + "layDanhSachDonViTinhMonAnJson";
    
    private static DonViTinhMonAnDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new DonViTinhMonAnDAO(dbHelper);
    }

    public static final DonViTinhMonAnDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }
    
    private List<DonViTinhMonAnDTO> mCached;

    private DonViTinhMonAnDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(DonViTinhMonAnDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = DonViTinhMonAnDTO.toContentValues(jsonObj);
                db.insert(DonViTinhMonAnDTO.TABLE_NAME, null, values);
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            db.endTransaction();
//            close();
        }

        return result;
    }

    @Override
    public String getName() {
        return "Các đơn vị tính của món ăn";
    }
}
