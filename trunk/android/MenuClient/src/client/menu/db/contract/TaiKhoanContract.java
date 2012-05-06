package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class TaiKhoanContract implements BaseColumns {
    public static final String TABLE_NAME = "TaiKhoan";
    
    public static final String COL_MA_TAI_KHOAN = "MaTaiKhoan";
    public static final String COL_TEN_TAI_KHOAN = "TenTaiKhoan";
    public static final String COL_MAT_KHAU = "MatKhau";
    public static final String COL_HO_TEN = "HoTen";
    public static final String COL_NGAY_SINH = "NgaySinh";
    public static final String COL_GIOI_TINH = "GioiTinh";
    public static final String COL_CMND = "CMND";
    public static final String COL_AVATAR = "Avatar";
    public static final String COL_ACTIVE = "Active";
    public static final String COL_MA_NHOM = "MaNhomTaiKhoan";
    
    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME 
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
