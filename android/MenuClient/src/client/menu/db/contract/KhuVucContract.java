package client.menu.db.contract;

import android.net.Uri;
import android.provider.BaseColumns;
import client.menu.db.provider.MyContentProvider;

public final class KhuVucContract  {
    public static final String TABLE_NAME = "KhuVuc";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_MA_KHU_VUC = TABLE_NAME + ".MaKhuVuc";
    public static final String CL_TEN_KHU_VUC = TABLE_NAME + ".TenKhuVuc";
    public static final String CL_MO_TA = TABLE_NAME + ".MoTa";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
