package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract implements BaseColumns {
    public static final String TABLE_NAME = "[Order]";

    public static final String COL_SID = "MaOrder";
    public static final String COL_MA_TAI_KHOAN = "MaTaiKhoan";
    public static final String COL_MA_BAN = "MaBan";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_SID
            + " INTEGER NOT NULL UNIQUE," + COL_MA_TAI_KHOAN + " INTEGER," + COL_MA_BAN
            + " INTEGER REFERENCES " + BanContract.TABLE_NAME + "("
            + BanContract.COL_SID + "));";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
