package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class NgonNguContract implements BaseColumns {
    public static final String TABLE_NAME = "NgonNgu";

    public static final String COL_MA_NGON_NGU = "MaNgonNgu";
    public static final String COL_TEN_NGON_NGU = "TenNgonNgu";
    public static final String COL_KI_HIEU = "KiHieu";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INT PRIMARY KEY AUTOINCREMENT, " + COL_MA_NGON_NGU
            + " INT NOT NULL UNIQUE, " + COL_TEN_NGON_NGU + " TEXT, " + COL_KI_HIEU
            + " TEXT);";

    public static final String PATH_NGONNGU_MACDINH = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_NGONNGU_MACDINH = Uri.withAppendedPath(CONTENT_URI,
            PATH_NGONNGU_MACDINH);

    public static final ContentValues extractData(Cursor cursor) {
        ContentValues values = new ContentValues();

        values.put(_ID, cursor.getInt(cursor.getColumnIndex(_ID)));
        values.put(COL_MA_NGON_NGU, cursor.getInt(cursor.getColumnIndex(COL_MA_NGON_NGU)));
        values.put(COL_TEN_NGON_NGU,
                cursor.getString(cursor.getColumnIndex(COL_TEN_NGON_NGU)));
        values.put(COL_KI_HIEU, cursor.getString(cursor.getColumnIndex(COL_KI_HIEU)));

        return values;
    }

    public static final String[] getFullProjection() {
        return new String[] { TABLE_NAME + "." + _ID, COL_MA_NGON_NGU, COL_TEN_NGON_NGU,
                COL_KI_HIEU };
    }
}
