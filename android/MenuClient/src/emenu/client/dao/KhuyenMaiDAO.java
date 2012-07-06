package emenu.client.dao;

import org.json.JSONArray;
import org.json.JSONObject;

import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.db.dto.KhuyenMaiMonDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class KhuyenMaiDAO extends AbstractDAO {

    private static KhuyenMaiDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new KhuyenMaiDAO(dbHelper);
    }

    public static final KhuyenMaiDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private KhuyenMaiDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    public boolean syncAll() {
        String url = SERVER_URL_SLASH + "layDanhSachKhuyenMaiCoHieuLucJson";
        SQLiteDatabase db = open();

        try {
            String response = U.loadGetResponse(url);
            JSONArray jsonArray = new JSONArray(response);

            db.beginTransaction();
            db.delete(KhuyenMaiDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = KhuyenMaiDTO.toContentValues(jsonObj);
                db.insert(KhuyenMaiDTO.TABLE_NAME, null, values);
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();
            close();
        }

        return true;
    }

    @Override
    public String getName() {
        return "Khuyến mãi";
    }

    @Override
    protected void createCache(Cursor cursor) {
    }

}
