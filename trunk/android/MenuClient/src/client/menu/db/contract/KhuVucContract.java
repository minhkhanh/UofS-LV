package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class KhuVucContract implements BaseColumns {
    public static final String TABLE_NAME = "KhuVuc";

    public static final String COL_MA_KHU_VUC = "MaKhuVuc";
    public static final String COL_TEN_KHU_VUC = "TenKhuVuc";
    public static final String COL_MO_TA = "MoTa";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MA_KHU_VUC
            + " INTEGER NOT NULL UNIQUE, " + COL_TEN_KHU_VUC + " TEXT, " + COL_MO_TA
            + " TEXT);";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
}
