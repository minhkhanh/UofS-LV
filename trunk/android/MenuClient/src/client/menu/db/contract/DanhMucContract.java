package client.menu.db.contract;

import android.provider.BaseColumns;

public final class DanhMucContract {
    public static final String TABLE_NAME = "DanhMuc";
    public static final String COL_SID = "MaDanhMuc";
    public static final String COL_PARENT_ID = "MaDanhMucCha";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SID + " INTEGER NOT NULL UNIQUE, " + COL_PARENT_ID
            + " INTEGER REFERENCES " + TABLE_NAME + " (" + COL_SID + ")" + ");";
}
