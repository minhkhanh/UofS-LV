package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BanContract {
    public static final String TABLE_NAME = "Ban";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_SID = TABLE_NAME + ".MaBan";
    public static final String CL_MA_KHU_VUC = TABLE_NAME + ".MaKhuVuc";
    public static final String CL_TEN_BAN = TABLE_NAME + ".TenBan";
    public static final String CL_GHI_CHU = TABLE_NAME + ".GhiChu";
    public static final String CL_ACTIVE = TABLE_NAME + ".Active";
    public static final String CL_TINH_TRANG = TABLE_NAME + ".TinhTrang";
    public static final String CL_MA_BAN_CHINH = TABLE_NAME + ".MaBanChinh";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
