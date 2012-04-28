package client.menu.db.provider;

import client.menu.db.contract.MonAnContract;
import client.menu.db.util.MyDatabaseHelper;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;

public class MenuClientContentProvider extends ContentProvider {

    MyDatabaseHelper dbHelper;

    public static final String AUTHORITY = "client.menu.db.provider";

    private static final UriMatcher uriMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    private static final int MATCH_TABLE_MONAN = 1;
    private static final int MATCH_ROW_MONAN = 2;

    static {
        uriMatcher.addURI(AUTHORITY, MonAnContract.TABLE_NAME,
                MATCH_TABLE_MONAN);
        uriMatcher.addURI(AUTHORITY, MonAnContract.TABLE_NAME + "/#",
                MATCH_ROW_MONAN);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsDeleted = 0;
        switch (uriMatcher.match(uri)) {
            case MATCH_ROW_MONAN:
                String appendedWhere = BaseColumns._ID + " = "
                        + uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = appendedWhere;
                } else {
                    selection += " and " + appendedWhere;
                }
                break;
            case MATCH_TABLE_MONAN:

                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        rowsDeleted = db.delete(MonAnContract.TABLE_NAME, selection,
                selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long rowId;
        switch (uriMatcher.match(uri)) {
            case MATCH_TABLE_MONAN:
                rowId = db.insert(MonAnContract.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, rowId);
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
            case MATCH_TABLE_MONAN:
                queryBuilder.setTables(MonAnContract.TABLE_NAME);
                break;

            case MATCH_ROW_MONAN:
                queryBuilder.appendWhere(BaseColumns._ID + " = "
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, null);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        return 0;
    }

}
