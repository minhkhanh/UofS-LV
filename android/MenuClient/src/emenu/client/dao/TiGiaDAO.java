package emenu.client.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import emenu.client.db.dto.TiGiaDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public class TiGiaDAO extends AbstractDAO {

    private static final String GET_ALL_JSON_URL = SERVER_URL_SLASH
            + "layDanhSachTiGiaJson";
    
    private static TiGiaDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new TiGiaDAO(dbHelper);
    }

    public static final TiGiaDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }
    
    private List<TiGiaDTO> mCached;

    private TiGiaDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(TiGiaDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = TiGiaDTO.toContentValues(jsonObj);
                db.insert(TiGiaDTO.TABLE_NAME, null, values);
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
        return "Tỉ giá";
    }

    @Override
    protected void createCache(Cursor cursor) {
        mCached = TiGiaDTO.fromArrayCursor(cursor);
    }
}
