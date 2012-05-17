package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class TaiKhoanContract  {
    public static final String TABLE_NAME = "TaiKhoan";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_MA_TAI_KHOAN = TABLE_NAME + ".MaTaiKhoan";
    public static final String CL_TEN_TAI_KHOAN = TABLE_NAME + ".TenTaiKhoan";
    public static final String CL_MAT_KHAU = TABLE_NAME + ".MatKhau";
    public static final String CL_HO_TEN = TABLE_NAME + ".HoTen";
    public static final String CL_NGAY_SINH = TABLE_NAME + ".NgaySinh";
    public static final String CL_GIOI_TINH = TABLE_NAME + ".GioiTinh";
    public static final String CL_CMND = TABLE_NAME + ".CMND";
    public static final String CL_AVATAR = TABLE_NAME + ".Avatar";
    public static final String CL_ACTIVE = TABLE_NAME + ".Active";
    public static final String CL_MA_NHOM = TABLE_NAME + ".MaNhomTaiKhoan";
    
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
