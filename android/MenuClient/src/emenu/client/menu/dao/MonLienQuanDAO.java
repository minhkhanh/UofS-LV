package emenu.client.menu.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.db.dto.MonLienQuanDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.menu.util.U;

public class MonLienQuanDAO extends AbstractDAO {

    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachMonLienQuanJson";

    private static MonLienQuanDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new MonLienQuanDAO(dbHelper);
    }

    public static final MonLienQuanDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private List<MonLienQuanDTO> mCached;

    private MonLienQuanDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public List<ContentValues> listContentByDishId(Integer languageId, Integer dishId) {
        List<ContentValues> list = new ArrayList<ContentValues>();
        SQLiteDatabase db = open();

        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        query.setTables(MonLienQuanDTO.TABLE_NAME + " INNER JOIN " + MonAnDTO.TABLE_NAME
                + " ON (" + MonAnDTO.CL_MA_MON_AN_QN + "="
                + MonLienQuanDTO.CL_MA_MON_LIEN_QUAN + ") INNER JOIN "
                + MonAnDaNgonNguDTO.TABLE_NAME + " ON ("
                + MonLienQuanDTO.CL_MA_MON_LIEN_QUAN + "="
                + MonAnDaNgonNguDTO.CL_MA_MON_QN + ")");

        String selection = MonLienQuanDTO.CL_MA_MON_QN + "=? and "
                + MonAnDaNgonNguDTO.CL_MA_NGON_NGU + "=?";
        String[] selectionArgs = { dishId.toString(), languageId.toString() };

        Cursor cursor = query.query(db, null, selection, selectionArgs, null, null, null);
        cursor.moveToPosition(-1);

        while (cursor.moveToNext()) {
            ContentValues c = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(cursor, c);

            list.add(c);
        }

        db.close();

        return list;
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(MonLienQuanDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = MonLienQuanDTO.toContentValues(jsonObj);
                db.insert(MonLienQuanDTO.TABLE_NAME, null, values);
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
        mCached = MonLienQuanDTO.fromArrayCursor(cursor);
    }
}
