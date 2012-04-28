package client.menu.db.contract;

import android.provider.BaseColumns;

public final class DonViTinhContract {
    public static final String TABLE_NAME = "DonViTinh";
    public static final String COL_SID = "MaDonViTinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SID + " INTEGER NOT NULL UNIQUE" + ");";
}
