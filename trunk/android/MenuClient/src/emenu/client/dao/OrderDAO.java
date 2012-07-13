package emenu.client.dao;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhMonAnDTO;
import emenu.client.db.dto.OrderDTO;
import emenu.client.db.dto.SplittingOrderItem;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public class OrderDAO extends AbstractDAO {
    public static final String POST_NEW_ORDER = SERVER_URL_SLASH + "themOrderJson";
    public static final String POST_ORDER_SPLITTING = SERVER_URL_SLASH + "tachOrderJson";

    private static OrderDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new OrderDAO(dbHelper);
    }

    public static final OrderDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private OrderDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public boolean postOrderSplitting(HttpClient client, List<Integer> itemIds)
            throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Integer i : itemIds) {
//            JSONObject jsonObject = i.toJson();
            jsonArray.put(i);
        }

        String response = U.loadPostResponseJson(client, POST_ORDER_SPLITTING,
                jsonArray.toString());

        return Boolean.valueOf(response);
    }

    public OrderDTO getOrder(Integer orderId) throws ClientProtocolException,
            IOException, JSONException {
        String url = SERVER_URL_SLASH + "layOrderJson?maOrder=" + orderId;
        String response = U.loadGetResponse(url);

        JSONObject jsonObject = new JSONObject(response);

        return OrderDTO.fromJson(jsonObject);
    }

    public ChiTietOrderDTO getOrderItem(Integer itemId) throws ClientProtocolException,
            IOException, JSONException {
        String url = SERVER_URL_SLASH + "layChiTietOrderJson?maChiTiet=" + itemId;
        String response = U.loadGetResponse(url);

        JSONObject jsonObject = new JSONObject(response);

        return ChiTietOrderDTO.fromJson(jsonObject);
    }

    public boolean getOrderedItemUpdate(Integer itemId, int newQuantity, String itemNote)
            throws ClientProtocolException, IOException, JSONException {
        ChiTietOrderDTO item = new ChiTietOrderDTO();
        item.setMaChiTiet(itemId);
        item.setGhiChu(itemNote);
        item.setSoLuong(newQuantity);

        JSONObject jsonObject = item.toJson();

        String url = SERVER_URL_SLASH + "suaChiTietOrderJson";
        String response = U.loadPutResponseJson(url, jsonObject.toString());

        return Boolean.valueOf(response);
    }

    public boolean getMoveOrder(HttpClient client, Integer orderId, Integer tableId)
            throws ClientProtocolException, IOException {
        String url = SERVER_URL_SLASH + "chuyenBan?maOrder=" + orderId + "&maBanMoi="
                + tableId;
        String response = U.loadGetResponse(url);
        return Boolean.valueOf(response);
    }

    public int queryDonGia(ChiTietOrderDTO c) {
        SQLiteDatabase db = open();
        String selection = DonViTinhMonAnDTO.CL_MA_MON_AN + "=? and "
                + DonViTinhMonAnDTO.CL_MA_DON_VI + "=?";
        String[] selectionArgs = { c.getMaMonAn().toString(),
                c.getMaDonViTinh().toString() };

        String[] projection = { DonViTinhMonAnDTO.CL_DON_GIA };

        Cursor cursor = db.query(DonViTinhMonAnDTO.TABLE_NAME, projection, selection,
                selectionArgs, null, null, null);

        int gia = 0;
        if (cursor != null && cursor.moveToFirst()) {
            gia = cursor.getInt(0);
        }
//        close();

        return gia;
    }

    public int queryTongTien(List<ChiTietOrderDTO> list) {
        int total = 0;

        for (ChiTietOrderDTO c : list) {
            total += queryDonGia(c) * c.getSoLuong();
        }

        return total;
    }

    public boolean postArrayChiTietOrder(HttpClient client, List<ChiTietOrderDTO> list) throws JSONException {
        String url = SERVER_URL_SLASH + "themNhieuChiTietOrderJson";

        JSONArray jsonArray = ChiTietOrderDTO.toArrayJson(list);
        String response = U.loadPostResponseJson(client, url, jsonArray.toString());

        return Boolean.valueOf(response);
    }

    // public OrderDTO postNewOrder(OrderDTO order) {
    // String jsonData = order.toJson().toString();
    // String response = U.loadPostResponseJson(POST_NEW_ORDER, jsonData);
    // JSONObject jsonObject = new JSONObject(response);
    //
    // return OrderDTO.fromJson(jsonObject);
    // }

    @Override
    public boolean syncAll() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }
}
