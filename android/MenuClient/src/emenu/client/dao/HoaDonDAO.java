package emenu.client.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import emenu.client.db.dto.ChiTietHoaDonDTO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.util.MyDatabaseHelper;
import emenu.client.util.U;

public class HoaDonDAO extends AbstractDAO {
    public static final String POST_BILL = SERVER_URL_SLASH + "themHoaDon";
    public static final String POST_BILL_ITEMS = SERVER_URL_SLASH
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

    public String postLapHoaDon(Integer orderId, List<String> voucherCodes) {
        String url = AbstractDAO.SERVER_URL_SLASH + "lapHoaDonJson?maOrder=" + orderId;

        JSONArray jsonArray = new JSONArray();
        for (String s : voucherCodes) {
            jsonArray.put(s);
        }

        return U.loadPostResponseJson(url, jsonArray.toString());
    }

    @Override
    public boolean syncAll() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }
}
