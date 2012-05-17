package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DonViTinhDaNgonNguContract  {
    public static final String TABLE_NAME = "ChiTietDonViTinhDaNgonNgu";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_MA_NGON_NGU = TABLE_NAME + ".MaNgonNgu";
    public static final String CL_MA_DON_VI = TABLE_NAME + ".MaDonViTinh";
    public static final String CL_TEN_DON_VI = TABLE_NAME + ".TenDonViTinh";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
