package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhDaNgonNguContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietDonViTinhDaNgonNgu";

    public static final String COL_MA_NGON_NGU = "MaNgonNgu";
    public static final String COL_MA_DON_VI = "MaDonViTinh";
    public static final String COL_TEN_DON_VI = "TenDonViTinh";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MA_NGON_NGU
            + " INTEGER REFERENCES " + NgonNguContract.TABLE_NAME + "("
            + NgonNguContract.COL_SID + ")," + COL_MA_DON_VI
            + " INTEGER REFERENCES " + DonViTinhContract.TABLE_NAME + "("
            + DonViTinhContract.COL_SID + ")," + COL_TEN_DON_VI + " TEXT);";
    
    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
