package emenu.client.menu.dao;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import emenu.client.db.dto.ThamSoDTO;
import emenu.client.db.util.MyDatabaseHelper;

public class ThamSoDAO extends AbstractDAO {

    private static ThamSoDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new ThamSoDAO(dbHelper);
    }

    public static final ThamSoDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    private List<ThamSoDTO> mCached;

    private ThamSoDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public ThamSoDTO byTenThamSo(String tenThamSo) {
        ThamSoDTO obj = null;

        try {
            SQLiteDatabase db = open();
            String selection = ThamSoDTO.CL_PARAM_NAME + "=?";
            String[] selectionArgs = { tenThamSo };

            Cursor cursor = db.query(ThamSoDTO.TABLE_NAME, null, selection,
                    selectionArgs, null, null, null, null);

            cursor.moveToFirst();
            obj = ThamSoDTO.fromCursor(cursor);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return obj;
    }

    @Override
    public boolean syncAll() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    protected void createCache(Cursor cursor) {
        mCached = ThamSoDTO.fromArrayCursor(cursor);
    }
}
