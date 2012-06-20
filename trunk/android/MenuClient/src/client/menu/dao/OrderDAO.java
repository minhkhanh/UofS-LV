package client.menu.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.OrderDTO;

import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class OrderDAO extends AbstractDAO {
    public static final String POST_NEW_ORDER = LOCAL_SERVER_URL + "themOrder";

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
        close();

        return gia;
    }

    public int queryTongTien(List<ChiTietOrderDTO> list) {
        int total = 0;

        for (ChiTietOrderDTO c : list) {
            total += queryDonGia(c) * c.getSoLuong();
        }

        return total;
    }

    public boolean postArrayChiTietOrder(List<ChiTietOrderDTO> list) throws JSONException {
        String url = LOCAL_SERVER_URL + "themNhieuChiTietOrderJson";

        JSONArray jsonArray = ChiTietOrderDTO.toArrayJson(list);
        String response = U.loadPostResponseJson(url, jsonArray.toString());

        return Boolean.valueOf(response);
    }

    public OrderDTO postNewOrder(OrderDTO order) {
        String xmlData = order.toXml();
        String respString = U.loadPostResponse(POST_NEW_ORDER, xmlData);

        return OrderDTO.fromXml(respString);
    }

    @Override
    public boolean syncAll() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    protected void createCache(Cursor cursor) {
    }
}