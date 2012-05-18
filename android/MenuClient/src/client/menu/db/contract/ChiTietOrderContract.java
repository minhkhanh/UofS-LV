package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class ChiTietOrderContract  {
    public static final String TABLE_NAME = "ChiTietOrder";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_SID = "MaChiTietOrder";
    public static final String CL_MA_ORDER = "MaOrder";
    public static final String CL_SO_LUONG = "SoLuong";
    public static final String CL_GHI_CHU = "GhiChu";
    public static final String CL_MA_BO_PHAN_CHE_BIEN = "MaBoPhanCheBien";
    public static final String CL_TINH_TRANG = "TinhTrang";
    public static final String CL_MA_MON_AN = "MaMonAn";
    public static final String CL_MA_DON_VI_TINH = "MaDonViTinh";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
