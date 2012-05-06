package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract implements BaseColumns {
    public static final String TABLE_NAME = "[Order]";
    
    public static final String COL_MA_ORDER = "MaOrder";
    public static final String COL_MA_TAI_KHOAN = "MaTaiKhoan";
    public static final String COL_MA_BAN = "MaBan";
    
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
