package emenu.client.dao;

import java.sql.Date;
import java.sql.Time;

import org.json.JSONArray;
import org.json.JSONObject;

import emenu.client.db.dto.BanDTO;
import emenu.client.db.dto.PhuThuDTO;
import emenu.client.db.dto.PhuThuKhuVucDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

public class PhuThuKhuVucDAO extends AbstractDAO {

    private static PhuThuKhuVucDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new PhuThuKhuVucDAO(dbHelper);
    }

    public static final PhuThuKhuVucDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private PhuThuKhuVucDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public PhuThuDTO objByTableId(Integer tableId) {
        PhuThuDTO obj = null;

        try {
            SQLiteDatabase db = open();
            SQLiteQueryBuilder query = new SQLiteQueryBuilder();

            query.setTables(BanDTO.TABLE_NAME + " inner join "
                    + PhuThuKhuVucDTO.TABLE_NAME + " on " + BanDTO.CL_MA_KHU_VUC_QN + "="
                    + PhuThuKhuVucDTO.CL_MA_KHU_VUC_QN + " inner join "
                    + PhuThuDTO.TABLE_NAME + " on " + PhuThuKhuVucDTO.CL_MA_PHU_THU_QN
                    + "=" + PhuThuDTO.CL_MA_PHU_THU_QN);

            String selection = BanDTO.CL_MA_BAN + "=?";
//                    + " and " + PhuThuDTO.CL_BAT_DAU
//                    + " <= " + time + " and " + time + " <= " + PhuThuDTO.CL_KET_THUC;
            String[] selectionArgs = { tableId.toString() };

            Cursor cursor = query.query(db, null, selection, selectionArgs, null, null,
                    null);

            if (cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            obj = PhuThuDTO.fromCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
            obj = null;
        } finally {
            close();
        }

        return obj;
    }

    @Override
    public boolean syncAll() {
        String url = SERVER_URL_SLASH + "layDanhSachPhuThuKhuVucCoHieuLucJson";
        SQLiteDatabase db = open();

        try {
            String response = U.loadGetResponse(url);
            JSONArray jsonArray = new JSONArray(response);

            db.beginTransaction();
            db.delete(PhuThuKhuVucDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = PhuThuKhuVucDTO.toContentValues(jsonObj);
                db.insert(PhuThuKhuVucDTO.TABLE_NAME, null, values);
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
        return "Phụ thu khu vực";
    }

    @Override
    protected void createCache(Cursor cursor) {
    }

}
