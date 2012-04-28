package client.menu.db.contract;

import android.provider.BaseColumns;

public final class DonViTinhDaNgonNguContract {
    public static final String TABLE_NAME = "ChiTietDonViTinhDaNgonNgu";

    public static final String COL_LANGUAGE_ID = "MaNgonNgu";
    public static final String COL_UNIT_ID = "MaDonViTinh";
    public static final String COL_UNIT_NAME = "TenDonViTinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_LANGUAGE_ID + " INTEGER REFERENCES " + NgonNguContract.TABLE_NAME
            + "(" + NgonNguContract.COL_SID + ")," + COL_UNIT_ID
            + " INTEGER REFERENCES " + DonViTinhContract.TABLE_NAME + "("
            + DonViTinhContract.COL_SID + ")," + COL_UNIT_NAME + " TEXT);";
}
