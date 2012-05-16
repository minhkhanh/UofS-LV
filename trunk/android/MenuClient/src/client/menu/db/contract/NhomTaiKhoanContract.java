package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class NhomTaiKhoanContract implements BaseColumns {
    public static final String TABLE_NAME = "NhomTaiKhoan";

    public static final String COL_SID = "MaNhomTaiKhoan";
    public static final String COL_TEN_NHOM = "TenNhom";
    public static final String COL_MO_TA = "MoTaNhom";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_SID + " INTEGER NOT NULL UNIQUE,"
            + COL_TEN_NHOM + " TEXT," + COL_MO_TA + " TEXT);";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
