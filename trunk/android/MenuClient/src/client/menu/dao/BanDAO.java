package client.menu.dao;

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
import client.menu.app.MyApplication;
import client.menu.db.dto.BanDTO;
import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class BanDAO extends AbstractDAO {
    private static final String GET_ALL_JSON_URL = LOCAL_SERVER_URL
            + "layDanhSachBanJson";
    private static final String PUT_URL = LOCAL_SERVER_URL + "capNhatBan";

    private static BanDAO mInstance;

    private List<BanDTO> mCached;

    private BanDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new BanDAO(dbHelper);
    }

    public static final BanDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
            // mInstance = new BanDAO(MyApplication.getInstance().dbOpener);
        }

        return mInstance;
    }

    public boolean put(BanDTO ban) {
        String response = null;

        try {
            JSONObject jsonObj = ban.toJson();
            response = U.loadPutResponse(PUT_URL, jsonObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return Boolean.valueOf(response);
    }

    public BanDTO objByMaBan(Integer maBan) {
        BanDTO ban = null;
        try {
            SQLiteDatabase db = open();
            String selection = BanDTO.CL_MA_BAN + "=?";
            String[] selectionArgs = { maBan.toString() };
            Cursor cursor = db.query(BanDTO.TABLE_NAME, null, selection, selectionArgs,
                    null, null, null, null);
            if (cursor.moveToFirst()) {
                ban = BanDTO.fromCursor(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ban = null;
        } finally {
            close();
        }

        return ban;
    }

    @Deprecated
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
        String respString = U.loadPutResponse(PUT_URL, xmlData);

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

    public Boolean getTableSplitting(Integer tableId) {
        String url = LOCAL_SERVER_URL + "tachBanJson?maBan=" + tableId;

        try {
            String response = U.loadGetResponse(url);
            return Boolean.valueOf(response);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BanDTO> getTableInGroup(Integer groupId) {
        String url = LOCAL_SERVER_URL + "layDanhSachBanThuocBanChinhJson?maBanChinh="
                + groupId;
        List<BanDTO> list = null;

        try {
            String jsonData = U.loadGetResponse(url);
            JSONArray jsonArray = new JSONArray(jsonData);
            list = BanDTO.fromArrayJson(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<BanDTO>();
        }

        return list;
    }

    public List<BanDTO> getByKhuVuc(Integer maKhuVuc) {
        String url = LOCAL_SERVER_URL + "layDanhSachBanTheoKhuVuc?maKhuVuc=" + maKhuVuc;
        List<BanDTO> list = new ArrayList<BanDTO>();

        try {
            String xmlData = U.loadGetResponse(url);
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

    @Override
    public String getName() {
        return "Danh sách bàn";
    }

    @Override
    protected void createCache(Cursor cursorAll) {
        mCached = BanDTO.fromArrayCursor(cursorAll);
    }

}
