package client.menu.db.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import client.menu.db.contract.BanContract;
import client.menu.db.contract.KhuVucContract;
import client.menu.db.contract.NgonNguContract;
import client.menu.db.util.MyDatabaseHelper;

public class MenuClientContentProvider extends ContentProvider {

    MyDatabaseHelper dbHelper;

    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "client.menu.db.provider";

    private static final UriMatcher uriMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

//    private static final int MATCH_TABLE_MONAN = 1;
//    private static final int MATCH_ROW_MONAN = 2;
    private static final int MATCH_BAN = 3;
//    private static final int MATCH_ROW_BAN = 4;
    private static final int MATCH_KHUVUC = 5;
//    private static final int MATCH_ROW_KHUVUC = 6;
    private static final int MATCH_NGONNGU = 7;

    static {
        uriMatcher.addURI(AUTHORITY, BanContract.TABLE_NAME, MATCH_BAN);
        uriMatcher.addURI(AUTHORITY, KhuVucContract.TABLE_NAME,
                MATCH_KHUVUC);
        uriMatcher.addURI(AUTHORITY, NgonNguContract.TABLE_NAME,
                MATCH_NGONNGU);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // SQLiteDatabase db = dbHelper.getWritableDatabase();
        //
        // int rowsDeleted = 0;
        // switch (uriMatcher.match(uri)) {
        // case MATCH_ROW_MONAN:
        // String appendedWhere = BaseColumns._ID + " = "
        // + uri.getLastPathSegment();
        // if (TextUtils.isEmpty(selection)) {
        // selection = appendedWhere;
        // } else {
        // selection += " and " + appendedWhere;
        // }
        // break;
        // default:
        // throw new IllegalArgumentException("Unknown URI: " + uri);
        // }
        //
        // rowsDeleted = db.delete(MonAnContract.TABLE_NAME, selection,
        // selectionArgs);
        // getContext().getContentResolver().notifyChange(uri, null);

        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        long rowId;
//        switch (uriMatcher.match(uri)) {
//            case MATCH_TABLE_MONAN:
//                rowId = db.insert(MonAnContract.TABLE_NAME, null, values);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI: " + uri);
//        }
//
//        getContext().getContentResolver().notifyChange(uri, null);
//        return ContentUris.withAppendedId(uri, rowId);
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
            case MATCH_BAN:
                queryBuilder.setTables(BanContract.TABLE_NAME);
                break;

            case MATCH_KHUVUC:
                queryBuilder.setTables(KhuVucContract.TABLE_NAME);
                break;

            case MATCH_NGONNGU:
                queryBuilder.setTables(NgonNguContract.TABLE_NAME);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, null);

//        Log.d(C.TAG, queryBuilder.)

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        return 0;
    }

}
