package client.menu.db.contract;

import android.provider.BaseColumns;

public final class KhuVucContract {
    public static final String TABLE_NAME = "KhuVuc";

    public static final String COL_SID = "MaKhuVuc";
    public static final String COL_AREA_NAME = "TenKhuVuc";
    public static final String COL_DESCRIPTION = "MoTa";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SID + " INTEGER NOT NULL UNIQUE, " + COL_AREA_NAME
            + " TEXT, " + COL_DESCRIPTION + " TEXT);";
}
