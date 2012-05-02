package client.menu.db.contract;

import client.menu.db.provider.MenuClientContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MonAnDaNgonNguContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietMonAnDaNgonNgu";

    public static final String COL_DISH_ID = "MaMonAn";
    public static final String COL_LANGUAGE_ID = "MaNgonNgu";
    public static final String COL_DISH_NAME = "TenMonAn";
    public static final String COL_DISH_DESCRIPTION = "MoTaMonAn";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_DISH_ID + " INTEGER REFERENCES " + MonAnContract.TABLE_NAME
            + "(" + MonAnContract.COL_SID + ")," + COL_LANGUAGE_ID
            + " INTEGER REFERENCES " + NgonNguContract.TABLE_NAME + "("
            + NgonNguContract.COL_SID + ")," + COL_DISH_NAME + " TEXT,"
            + COL_DISH_DESCRIPTION + " TEXT);";
    
    public static final Uri URI_TABLE = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_ROW = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME + "/#");
}
