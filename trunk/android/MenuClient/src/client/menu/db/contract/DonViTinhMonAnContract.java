package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhMonAnContract  {
    public static final String TABLE_NAME = "ChiTietMonAnDonViTinh";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_MA_MON_AN = TABLE_NAME + ".MaMonAn";
    public static final String CL_MA_DON_VI = TABLE_NAME + ".MaDonViTinh";
    public static final String CL_DON_GIA = TABLE_NAME + ".DonGia";

    public static final String PATH_DONVITINHMONAN_INNER_DANGONNGU = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_DONVITINHMONAN_INNER_DANGONNGU = Uri.withAppendedPath(
            CONTENT_URI, PATH_DONVITINHMONAN_INNER_DANGONNGU);
}
