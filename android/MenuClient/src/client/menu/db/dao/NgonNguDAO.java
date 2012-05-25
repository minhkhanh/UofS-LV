package client.menu.db.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import client.menu.app.MyAppRepository;
import client.menu.db.dto.NgonNguDTO;
import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public final class NgonNguDAO extends AbstractDAO {
    public static final String GET_ALL_URL = MyAppRepository.LOCAL_SERVER_URL
            + "layDanhSachNgonNgu";

    private Cursor mCursorAll;

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

    public NgonNguDTO objNgonNguMacDinh() {
        Cursor cursor = cursorAll();
        cursor.moveToFirst();

        return NgonNguDTO.valueOf(cursor);
    }

    public List<NgonNguDTO> getAll() {
        List<NgonNguDTO> list = new ArrayList<NgonNguDTO>();
        String xmlData = U.loadGetResponse(GET_ALL_URL);

        try {
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

    public List<Map<String, Object>> mapAll() {
        Cursor cursor = cursorAll();

        return U.toMapList(cursor);
    }

    public Cursor cursorAll() {
        if (mCursorAll == null || mCursorAll.isClosed()) {
            SQLiteDatabase db = open();
            String orderBy = NgonNguDTO.CL_MA_NGON_NGU + " asc";
            mCursorAll = db.query(NgonNguDTO.TABLE_NAME, null, null, null, null, null,
                    orderBy, null);
        }

        return mCursorAll;
    }
}
