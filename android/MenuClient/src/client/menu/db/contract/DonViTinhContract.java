package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhContract implements BaseColumns {
    public static final String TABLE_NAME = "DonViTinh";
    
    public static final String COL_SID = "MaDonViTinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SID
            + " INTEGER NOT NULL UNIQUE" + ");";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_ROW = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME + "/#");
}
