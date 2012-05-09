package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class ChiTietOrderContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietOrder";

    public static final String COL_SID = "MaChiTietOrder";
    public static final String COL_MA_ORDER = "MaOrder";
    public static final String COL_SO_LUONG = "SoLuong";
    public static final String COL_MA_MON = "MaMonAn";
    public static final String COL_GHI_CHU = "GhiChu";
    public static final String COL_MA_BO_PHAN_CHE_BIEN = "MaBoPhanCheBien";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_SID
            + " INTEGER NOT NULL UNIQUE," + COL_MA_ORDER + " INTEGER," + COL_SO_LUONG
            + " INTEGER," + COL_MA_MON + " INTEGER," + COL_GHI_CHU + " TEXT,"
            + COL_MA_BO_PHAN_CHE_BIEN + " INTEGER);";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
