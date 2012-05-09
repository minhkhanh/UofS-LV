package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class KhuVucContract implements BaseColumns {
    public static final String TABLE_NAME = "KhuVuc";

    public static final String COL_SID = "MaKhuVuc";
    public static final String COL_AREA_NAME = "TenKhuVuc";
    public static final String COL_DESCRIPTION = "MoTa";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SID
            + " INTEGER NOT NULL UNIQUE, " + COL_AREA_NAME + " TEXT, " + COL_DESCRIPTION
            + " TEXT);";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);

    public static final ContentValues extractData(Cursor cursor) {
        ContentValues values = new ContentValues();

        values.put(_ID, cursor.getInt(cursor.getColumnIndex(_ID)));
        values.put(COL_SID, cursor.getInt(cursor.getColumnIndex(COL_SID)));
        values.put(COL_AREA_NAME, cursor.getString(cursor.getColumnIndex(COL_AREA_NAME)));
        values.put(COL_DESCRIPTION,
                cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));

        return values;
    }
}
