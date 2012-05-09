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
    public static final String COL_STATUS = "TinhTrang";
    public static final String COL_MA_BAN_CHINH = "MaBanChinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SID
            + " INTEGER NOT NULL UNIQUE, " + COL_MA_KHU_VUC + " INTEGER REFERENCES "
            + KhuVucContract.TABLE_NAME + " ( " + KhuVucContract.COL_SID + "), "
            + COL_TEN_BAN + " TEXT, " + COL_GHI_CHU + " TEXT, " + COL_ACTIVE
            + " BOOLEAN DEFAULT ( 1 ), " + COL_STATUS + " BOOLEAN, " + COL_MA_BAN_CHINH
            + " INTEGER REFERENCES " + TABLE_NAME + " (" + COL_SID + "));";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
