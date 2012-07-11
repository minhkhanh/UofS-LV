package emenu.client.dao;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public class MonAnDaNgonNguDAO extends AbstractDAO {

    private static final String GET_ALL_JSON_URL = SERVER_URL_SLASH
            + "layDanhSachMonAnDaNgonNguJson";

    private static MonAnDaNgonNguDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new MonAnDaNgonNguDAO(dbHelper);
    }

    public static final MonAnDaNgonNguDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    List<MonAnDaNgonNguDTO> mCached;

    private MonAnDaNgonNguDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public static final Pattern DIACRITICS_AND_FRIENDS = Pattern
            .compile("[\\p{InCombiningDiacriticalMarks}]+");

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(MonAnDaNgonNguDTO.TABLE_NAME, "1", null);
            db.delete(MonAnDaNgonNguDTO.TABLE_NAME_FTS, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = MonAnDaNgonNguDTO.toContentValues(jsonObj);
                db.insert(MonAnDaNgonNguDTO.TABLE_NAME, null, values);

                ContentValues val2 = new ContentValues();
                val2.put(MonAnDaNgonNguDTO.CL_MA_MON,
                        values.getAsInteger(MonAnDaNgonNguDTO.CL_MA_MON));
                val2.put(MonAnDaNgonNguDTO.CL_MA_NGON_NGU,
                        values.getAsInteger(MonAnDaNgonNguDTO.CL_MA_NGON_NGU));

                // String regex = "[\\p{InCombiningDiacriticalMarks}]+";
                String dishName = values.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON);
                dishName = Normalizer.normalize(dishName, Normalizer.Form.NFD);
                // dishName = new String(dishName.replaceAll(regex,
                // "").getBytes("ascii"),
                // "ascii");
                dishName = DIACRITICS_AND_FRIENDS.matcher(dishName).replaceAll("");
                val2.put(MonAnDaNgonNguDTO.CL_TEN_MON, dishName);
                db.insert(MonAnDaNgonNguDTO.TABLE_NAME_FTS, null, val2);
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            db.endTransaction();
            close();
        }

        return result;
    }

    @Override
    public String getName() {
        return "Danh sách món ăn";
    }
}
