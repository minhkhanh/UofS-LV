package client.menu.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import client.menu.db.dto.ChiTietHoaDonDTO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.HoaDonDTO;

import client.menu.db.util.MyDatabaseHelper;
import client.menu.util.U;

public class HoaDonDAO extends AbstractDAO {
    public static final String POST_BILL = LOCAL_SERVER_URL
            + "themHoaDon";
    public static final String POST_BILL_ITEMS = LOCAL_SERVER_URL
            + "themNhieuChiTietHoaDon";

    private static HoaDonDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new HoaDonDAO(dbHelper);
    }

    public static final HoaDonDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private HoaDonDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public List<ChiTietHoaDonDTO> postChiTietHoaDonArray(List<ChiTietHoaDonDTO> list) {
        String xmlData = ChiTietHoaDonDTO.toXmlArray(list);
        String respString = U.loadPostResponse(POST_BILL_ITEMS, xmlData);
        
        return ChiTietHoaDonDTO.fromXmlArray(respString);
    }

    public List<ChiTietHoaDonDTO> createListChiTietHoaDon(
            List<ChiTietOrderDTO> orderItems, Integer maHoaDon) {
        List<ChiTietHoaDonDTO> list = new ArrayList<ChiTietHoaDonDTO>();
        for (ChiTietOrderDTO c : orderItems) {
            ChiTietHoaDonDTO h = new ChiTietHoaDonDTO();
            int gia = OrderDAO.getInstance().queryDonGia(c);
            h.setDonGiaLuuTru(gia);
            h.setMaDonViTinh(c.getMaDonViTinh());
            h.setMaHoaDon(maHoaDon);
            h.setMaMonAn(c.getMaMonAn());
            h.setSoLuong(c.getSoLuong());
            h.setThanhTien(c.getSoLuong() * gia);

            list.add(h);
        }

        return list;
    }

    public HoaDonDTO postHoaDon(HoaDonDTO hoaDon) {
        String xmlData = hoaDon.toXml();
        String respString = U.loadPostResponse(POST_BILL, xmlData);

        return HoaDonDTO.fromXml(respString);
    }

    @Override
    public boolean syncAll() {
        return false;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void createCache(Cursor cursor) {
        // TODO Auto-generated method stub
        
    }
}
