package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BanContract implements BaseColumns {
    public static final String TABLE_NAME = "Ban";

    public static final String COL_SID = "MaBan";
    public static final String COL_MA_KHU_VUC = "MaKhuVuc";
    public static final String COL_TEN_BAN = "TenBan";
    public static final String COL_GHI_CHU = "GhiChu";
    public static final String COL_ACTIVE = "Active";
    public static final String COL_TINH_TRANG = "TinhTrang";
    public static final String COL_MA_BAN_CHINH = "MaBanChinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INT PRIMARY KEY AUTOINCREMENT, " + COL_SID
            + " INT NOT NULL UNIQUE, " + COL_MA_KHU_VUC + " INT REFERENCES "
            + KhuVucContract.TABLE_NAME + " ( " + KhuVucContract.COL_MA_KHU_VUC + "), "
            + COL_TEN_BAN + " TEXT, " + COL_GHI_CHU + " TEXT, " + COL_ACTIVE
            + " INT DEFAULT ( 1 ), " + COL_TINH_TRANG + " INT, " + COL_MA_BAN_CHINH
            + " INT REFERENCES " + TABLE_NAME + " (" + COL_SID + "));";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
