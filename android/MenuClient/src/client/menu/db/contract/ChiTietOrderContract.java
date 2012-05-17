package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class ChiTietOrderContract  {
    public static final String TABLE_NAME = "ChiTietOrder";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_SID = TABLE_NAME + ".MaChiTietOrder";
    public static final String CL_MA_ORDER = TABLE_NAME + ".MaOrder";
    public static final String CL_SO_LUONG = TABLE_NAME + ".SoLuong";
    public static final String CL_GHI_CHU = TABLE_NAME + ".GhiChu";
    public static final String CL_MA_BO_PHAN_CHE_BIEN = TABLE_NAME + ".MaBoPhanCheBien";
    public static final String CL_TINH_TRANG = TABLE_NAME + ".TinhTrang";
    public static final String CL_MA_MON_AN = TABLE_NAME + ".MaMonAn";
    public static final String CL_MA_DON_VI_TINH = TABLE_NAME + ".MaDonViTinh";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
