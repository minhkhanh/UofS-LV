package client.menu.db.contract;

import android.provider.BaseColumns;

public class DanhMucDaNgonNguContract {
    public static final String TABLE_NAME = "ChiTietDanhMucDaNgonNgu";

    public static final String COL_CATEGORY_ID = "MaDanhMuc";
    public static final String COL_LANGUAGE_ID = "MaNgonNgu";
    public static final String COL_CATEGORY_NAME = "TenDanhMuc";
    public static final String COL_CATEGORY_DESCRIPTION = "MoTaDanhMuc";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_CATEGORY_ID + " INTEGER REFERENCES "
            + DanhMucContract.TABLE_NAME + "(" + DanhMucContract.COL_SID + "),"
            + COL_LANGUAGE_ID + " INTEGER REFERENCES "
            + NgonNguContract.TABLE_NAME + "(" + NgonNguContract.COL_SID + "),"
            + COL_CATEGORY_NAME + " TEXT," + COL_CATEGORY_DESCRIPTION
            + " TEXT);";
}