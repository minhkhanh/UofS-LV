package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract  {
    public static final String TABLE_NAME = "[Order]";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_MA_ORDER = TABLE_NAME + ".MaOrder";
    public static final String CL_MA_TAI_KHOAN = TABLE_NAME + ".MaTaiKhoan";
    public static final String CL_MA_BAN = TABLE_NAME + ".MaBan";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
