package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DanhMucContract implements BaseColumns {
    public static final String TABLE_NAME = "DanhMuc";

    public static final String COL_MA_DANH_MUC = "MaDanhMuc";
    public static final String COL_MA_DANH_MUC_CHA = "MaDanhMucCha";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INT PRIMARY KEY AUTOINCREMENT, " + COL_MA_DANH_MUC
            + " INT NOT NULL UNIQUE, " + COL_MA_DANH_MUC_CHA + " INT REFERENCES "
            + TABLE_NAME + " (" + COL_MA_DANH_MUC + ")" + ");";

    public static final String PATH_DANHMUC_INNER_DANGONNGU = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_DANHMUC_INNER_DANGONNGU = Uri.withAppendedPath(
            CONTENT_URI, PATH_DANHMUC_INNER_DANGONNGU);
}
