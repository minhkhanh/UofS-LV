package client.menu.db.contract;

import client.menu.db.provider.MenuClientContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class NgonNguContract implements BaseColumns {
    public static final String TABLE_NAME = "NgonNgu";

    public static final String COL_SID = "MaNgonNgu";
    public static final String COL_DISPLAY_NAME = "TenNgonNgu";
    public static final String COL_ABBREVIATE = "KiHieu";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SID + " INTEGER NOT NULL UNIQUE, " + COL_DISPLAY_NAME
            + " TEXT, " + COL_ABBREVIATE + " TEXT);";
    
    public static final Uri CONTENT_URI = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
