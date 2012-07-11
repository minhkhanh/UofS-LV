package emenu.client.dao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import emenu.client.db.dto.KhuVucDTO;
import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.db.dto.KhuyenMaiMonDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

public class KhuyenMaiMonDAO extends AbstractDAO {

    private static KhuyenMaiMonDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new KhuyenMaiMonDAO(dbHelper);
    }

    public static final KhuyenMaiMonDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private KhuyenMaiMonDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    // public KhuyenMaiDTO getDishPromotion(Integer dishId) throws
    // ClientProtocolException,
    // IOException, JSONException {
    // String url = LOCAL_SERVER_URL + "layKhuyenMaiMonApDungJson?maMon=" +
    // dishId;
    // String response = U.loadGetResponse(url);
    // JSONObject jsonObject = new JSONObject(response);
    //
    // return KhuyenMaiDTO.fromJson(jsonObject);
    // }

    public KhuyenMaiDTO objByDishId(Integer dishId) {
        KhuyenMaiDTO obj = null;
        SQLiteDatabase db = open();
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();

        query.setTables(KhuyenMaiDTO.TABLE_NAME + " INNER JOIN "
                + KhuyenMaiMonDTO.TABLE_NAME + " ON (" + KhuyenMaiDTO.CL_MA_KHUYEN_MAI_QN
                + "=" + KhuyenMaiMonDTO.CL_MA_KM_QN + ")");

        String selection = KhuyenMaiMonDTO.CL_MA_MON + "=?";
        // + " and " + KhuyenMaiDTO.CL_BAT_DAU + "<=" + time + " and " + time +
        // "<="
        // + KhuyenMaiDTO.CL_KET_THUC;
        String[] selectionArgs = { dishId.toString() };

        Cursor cursor = query.query(db, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();

        obj = KhuyenMaiDTO.fromCursor(cursor);

        return obj;
    }

    @Override
    public boolean syncAll() {
        String url = SERVER_URL_SLASH + "layDanhSachKhuyenMaiMonCoHieuLucJson";
        SQLiteDatabase db = open();

        try {
            String response = U.loadGetResponse(url);
            JSONArray jsonArray = new JSONArray(response);

            db.beginTransaction();
            db.delete(KhuyenMaiMonDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = KhuyenMaiMonDTO.toContentValues(jsonObj);
                db.insert(KhuyenMaiMonDTO.TABLE_NAME, null, values);
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
        return "Khuyến mãi món";
    }
}
