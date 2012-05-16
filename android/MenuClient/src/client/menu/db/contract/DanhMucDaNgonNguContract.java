package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.net.Uri;
import android.provider.BaseColumns;

public class DanhMucDaNgonNguContract implements BaseColumns {
    public static final String TABLE_NAME = "ChiTietDanhMucDaNgonNgu";

    public static final String COL_MA_DANH_MUC = "MaDanhMuc";
    public static final String COL_MA_NGON_NGU = "MaNgonNgu";
    public static final String COL_TEN_DANH_MUC = "TenDanhMuc";
    public static final String COL_MO_TA_DANH_MUC = "MoTaDanhMuc";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + _ID
            + " INT PRIMARY KEY AUTOINCREMENT, " + COL_MA_DANH_MUC
            + " INT NOT NULL REFERENCES " + DanhMucContract.TABLE_NAME + "("
            + DanhMucContract.COL_MA_DANH_MUC + ")," + COL_MA_NGON_NGU
            + " INT NOT NULL REFERENCES " + NgonNguContract.TABLE_NAME + "("
            + NgonNguContract.COL_MA_NGON_NGU + ")," + COL_TEN_DANH_MUC + " TEXT,"
            + COL_MO_TA_DANH_MUC + " TEXT," + "UNIQUE (" + COL_MA_DANH_MUC + ","
            + COL_MA_NGON_NGU + "));";

    public static final Uri URI_TABLE = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_ROW = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME + "/#");
}
