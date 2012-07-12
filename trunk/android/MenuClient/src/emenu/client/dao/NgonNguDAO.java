package emenu.client.dao;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import emenu.client.db.dto.NgonNguDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public final class NgonNguDAO extends AbstractDAO {
    public static final String GET_ALL_URL = SERVER_URL_SLASH + "layDanhSachNgonNgu";

    private static final String GET_ALL_JSON_URL = SERVER_URL_SLASH
            + "layDanhSachNgonNguJson";

    private List<NgonNguDTO> mCached;

    private static NgonNguDAO mInstance;

    private NgonNguDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new NgonNguDAO(dbHelper);
    }

    public static final NgonNguDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(NgonNguDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = NgonNguDTO.toContentValues(jsonObj);
                db.insert(NgonNguDTO.TABLE_NAME, null, values);
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

    public List<NgonNguDTO> listAll() {
        SQLiteDatabase db = open();
        Cursor cursor = db.query(NgonNguDTO.TABLE_NAME, null, null, null, null, null,
                null);
        return NgonNguDTO.fromArrayCursor(cursor);
    }

    public NgonNguDTO objFirstLang() {
        SQLiteDatabase db = open();

        String orderBy = NgonNguDTO.CL_MA_NGON_NGU + " asc";
        Cursor cursor = db.query(NgonNguDTO.TABLE_NAME, null, null, null, null, null,
                orderBy, null);
        cursor.moveToFirst();

        return NgonNguDTO.fromCursor(cursor);
    }

    public NgonNguDTO objByName(String name) {
        SQLiteDatabase db = open();

        String selection = NgonNguDTO.CL_KI_HIEU + "=?";
        String[] selectionArgs = { name };
        // String orderBy = NgonNguDTO.CL_MA_NGON_NGU + " asc";
        Cursor cursor = db.query(NgonNguDTO.TABLE_NAME, null, selection, selectionArgs,
                null, null, null, null);
        cursor.moveToFirst();

        return NgonNguDTO.fromCursor(cursor);
    }

    @Deprecated
    public List<NgonNguDTO> getAll() {
        List<NgonNguDTO> list = new ArrayList<NgonNguDTO>();

        try {
            String xmlData = U.loadGetResponse(GET_ALL_URL);
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                NgonNguDTO obj = NgonNguDTO.valueOf(parser);
                if (obj != null) {
                    list.add(obj);
                }

                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public String getName() {
        return "Danh sách ngôn ngữ";
    }
}
