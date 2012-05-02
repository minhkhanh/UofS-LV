package client.menu.db.contract;

import client.menu.db.provider.MenuClientContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MonAnContract implements BaseColumns {
    public static final String TABLE_NAME = "MonAn";

    // public static final String COL_ID = "_id";
    public static final String COL_SID = "MaMonAn";
    public static final String COL_AVATAR = "HinhAnh";
    public static final String COL_RATE = "DiemDanhGia";
    public static final String COL_RATE_COUNT = "SoLuotDanhGia";
    public static final String COL_DEFAULT_UNIT_ID = "MaDonViTinhMacDinh";
    public static final String COL_CATEGORY_ID = "MaDanhMuc";
    public static final String COL_UNAVAILABLE = "NgungBan";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SID + " INTEGER NOT NULL UNIQUE, " + COL_AVATAR + " TEXT, "
            + COL_RATE + " REAL NOT NULL DEFAULT (0), " + COL_RATE_COUNT
            + " INTEGER NOT NULL DEFAULT (0), " + COL_DEFAULT_UNIT_ID
            + " INTEGER REFERENCES " + DonViTinhContract.TABLE_NAME + " ("
            + DonViTinhContract.COL_SID + ")," + COL_CATEGORY_ID
            + " INTEGER REFERENCES " + DanhMucContract.TABLE_NAME + " ("
            + DanhMucContract.COL_SID + ")," + COL_UNAVAILABLE
            + " BOOLEAN NOT NULL DEFAULT (0)" + ");";

    public static final Uri URI_TABLE = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_ROW = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME + "/#");
}
