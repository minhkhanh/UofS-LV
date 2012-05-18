package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class DanhMucDaNgonNguContract  {
    public static final String TABLE_NAME = "ChiTietDanhMucDaNgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DANH_MUC = "MaDanhMuc";
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_TEN_DANH_MUC = "TenDanhMuc";
    public static final String CL_MO_TA_DANH_MUC = "MoTaDanhMuc";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
