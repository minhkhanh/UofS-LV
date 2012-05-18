package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DanhMucContract  {
    public static final String TABLE_NAME = "DanhMuc";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DANH_MUC = "MaDanhMuc";
    public static final String CL_MA_DANH_MUC_CHA = "MaDanhMucCha";
    
    public static final String PATH_DANHMUC_INNER_DANGONNGU = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_DANHMUC_INNER_DANGONNGU = Uri.withAppendedPath(
            CONTENT_URI, PATH_DANHMUC_INNER_DANGONNGU);
}
