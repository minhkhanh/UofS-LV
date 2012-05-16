package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhContract implements BaseColumns {
    public static final String TABLE_NAME = "DonViTinh";

    public static final String COL_MA_DON_VI_TINH = "MaDonViTinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MA_DON_VI_TINH
            + " INTEGER NOT NULL UNIQUE);";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);

}
