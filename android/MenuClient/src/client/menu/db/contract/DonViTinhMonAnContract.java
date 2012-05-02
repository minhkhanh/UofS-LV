package client.menu.db.contract;

import client.menu.db.provider.MenuClientContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhMonAnContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietMonAnDonViTinh";

    public static final String COL_DISH_ID = "MaMonAn";
    public static final String COL_UNIT_ID = "MaDonViTinh";
    public static final String COL_PRICE = "DonGia";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_DISH_ID + " INTEGER REFERENCES " + MonAnContract.TABLE_NAME
            + "(" + MonAnContract.COL_SID + ")," + COL_UNIT_ID
            + " INTEGER REFERENCES " + DonViTinhContract.TABLE_NAME + "("
            + DonViTinhContract.COL_SID + ")," + COL_PRICE + " REAL);";
    
    public static final Uri URI_TABLE = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_ROW = Uri.parse(MenuClientContentProvider.SCHEME
            + MenuClientContentProvider.AUTHORITY + "/" + TABLE_NAME + "/#");
}
