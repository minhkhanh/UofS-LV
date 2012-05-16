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
            + " INT PRIMARY KEY AUTOINCREMENT, " + COL_MA_NGON_NGU
            + " INT NOT NULL REFERENCES " + NgonNguContract.TABLE_NAME + "("
            + NgonNguContract.COL_MA_NGON_NGU + ")," + COL_MA_DON_VI
            + " INT NOT NULL REFERENCES " + DonViTinhContract.TABLE_NAME + "("
            + DonViTinhContract.COL_MA_DON_VI_TINH + ")," + COL_TEN_DON_VI + " TEXT,"
            + "UNIQUE (" + COL_MA_DON_VI + "," + COL_MA_NGON_NGU + "));";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
