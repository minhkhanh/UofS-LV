package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class ChiTietOrderContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietOrder";

    public static final String COL_SID = "MaChiTietOrder";
    public static final String COL_MA_ORDER = "MaOrder";
    public static final String COL_SO_LUONG = "SoLuong";
    public static final String COL_GHI_CHU = "GhiChu";
    public static final String COL_MA_BO_PHAN_CHE_BIEN = "MaBoPhanCheBien";
    public static final String COL_TINH_TRANG = "TinhTrang";
    public static final String COL_MA_MON_AN = "MaMonAn";
    public static final String COL_MA_DON_VI_TINH = "MaDonViTinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INT PRIMARY KEY AUTOINCREMENT," + COL_SID + " INT NOT NULL UNIQUE,"
            + COL_MA_ORDER + " INT," + COL_SO_LUONG + " INT," + COL_GHI_CHU + " TEXT,"
            + COL_MA_BO_PHAN_CHE_BIEN + " INT," + COL_TINH_TRANG
            + " INT NOT NULL DEFAULT 0," + COL_MA_MON_AN + " INT," + COL_MA_DON_VI_TINH
            + " INT," + "FOREIGN KEY (" + COL_MA_MON_AN + "," + COL_MA_DON_VI_TINH
            + ") REFERENCES " + DonViTinhMonAnContract.TABLE_NAME + "("
            + DonViTinhMonAnContract.COL_MA_MON_AN + ","
            + DonViTinhMonAnContract.COL_MA_DON_VI + "));";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
