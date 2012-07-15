package emenu.client.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import emenu.client.db.dto.TaiKhoanDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.menu.app.AuthenticationManager;
import emenu.client.util.MyHttpClient;
import emenu.client.util.U;

public class TaiKhoanDAO extends AbstractDAO {

    private static final String GET_ALL_JSON_URL = SERVER_URL_SLASH
            + "layDanhSachTaiKhoanJson";

    private static TaiKhoanDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new TaiKhoanDAO(dbHelper);
    }

    public static final TaiKhoanDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private TaiKhoanDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean postLogIn(String name, String pass) throws ClientProtocolException,
            IOException {
        String url = SERVER_URL_SLASH + "dangNhapJson";
        MyHttpClient client = new MyHttpClient();
        HttpPost post = new HttpPost(url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("tenDangNhap", name));
        nameValuePairs.add(new BasicNameValuePair("matKhau", pass));
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String strResult = EntityUtils.toString(response.getEntity());
            boolean boolResult = Boolean.valueOf(strResult);
            if (boolResult) {
                AuthenticationManager.getInstance().setCookieStore(
                        client.getCookieStore());
                return true;
            }

            return false;
        }

        return false;
    }

    public boolean syncAll() {
        SQLiteDatabase db = open();
        boolean result = true;

        try {
            String jsonData = U.loadGetResponse(GET_ALL_JSON_URL);
            JSONArray jsonArray = new JSONArray(jsonData);

            db.beginTransaction();
            db.delete(TaiKhoanDTO.TABLE_NAME, "1", null);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                ContentValues values = TaiKhoanDTO.toContentValues(jsonObj);
                db.insert(TaiKhoanDTO.TABLE_NAME, null, values);
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            db.endTransaction();
            // close();
        }

        return result;
    }

    @Override
    public String getName() {
        return "Tài khoản";
    }
}
