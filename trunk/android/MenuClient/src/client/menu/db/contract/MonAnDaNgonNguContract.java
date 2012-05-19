package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MonAnDaNgonNguContract {
    public static final String TABLE_NAME = "ChiTietMonAnDaNgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_MON = "MaMonAn";
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_MA_NGON_NGU_QN = TABLE_NAME + ".MaNgonNgu";
    public static final String CL_TEN_MON = "TenMonAn";
    public static final String CL_MO_TA_MON = "MoTaMonAn";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
