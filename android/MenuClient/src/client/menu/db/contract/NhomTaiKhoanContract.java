package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class NhomTaiKhoanContract  {
    public static final String TABLE_NAME = "NhomTaiKhoan";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_SID = TABLE_NAME + ".MaNhomTaiKhoan";
    public static final String CL_TEN_NHOM = TABLE_NAME + ".TenNhom";
    public static final String CL_MO_TA = TABLE_NAME + ".MoTaNhom";;

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
