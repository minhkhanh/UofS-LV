package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhDaNgonNguContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietDonViTinhDaNgonNgu";

    public static final String COL_LANGUAGE_ID = "MaNgonNgu";
    public static final String COL_UNIT_ID = "MaDonViTinh";
    public static final String COL_UNIT_NAME = "TenDonViTinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LANGUAGE_ID
            + " INTEGER REFERENCES " + NgonNguContract.TABLE_NAME + "("
            + NgonNguContract.COL_SID + ")," + COL_UNIT_ID + " INTEGER REFERENCES "
            + DonViTinhContract.TABLE_NAME + "(" + DonViTinhContract.COL_SID + "),"
            + COL_UNIT_NAME + " TEXT);";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_ROW = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME + "/#");
}
