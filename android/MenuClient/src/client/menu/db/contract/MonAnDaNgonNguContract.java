package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MonAnDaNgonNguContract  {
    public static final String TABLE_NAME = "ChiTietMonAnDaNgonNgu";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_MA_MON = TABLE_NAME + ".MaMonAn";
    public static final String CL_MA_NGON_NGU = TABLE_NAME + ".MaNgonNgu";
    public static final String CL_TEN_MON = TABLE_NAME + ".TenMonAn";
    public static final String CL_MO_TA_MON = TABLE_NAME + ".MoTaMonAn";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
