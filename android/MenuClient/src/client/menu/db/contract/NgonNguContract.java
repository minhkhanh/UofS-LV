package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class NgonNguContract implements BaseColumns {
    public static final String TABLE_NAME = "NgonNgu";

    public static final String COL_SID = "MaNgonNgu";
    public static final String COL_DISPLAY_NAME = "TenNgonNgu";
    public static final String COL_ABBREVIATE = "KiHieu";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SID
            + " INTEGER NOT NULL UNIQUE, " + COL_DISPLAY_NAME + " TEXT, "
            + COL_ABBREVIATE + " TEXT);";

    public static final String PATH_NGONNGU_MACDINH = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_NGONNGU_MACDINH = Uri.withAppendedPath(CONTENT_URI,
            PATH_NGONNGU_MACDINH);

    public static final ContentValues extractData(Cursor cursor) {
        ContentValues values = new ContentValues();

        values.put(_ID, cursor.getInt(cursor.getColumnIndex(_ID)));
        values.put(COL_SID, cursor.getInt(cursor.getColumnIndex(COL_SID)));
        values.put(COL_DISPLAY_NAME,
                cursor.getString(cursor.getColumnIndex(COL_DISPLAY_NAME)));
        values.put(COL_ABBREVIATE,
                cursor.getString(cursor.getColumnIndex(COL_ABBREVIATE)));

        return values;
    }

    public static final String[] getAllColumns() {
        return new String[] {
                TABLE_NAME + "." + _ID, COL_SID, COL_DISPLAY_NAME, COL_ABBREVIATE
        };
    }
}
