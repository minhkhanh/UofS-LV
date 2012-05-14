package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MonAnContract implements BaseColumns {
    public static final String TABLE_NAME = "MonAn";

    // public static final String COL_ID = "_id";
    public static final String COL_SID = "MaMonAn";
    public static final String COL_HINH_ANH = "HinhAnh";
    public static final String COL_DIEM_DANH_GIA = "DiemDanhGia";
    public static final String COL_SO_LUOT_RATE = "SoLuotDanhGia";
    public static final String COL_DEFAULT_UNIT_ID = "MaDonViTinhMacDinh";
    public static final String COL_CATEGORY_ID = "MaDanhMuc";
    public static final String COL_UNAVAILABLE = "NgungBan";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SID
            + " INTEGER NOT NULL UNIQUE, " + COL_HINH_ANH + " TEXT, " + COL_DIEM_DANH_GIA
            + " REAL NOT NULL DEFAULT (0), " + COL_SO_LUOT_RATE
            + " INTEGER NOT NULL DEFAULT (0), " + COL_DEFAULT_UNIT_ID
            + " INTEGER REFERENCES " + DonViTinhContract.TABLE_NAME + " ("
            + DonViTinhContract.COL_SID + ")," + COL_CATEGORY_ID + " INTEGER REFERENCES "
            + DanhMucContract.TABLE_NAME + " (" + DanhMucContract.COL_SID + "),"
            + COL_UNAVAILABLE + " BOOLEAN NOT NULL DEFAULT (0)" + ");";

    public static final String PATH_MONAN_INNER_DANGONNGU = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_MONAN_INNER_DANGONNGU = Uri.withAppendedPath(CONTENT_URI,
            PATH_MONAN_INNER_DANGONNGU);

    public static ContentValues extractData(Cursor cursor) {
        ContentValues values = new ContentValues();

        values.put(_ID, cursor.getInt(cursor.getColumnIndex(_ID)));
        values.put(COL_SID, cursor.getInt(cursor.getColumnIndex(COL_SID)));
        values.put(COL_HINH_ANH, cursor.getString(cursor.getColumnIndex(COL_HINH_ANH)));
        values.put(COL_DIEM_DANH_GIA, cursor.getFloat(cursor.getColumnIndex(COL_DIEM_DANH_GIA)));
        values.put(COL_SO_LUOT_RATE, cursor.getInt(cursor.getColumnIndex(COL_SO_LUOT_RATE)));
        values.put(COL_DEFAULT_UNIT_ID,
                cursor.getInt(cursor.getColumnIndex(COL_DEFAULT_UNIT_ID)));
        values.put(COL_CATEGORY_ID, cursor.getInt(cursor.getColumnIndex(COL_CATEGORY_ID)));
        values.put(COL_UNAVAILABLE,
                cursor.getBlob(cursor.getColumnIndex(COL_UNAVAILABLE)));

        return values;
    }

    public String[] getAllColumns() {
        // TODO Auto-generated method stub
        return null;
    }
}
