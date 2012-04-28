package client.menu.db.contract;

import android.provider.BaseColumns;

public final class NgonNguContract {
    public static final String TABLE_NAME = "NgonNgu";

    public static final String COL_SID = "MaNgonNgu";
    public static final String COL_DISPLAY_NAME = "TenNgonNgu";
    public static final String COL_ABBREVIATE = "KiHieu";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SID + " INTEGER NOT NULL UNIQUE, " + COL_DISPLAY_NAME
            + " TEXT, " + COL_ABBREVIATE + " TEXT);";
}
