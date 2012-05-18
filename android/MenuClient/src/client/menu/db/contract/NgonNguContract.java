package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class NgonNguContract  {
    public static final String TABLE_NAME = "NgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_TEN_NGON_NGU = "TenNgonNgu";
    public static final String CL_KI_HIEU = "KiHieu";
    
    public static final String PATH_NGONNGU_MACDINH = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_NGONNGU_MACDINH = Uri.withAppendedPath(CONTENT_URI,
            PATH_NGONNGU_MACDINH);

    public static final ContentValues extractData(Cursor cursor) {
        ContentValues values = new ContentValues();

        values.put(CL_ID, cursor.getInt(cursor.getColumnIndex(CL_ID)));
        values.put(CL_MA_NGON_NGU, cursor.getInt(cursor.getColumnIndex(CL_MA_NGON_NGU)));
        values.put(CL_TEN_NGON_NGU,
                cursor.getString(cursor.getColumnIndex(CL_TEN_NGON_NGU)));
        values.put(CL_KI_HIEU, cursor.getString(cursor.getColumnIndex(CL_KI_HIEU)));

        return values;
    }

    public static final String[] getFullProjection() {
        return new String[] { "" + CL_ID, CL_MA_NGON_NGU, CL_TEN_NGON_NGU,
                CL_KI_HIEU };
    }
}
