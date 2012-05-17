package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhContract  {
    public static final String TABLE_NAME = "DonViTinh";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_MA_DON_VI_TINH = TABLE_NAME + ".MaDonViTinh";
    
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);

}
