package emenu.client.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Base64;

import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.db.dto.KhuyenMaiMonDTO;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public final class MonAnDAO extends AbstractDAO {
    private static final String GET_ALL_JSON_URL = SERVER_URL_SLASH
            + "layDanhSachMonAnJson";
    private static MonAnDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new MonAnDAO(dbHelper);
    }

    public static final MonAnDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private List<MonAnDTO> mCached;

    private MonAnDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean syncAll() {
        String imgUrlBegin = SERVER_URL_SLASH + "getImageJson?path=";
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(MonAnDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = MonAnDTO.toContentValues(jsonObj);

                try {
                    String imgUrl = imgUrlBegin
                            + values.getAsString(MonAnDTO.CL_HINH_ANH);
                    String imgData = U.loadGetResponse(imgUrl);
                    byte[] decodedData = Base64.decode(imgData, Base64.DEFAULT);
                    values.put(MonAnDTO.CL_HINH_ANH, decodedData);
                } catch (Exception e) {
                    e.printStackTrace();
                    values.put(MonAnDTO.CL_HINH_ANH, new byte[] {});
                }

                db.insert(MonAnDTO.TABLE_NAME, null, values);
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

    public boolean getRatingUpdate(Integer dishId, float rate)
            throws ClientProtocolException, IOException {
        String url = AbstractDAO.SERVER_URL_SLASH + "danhGiaMonAnJson?maMonAn=" + dishId
                + "&diemDanhGia=" + rate;
        String response = U.loadGetResponse(url);

        U.logOwnTag("rate dish: id=" + dishId + " rate=" + rate + " result=" + response);

        return Boolean.valueOf(response);
    }

    public List<ContentValues> contentByCatIdWithProm(Integer maNgonNgu, Integer maDanhMuc) {
        SQLiteDatabase db = open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String subTable = "(select * from " + KhuyenMaiMonDTO.TABLE_NAME + " inner join "
                + KhuyenMaiDTO.TABLE_NAME + " on " + KhuyenMaiMonDTO.CL_MA_KM_QN + "="
                + KhuyenMaiDTO.CL_MA_KHUYEN_MAI_QN + ")";

        queryBuilder.setTables(MonAnDaNgonNguDTO.TABLE_NAME + " inner join "
                + MonAnDTO.TABLE_NAME + " on " + MonAnDaNgonNguDTO.CL_MA_MON_QN + "="
                + MonAnDTO.CL_MA_MON_AN_QN + " left outer join " + subTable
                + " as km on " + MonAnDTO.CL_MA_MON_AN_QN + "=" + "km."
                + KhuyenMaiMonDTO.CL_MA_MON);

        String selection = MonAnDaNgonNguDTO.CL_MA_NGON_NGU + "=? and "
                + MonAnDTO.CL_MA_DANH_MUC + "=?";

        String[] selectionArgs = { maNgonNgu.toString(), maDanhMuc.toString() };

        String[] columns = { MonAnDaNgonNguDTO.CL_ID_QN, MonAnDaNgonNguDTO.CL_MA_MON_QN,
                MonAnDaNgonNguDTO.CL_MA_NGON_NGU, MonAnDaNgonNguDTO.CL_MO_TA_MON,
                MonAnDaNgonNguDTO.CL_TEN_MON, MonAnDTO.CL_DIEM_DANH_GIA,
                MonAnDTO.CL_HINH_ANH, MonAnDTO.CL_MA_DANH_MUC, MonAnDTO.CL_NGUNG_BAN,
                MonAnDTO.CL_SO_LUOT_RATE, KhuyenMaiDTO.CL_MA_KHUYEN_MAI,
                KhuyenMaiDTO.CL_BAT_DAU, KhuyenMaiDTO.CL_GIA_GIAM,
                KhuyenMaiDTO.CL_KET_THUC, KhuyenMaiDTO.CL_TI_LE_GIAM };

        // just in case there are couple of promotions for one dish
        String groupBy = MonAnDTO.CL_MA_MON_AN_QN;

        Cursor cursor = queryBuilder.query(db, columns, selection, selectionArgs,
                groupBy, null, null);

        List<ContentValues> list = U.toContentValuesList(cursor);

        db.close();

        return list;
    }

    public Cursor cursorByMaDanhMuc(Integer maNgonNgu, Integer maDanhMuc) {
        SQLiteDatabase db = open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(MonAnDTO.TABLE_NAME + " INNER JOIN "
                + MonAnDaNgonNguDTO.TABLE_NAME + " ON (" + MonAnDTO.TABLE_NAME + "."
                + MonAnDTO.CL_MA_MON_AN + " = " + MonAnDaNgonNguDTO.TABLE_NAME + "."
                + MonAnDaNgonNguDTO.CL_MA_MON + ")");

        String selection = MonAnDaNgonNguDTO.CL_MA_NGON_NGU + "=? and "
                + MonAnDTO.CL_MA_DANH_MUC + "=?";
        String[] selectionArgs = { maNgonNgu.toString(), maDanhMuc.toString() };

        Cursor cursor = queryBuilder.query(db, null, selection, selectionArgs, null,
                null, null);

        return cursor;
    }

    public List<MonAnDaNgonNguDTO> getByMaDanhMuc(Integer maDanhMuc, Integer maNgonNgu) {
        List<MonAnDaNgonNguDTO> list = new ArrayList<MonAnDaNgonNguDTO>();

        try {
            SQLiteDatabase db = open();

            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

            queryBuilder.setTables(MonAnDTO.TABLE_NAME + " INNER JOIN "
                    + MonAnDaNgonNguDTO.TABLE_NAME + " ON (" + MonAnDTO.TABLE_NAME + "."
                    + MonAnDTO.CL_MA_MON_AN + " = " + MonAnDaNgonNguDTO.TABLE_NAME + "."
                    + MonAnDaNgonNguDTO.CL_MA_MON + ")");

            String selection = MonAnDaNgonNguDTO.CL_MA_NGON_NGU + "=? and "
                    + MonAnDTO.CL_MA_DANH_MUC + "=?";
            String[] selectionArgs = { maNgonNgu.toString(), maDanhMuc.toString() };

            Cursor cursor = queryBuilder.query(db, null, selection, selectionArgs, null,
                    null, null);

            while (cursor.moveToNext()) {
                MonAnDaNgonNguDTO obj = MonAnDaNgonNguDTO.fromCursor(cursor);
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }

    @Override
    public String getName() {
        return "Danh sách món ăn";
    }

    @Override
    protected void createCache(Cursor cursor) {
        mCached = MonAnDTO.fromArrayCursor(cursor);
    }
}
