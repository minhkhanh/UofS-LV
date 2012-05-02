package client.menu.db.contract;

import client.menu.db.provider.MenuClientContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BanContract implements BaseColumns {
    public static final String TABLE_NAME = "Ban";

    public static final String COL_SID = "MaBan";
    public static final String COL_AREA_ID = "MaKhuVuc";
    public static final String COL_TABLE_NAME = "TenBan";
    public static final String COL_NOTE = "GhiChu";
    public static final String COL_ACTIVE = "Active";
    public static final String COL_STATUS = "TinhTrang";
    public static final String COL_ROOT_ID = "MaBanChinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SID + " INTEGER NOT NULL UNIQUE, " + COL_AREA_ID
            + " INTEGER REFERENCES " + KhuVucContract.TABLE_NAME + " ( "
            + KhuVucContract.COL_SID + "), " + COL_TABLE_NAME + " TEXT, "
            + COL_NOTE + " TEXT, " + COL_ACTIVE + " BOOLEAN DEFAULT ( 1 ), "
            + COL_STATUS + " BOOLEAN, " + COL_ROOT_ID + " INTEGER REFERENCES "
            + TABLE_NAME + " (" + COL_SID + "));";
    
    public static final Uri CONTENT_URI = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
