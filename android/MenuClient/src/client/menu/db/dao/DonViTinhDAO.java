package client.menu.db.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.db.util.MyDatabaseHelper;

public class DonViTinhDAO extends AbstractDAO {
    private static DonViTinhDAO mInstance;

    public static final void createInstance(MyDatabaseHelper dbHelper) {
        mInstance = new DonViTinhDAO(dbHelper);
    }

    public static final DonViTinhDAO getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Singleton instance not created yet.");
        }
        return mInstance;
    }

    public DonViTinhDAO(MyDatabaseHelper dbHelper) {
        super(dbHelper);
    }

    public Cursor cursorByDonViTinhMonAn(Integer maMonAn, Integer maDonViTinh,
            Integer maNgonNgu) {
        SQLiteDatabase db = open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(MonAnDaNgonNguDTO.TABLE_NAME + " INNER JOIN "
                + DonViTinhMonAnDTO.TABLE_NAME + " ON (" + MonAnDaNgonNguDTO.CL_MA_MON_QN
                + "=" + DonViTinhMonAnDTO.CL_MA_MON_AN_QN + ")" + "INNER JOIN "
                + DonViTinhDaNgonNguDTO.TABLE_NAME + " ON ("
                + DonViTinhMonAnDTO.CL_MA_DON_VI_QN + "="
                + DonViTinhDaNgonNguDTO.CL_MA_DON_VI_QN + ")");

        String selection = DonViTinhDaNgonNguDTO.CL_MA_NGON_NGU_QN + "=? and "
                + DonViTinhMonAnDTO.CL_MA_MON_AN_QN + "=? and "
                + DonViTinhMonAnDTO.CL_MA_DON_VI_QN + "=?";
        String[] selectionArgs = { maNgonNgu.toString(), maMonAn.toString(),
                maDonViTinh.toString() };
        
        Cursor cursor = queryBuilder.query(db, null, selection, selectionArgs, null, null, null);
        
        return cursor;
    }

    public Cursor cursorByMonAn(Integer maMonAn, Integer maNgonNgu) {
        SQLiteDatabase db = open();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(DonViTinhMonAnDTO.TABLE_NAME + " INNER JOIN "
                + DonViTinhDaNgonNguDTO.TABLE_NAME + " ON ("
                + DonViTinhMonAnDTO.TABLE_NAME + "." + DonViTinhMonAnDTO.CL_MA_DON_VI
                + " = " + DonViTinhDaNgonNguDTO.TABLE_NAME + "."
                + DonViTinhDaNgonNguDTO.CL_MA_DON_VI + ")");

        String selection = DonViTinhDaNgonNguDTO.CL_MA_NGON_NGU + "=? and "
                + DonViTinhMonAnDTO.CL_MA_MON_AN + "=?";
        String[] selectionArgs = { maNgonNgu.toString(), maMonAn.toString() };

        Cursor cursor = queryBuilder.query(db, null, selection, selectionArgs, null,
                null, null);

        return cursor;
    }

    public List<DonViTinhDaNgonNguDTO> byMaNgonNgu(Integer maNgonNgu) {
        List<DonViTinhDaNgonNguDTO> list = new ArrayList<DonViTinhDaNgonNguDTO>();

        try {
            SQLiteDatabase db = open();

            String selection = DonViTinhDaNgonNguDTO.CL_MA_NGON_NGU + "=?";
            String[] selectionArgs = { maNgonNgu.toString() };

            Cursor cursor = db.query(DonViTinhDaNgonNguDTO.TABLE_NAME, null, selection,
                    selectionArgs, null, null, null, null);

            while (cursor.moveToNext()) {
                DonViTinhDaNgonNguDTO obj = DonViTinhDaNgonNguDTO.extractFrom(cursor);
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return list;
    }
}
