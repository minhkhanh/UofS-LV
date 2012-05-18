package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class NhomTaiKhoanContract  {
    public static final String TABLE_NAME = "NhomTaiKhoan";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_SID = "MaNhomTaiKhoan";
    public static final String CL_TEN_NHOM = "TenNhom";
    public static final String CL_MO_TA = "MoTaNhom";;

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
