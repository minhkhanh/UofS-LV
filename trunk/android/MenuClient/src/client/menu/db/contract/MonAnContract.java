package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MonAnContract implements BaseColumns {
    public static final String TABLE_NAME = "MonAn";

    // public static final String COL_ID = "_id";
    public static final String COL_MA_MON_AN = "MaMonAn";
    public static final String COL_HINH_ANH = "HinhAnh";
    public static final String COL_DIEM_DANH_GIA = "DiemDanhGia";
    public static final String COL_SO_LUOT_RATE = "SoLuotDanhGia";
    public static final String COL_DEFAULT_UNIT_ID = "MaDonViTinhMacDinh";
    public static final String COL_MA_DANH_MUC = "MaDanhMuc";
    public static final String COL_NGUNG_BAN = "NgungBan";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INT PRIMARY KEY AUTOINCREMENT, " + COL_MA_MON_AN
            + " INT NOT NULL UNIQUE, " + COL_HINH_ANH + " TEXT, " + COL_DIEM_DANH_GIA
            + " REAL NOT NULL DEFAULT (0), " + COL_SO_LUOT_RATE
            + " INT NOT NULL DEFAULT (0), " + COL_DEFAULT_UNIT_ID + " INT REFERENCES "
            + DonViTinhContract.TABLE_NAME + " (" + DonViTinhContract.COL_MA_DON_VI_TINH
            + ")," + COL_MA_DANH_MUC + " INT REFERENCES " + DanhMucContract.TABLE_NAME
            + " (" + DanhMucContract.COL_MA_DANH_MUC + ")," + COL_NGUNG_BAN
            + " INT NOT NULL DEFAULT (0));";

    public static final String PATH_MONAN_INNER_DANGONNGU = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_MONAN_INNER_DANGONNGU = Uri.withAppendedPath(CONTENT_URI,
            PATH_MONAN_INNER_DANGONNGU);

    public static ContentValues extractData(Cursor cursor) {
        ContentValues values = new ContentValues();

        values.put(_ID, cursor.getInt(cursor.getColumnIndex(_ID)));
        values.put(COL_MA_MON_AN, cursor.getInt(cursor.getColumnIndex(COL_MA_MON_AN)));
        values.put(COL_HINH_ANH, cursor.getString(cursor.getColumnIndex(COL_HINH_ANH)));
        values.put(COL_DIEM_DANH_GIA,
                cursor.getFloat(cursor.getColumnIndex(COL_DIEM_DANH_GIA)));
        values.put(COL_SO_LUOT_RATE,
                cursor.getInt(cursor.getColumnIndex(COL_SO_LUOT_RATE)));
        values.put(COL_DEFAULT_UNIT_ID,
                cursor.getInt(cursor.getColumnIndex(COL_DEFAULT_UNIT_ID)));
        values.put(COL_MA_DANH_MUC, cursor.getInt(cursor.getColumnIndex(COL_MA_DANH_MUC)));
        values.put(COL_NGUNG_BAN, cursor.getBlob(cursor.getColumnIndex(COL_NGUNG_BAN)));

        return values;
    }

    public String[] getAllColumns() {
        // TODO Auto-generated method stub
        return null;
    }
}
