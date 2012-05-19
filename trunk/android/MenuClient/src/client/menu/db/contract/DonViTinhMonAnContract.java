package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhMonAnContract  {
    public static final String TABLE_NAME = "ChiTietMonAnDonViTinh";

    public static final String CL_ID = BaseColumns._ID;
    
    public static final String CL_MA_MON_AN = "MaMonAn";
    public static final String CL_MA_MON_AN_QN = TABLE_NAME + ".MaMonAn";
    
    public static final String CL_MA_DON_VI = "MaDonViTinh";
    public static final String CL_MA_DON_VI_QN = TABLE_NAME + ".MaDonViTinh";
    public static final String CL_DON_GIA = "DonGia";

    public static final String PATH_DONVITINHMONAN_INNER_DANGONNGU = "01";
    public static final String PATH_MONANDANGONNGU_DONVITINHMONAN_DANGONNGU = "02";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_DONVITINHMONAN_INNER_DANGONNGU = Uri.withAppendedPath(
            CONTENT_URI, PATH_DONVITINHMONAN_INNER_DANGONNGU);
    public static final Uri URI_MONANDANGONNGU_DONVITINHMONAN_DANGONNGU = Uri.withAppendedPath(
            CONTENT_URI, PATH_MONANDANGONNGU_DONVITINHMONAN_DANGONNGU);
}
