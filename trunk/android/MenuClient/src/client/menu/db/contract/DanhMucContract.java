package client.menu.db.contract;

import client.menu.db.provider.MenuClientContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DanhMucContract implements BaseColumns {
    public static final String TABLE_NAME = "DanhMuc";
    
    public static final String COL_SID = "MaDanhMuc";
    public static final String COL_PARENT_ID = "MaDanhMucCha";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SID + " INTEGER NOT NULL UNIQUE, " + COL_PARENT_ID
            + " INTEGER REFERENCES " + TABLE_NAME + " (" + COL_SID + ")" + ");";
    
    public static final Uri URI_TABLE = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_ROW = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME + "/#");
}
