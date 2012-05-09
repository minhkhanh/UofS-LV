package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class ThamSoContract implements BaseColumns {
    public static final String TABLE_NAME = "ThamSo";

    public static final String COL_SID = "MaThamSo";
    public static final String COL_PARAM_NAME = "TenThamSo";
    public static final String COL_DATA_TYPE = "KieuDuLieu";
    public static final String COL_VALUE = "GiaTriThamSo";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SID
            + " INTEGER NOT NULL UNIQUE, " + COL_PARAM_NAME + " TEXT," + COL_DATA_TYPE
            + " TEXT," + COL_VALUE + " TEXT);";

    public static final Integer SID_NGONNGU_MACDINH = 1;

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
