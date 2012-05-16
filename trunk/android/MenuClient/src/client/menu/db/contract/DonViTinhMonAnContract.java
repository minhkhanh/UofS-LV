package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhMonAnContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietMonAnDonViTinh";

    public static final String COL_MA_MON_AN = "MaMonAn";
    public static final String COL_MA_DON_VI = "MaDonViTinh";
    public static final String COL_DON_GIA = "DonGia";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_MA_MON_AN
            + " INTEGER NOT NULL REFERENCES " + MonAnContract.TABLE_NAME + "("
            + MonAnContract.COL_MA_MON_AN + ")," + COL_MA_DON_VI
            + " INTEGER NOT NULL REFERENCES " + DonViTinhContract.TABLE_NAME + "("
            + DonViTinhContract.COL_MA_DON_VI_TINH + ")," + COL_DON_GIA + " INTEGER,"
            + "UNIQUE(" + COL_MA_MON_AN + "," + COL_MA_DON_VI + "));";

    public static final String PATH_DONVITINHMONAN_INNER_DANGONNGU = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_DONVITINHMONAN_INNER_DANGONNGU = Uri.withAppendedPath(
            CONTENT_URI, PATH_DONVITINHMONAN_INNER_DANGONNGU);
}
