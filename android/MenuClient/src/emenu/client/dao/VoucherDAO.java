package emenu.client.dao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import emenu.client.db.dto.VoucherDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;
import android.database.Cursor;

public class VoucherDAO extends AbstractDAO {

    private static VoucherDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new VoucherDAO(dbHelper);
    }

    public static final VoucherDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    public VoucherDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    @Override
    public boolean syncAll() {
        return false;
    }

    @Override
    public String getName() {
        return "";
    }

    public float checkVoucher(String code, Float billTotal)
            throws ClientProtocolException, IOException {
        String url = SERVER_URL_SLASH + "kiemTraVoucherJson?code=" + code
                + "&tongHoaDon=" + billTotal.toString();
        String response = U.loadGetResponse(url);
        return Float.valueOf(response);
    }

    public boolean getVoucherUsed(String code) throws ClientProtocolException,
            IOException {
        String url = SERVER_URL_SLASH + "dungVoucherJson?code=" + code;
        String response = U.loadGetResponse(url);
        return Boolean.valueOf(response);
    }

    public VoucherDTO getVoucher(String code, int billTotal)
            throws ClientProtocolException, IOException, JSONException {
        String url = SERVER_URL_SLASH + "layVoucherJson?code=" + code + "&tongHoaDon="
                + billTotal;
        String response = U.loadGetResponse(url);
        JSONObject jsonObj = new JSONObject(response);
        return VoucherDTO.fromJson(jsonObj);
    }
}
