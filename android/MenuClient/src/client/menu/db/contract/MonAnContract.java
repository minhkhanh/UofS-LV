package client.menu.db.contract;

import client.menu.db.provider.MyContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class MonAnContract  {
    public static final String TABLE_NAME = "MonAn";

    public static final String CL_ID = TABLE_NAME + BaseColumns._ID;
    public static final String CL_MA_MON_AN = TABLE_NAME + ".MaMonAn";
    public static final String CL_HINH_ANH = TABLE_NAME + ".HinhAnh";
    public static final String CL_DIEM_DANH_GIA = TABLE_NAME + ".DiemDanhGia";
    public static final String CL_SO_LUOT_RATE = TABLE_NAME + ".SoLuotDanhGia";
    public static final String CL_DEFAULT_UNIT_ID = TABLE_NAME + ".MaDonViTinhMacDinh";
    public static final String CL_MA_DANH_MUC = TABLE_NAME + ".MaDanhMuc";
    public static final String CL_NGUNG_BAN = TABLE_NAME + ".NgungBan";

    public static final String PATH_MONAN_INNER_DANGONNGU = "01";

    public static final Uri CONTENT_URI = Uri.parse(MyContentProvider.SCHEME
            + MyContentProvider.AUTHORITY + "/" + TABLE_NAME);
    public static final Uri URI_MONAN_INNER_DANGONNGU = Uri.withAppendedPath(CONTENT_URI,
            PATH_MONAN_INNER_DANGONNGU);

    public static ContentValues extractData(Cursor cursor) {
        ContentValues values = new ContentValues();

        values.put(CL_ID, cursor.getInt(cursor.getColumnIndex(CL_ID)));
        values.put(CL_MA_MON_AN, cursor.getInt(cursor.getColumnIndex(CL_MA_MON_AN)));
        values.put(CL_HINH_ANH, cursor.getString(cursor.getColumnIndex(CL_HINH_ANH)));
        values.put(CL_DIEM_DANH_GIA,
                cursor.getFloat(cursor.getColumnIndex(CL_DIEM_DANH_GIA)));
        values.put(CL_SO_LUOT_RATE, cursor.getInt(cursor.getColumnIndex(CL_SO_LUOT_RATE)));
        values.put(CL_DEFAULT_UNIT_ID,
                cursor.getInt(cursor.getColumnIndex(CL_DEFAULT_UNIT_ID)));
        values.put(CL_MA_DANH_MUC, cursor.getInt(cursor.getColumnIndex(CL_MA_DANH_MUC)));
        values.put(CL_NGUNG_BAN, cursor.getBlob(cursor.getColumnIndex(CL_NGUNG_BAN)));

        return values;
    }

    public String[] getAllColumns() {
        // TODO Auto-generated method stub
        return null;
    }
}
