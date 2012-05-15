package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MonAnDaNgonNguContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietMonAnDaNgonNgu";

    public static final String COL_MA_MON = "MaMonAn";
    public static final String COL_MA_NGON_NGU = "MaNgonNgu";
    public static final String COL_TEN_MON = "TenMonAn";
    public static final String COL_MO_TA_MON = "MoTaMonAn";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MA_MON
            + " INTEGER REFERENCES " + MonAnContract.TABLE_NAME + "("
            + MonAnContract.COL_SID + ")," + COL_MA_NGON_NGU + " INTEGER REFERENCES "
            + NgonNguContract.TABLE_NAME + "(" + NgonNguContract.COL_SID + "),"
            + COL_TEN_MON + " TEXT," + COL_MO_TA_MON + " TEXT);";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_ROW = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME + "/#");
}
