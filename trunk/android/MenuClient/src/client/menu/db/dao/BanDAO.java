package client.menu.db.dao;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.app.MyAppRepository;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.BanDTO;
import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class BanDAO extends AbstractDAO {
    private static final String GET_BY_KHU_VUC_URL = MyAppRepository.LOCAL_SERVER_URL
            + "layDanhSachBanTheoKhuVuc?maKhuVuc=";
    private static final String PUT_UPDATE_URL = MyAppRepository.LOCAL_SERVER_URL
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

//    public List<BanDTO> listFromXml(String xmlData) {
//        List<BanDTO> list = new ArrayList<BanDTO>();
//
//        try {
//            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
//            XmlPullParser parser = parserFactory.newPullParser();
//            parser.setInput(new StringReader(xmlData));
//            int type = parser.getEventType();
//            while (type != XmlPullParser.END_DOCUMENT) {
//                BanDTO obj = BanDTO.fromXml(parser);
//                if (obj != null) {
//                    list.add(obj);
//                }
//
//                type = parser.next();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }

    public List<BanDTO> getByKhuVuc(Integer maKhuVuc) {
        List<BanDTO> list = new ArrayList<BanDTO>();
        String xmlData = U.loadGetResponse(GET_BY_KHU_VUC_URL + maKhuVuc.toString());

        try {
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
}
