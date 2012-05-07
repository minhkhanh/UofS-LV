package client.menu.db.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import client.menu.db.contract.BanContract;
import client.menu.db.contract.DanhMucContract;
import client.menu.db.contract.DanhMucDaNgonNguContract;
import client.menu.db.contract.KhuVucContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.contract.NgonNguContract;
import client.menu.db.contract.ThamSoContract;
import client.menu.db.util.MyDatabaseHelper;

public class MyContentProvider extends ContentProvider {

    MyDatabaseHelper dbHelper;

    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "client.menu.db.provider";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int MATCH_DANHMUC_INNER_DANGONNGU = 1;
    private static final int MATCH_NGONNGU_ROW = 2;
    private static final int MATCH_BAN = 3;
    private static final int MATCH_THAMSO_ROW = 4;
    private static final int MATCH_KHUVUC = 5;
    private static final int MATCH_NGONNGU_MACDINH = 6;
    private static final int MATCH_NGONNGU = 7;
    private static final int MATCH_MONAN_INNER_DANGONNGU = 8;

    static {
        uriMatcher.addURI(AUTHORITY, MonAnContract.TABLE_NAME + "/"
                + MonAnContract.PATH_MONAN_INNER_DANGONNGU, MATCH_MONAN_INNER_DANGONNGU);

        uriMatcher.addURI(AUTHORITY, NgonNguContract.TABLE_NAME + "/"
                + NgonNguContract.PATH_NGONNGU_MACDINH, MATCH_NGONNGU_MACDINH);

        uriMatcher.addURI(AUTHORITY, ThamSoContract.TABLE_NAME + "/#", MATCH_THAMSO_ROW);
        uriMatcher
                .addURI(AUTHORITY, NgonNguContract.TABLE_NAME + "/#", MATCH_NGONNGU_ROW);

        uriMatcher.addURI(AUTHORITY, DanhMucContract.TABLE_NAME + "/"
                + DanhMucContract.PATH_DANHMUC_INNER_DANGONNGU,
                MATCH_DANHMUC_INNER_DANGONNGU);

        uriMatcher.addURI(AUTHORITY, BanContract.TABLE_NAME, MATCH_BAN);
        uriMatcher.addURI(AUTHORITY, KhuVucContract.TABLE_NAME, MATCH_KHUVUC);
        uriMatcher.addURI(AUTHORITY, NgonNguContract.TABLE_NAME, MATCH_NGONNGU);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (uriMatcher.match(uri)) {
            case MATCH_MONAN_INNER_DANGONNGU:
                queryBuilder.setTables(MonAnContract.TABLE_NAME + " INNER JOIN "
                        + MonAnDaNgonNguContract.TABLE_NAME + " ON ("
                        + MonAnContract.TABLE_NAME + "." + MonAnContract.COL_SID + " = "
                        + MonAnDaNgonNguContract.TABLE_NAME + "."
                        + MonAnDaNgonNguContract.COL_DISH_ID + ")");
                break;
            case MATCH_NGONNGU_MACDINH:
                queryBuilder.setTables(NgonNguContract.TABLE_NAME + ","
                        + ThamSoContract.TABLE_NAME);
                queryBuilder.appendWhere(NgonNguContract.COL_SID + " = "
                        + ThamSoContract.COL_VALUE + " and " + ThamSoContract.COL_SID
                        + "=" + ThamSoContract.SID_NGONNGU_MACDINH);
                break;
            case MATCH_THAMSO_ROW:
                queryBuilder.setTables(ThamSoContract.TABLE_NAME);
                queryBuilder.appendWhere(ThamSoContract.COL_SID + "="
                        + uri.getLastPathSegment());
                break;
            case MATCH_DANHMUC_INNER_DANGONNGU:
                queryBuilder.setTables(DanhMucContract.TABLE_NAME + " INNER JOIN "
                        + DanhMucDaNgonNguContract.TABLE_NAME + " ON ("
                        + DanhMucContract.TABLE_NAME + "." + DanhMucContract.COL_SID
                        + " = " + DanhMucDaNgonNguContract.TABLE_NAME + "."
                        + DanhMucDaNgonNguContract.COL_CATEGORY_ID + ")");
                break;

            case MATCH_BAN:
                queryBuilder.setTables(BanContract.TABLE_NAME);
                break;

            case MATCH_KHUVUC:
                queryBuilder.setTables(KhuVucContract.TABLE_NAME);
                break;

            case MATCH_NGONNGU:
                queryBuilder.setTables(NgonNguContract.TABLE_NAME);
                break;

            case MATCH_NGONNGU_ROW:
                queryBuilder.setTables(NgonNguContract.TABLE_NAME);
                queryBuilder.appendWhere(NgonNguContract.COL_SID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs,
                null, null, null);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        return 0;
    }

}