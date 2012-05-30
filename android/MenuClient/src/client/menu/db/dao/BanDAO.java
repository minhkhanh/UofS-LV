package client.menu.db.dao;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.db.dto.BanDTO;
import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class BanDAO extends AbstractDAO {
    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachBanJson";
    private static final String GET_BY_KHU_VUC_URL = LOCAL_SERVER_URL
            + "layDanhSachBanTheoKhuVuc?maKhuVuc=";
    private static final String PUT_UPDATE_URL = LOCAL_SERVER_URL
            + "capNhatBan";

    private static BanDAO mInstance;

    private BanDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new BanDAO(dbHelper);
    }

    public static final BanDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    public Cursor cursorByKhuVuc(Integer maKhuVuc) {
        Cursor cursor = null;
        SQLiteDatabase db = open();
        String selection = BanDTO.CL_MA_KHU_VUC + "=?";
        String[] selectionArgs = { maKhuVuc.toString() };
        cursor = db.query(BanDTO.TABLE_NAME, null, selection, selectionArgs, null, null,
                null, null);

        return cursor;
    }

    public boolean putUpdate(BanDTO ban) {
        String xmlData = ban.toXml();
        String respString = U.loadPutResponse(PUT_UPDATE_URL, xmlData);

        return U.deserializeXml(respString);
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(BanDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = BanDTO.toContentValues(jsonObj);
                db.insert(BanDTO.TABLE_NAME, null, values);
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

    public List<BanDTO> getByKhuVuc(Integer maKhuVuc) {
        List<BanDTO> list = new ArrayList<BanDTO>();

        try {
            String xmlData = U.loadGetResponse(GET_BY_KHU_VUC_URL + maKhuVuc.toString());
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                BanDTO obj = BanDTO.fromXml(parser);
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

    public List<BanDTO> objAll() {
        List<BanDTO> list = new ArrayList<BanDTO>();

        try {
            SQLiteDatabase db = open();

            Cursor cursor = db.query(BanDTO.TABLE_NAME, null, null, null, null, null,
                    null, null);

            while (cursor.moveToNext()) {
                BanDTO ngonNgu = BanDTO.extractFrom(cursor);
                list.add(ngonNgu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }

    @Override
    public String getSyncTaskName() {
        return "Danh sách bàn";
    }
}
