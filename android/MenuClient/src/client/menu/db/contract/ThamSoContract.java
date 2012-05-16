package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class ThamSoContract implements BaseColumns {
    public static final String TABLE_NAME = "ThamSo";

    // public static final String COL_MA_THAM_SO = "MaThamSo";
    public static final String COL_PARAM_NAME = "Ten";
    // public static final String COL_DATA_TYPE = "KieuDuLieu";
    public static final String COL_VALUE = "GiaTri";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PARAM_NAME
            + " TEXT NOT NULL UNIQUE," + COL_VALUE + " TEXT);";

    public static final String SID_MA_NGONNGU_MACDINH = "'MaNgonNguMacDinh'";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
