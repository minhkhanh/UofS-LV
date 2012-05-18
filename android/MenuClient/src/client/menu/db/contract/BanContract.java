package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BanContract {
    public static final String TABLE_NAME = "Ban";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_SID = "MaBan";
    public static final String CL_MA_KHU_VUC = "MaKhuVuc";
    public static final String CL_TEN_BAN = "TenBan";
    public static final String CL_GHI_CHU = "GhiChu";
    public static final String CL_ACTIVE = "Active";
    public static final String CL_TINH_TRANG = "TinhTrang";
    public static final String CL_MA_BAN_CHINH = "MaBanChinh";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
