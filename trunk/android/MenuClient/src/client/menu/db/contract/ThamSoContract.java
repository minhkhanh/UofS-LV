package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class ThamSoContract {
    public static final String TABLE_NAME = "ThamSo";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_PARAM_NAME = TABLE_NAME + ".Ten";
    public static final String CL_VALUE = TABLE_NAME + ".GiaTri";

    public static final String SID_MA_NGONNGU_MACDINH = "'MaNgonNguMacDinh'";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
