package client.menu.db.contract;

import android.provider.BaseColumns;

public final class DonViTinhMonAnContract {
    public static final String TABLE_NAME = "ChiTietMonAnDonViTinh";

    public static final String COL_DISH_ID = "MaMonAn";
    public static final String COL_UNIT_ID = "MaDonViTinh";
    public static final String COL_PRICE = "DonGia";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_DISH_ID + " INTEGER REFERENCES " + MonAnContract.TABLE_NAME
            + "(" + MonAnContract.COL_SID + ")," + COL_UNIT_ID
            + " INTEGER REFERENCES " + DonViTinhContract.TABLE_NAME + "("
            + DonViTinhContract.COL_SID + ")," + COL_PRICE + " REAL);";
}
