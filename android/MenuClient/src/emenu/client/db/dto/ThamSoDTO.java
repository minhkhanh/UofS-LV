package emenu.client.db.dto;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.provider.BaseColumns;

public class ThamSoDTO {
    public static final String TABLE_NAME = "ThamSo";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_PARAM_NAME = "Ten";
    public static final String CL_VALUE = "GiaTri";

    private Integer mId;
    private String mTenThamSo;
    private String mGiaTri;

    public static List<ThamSoDTO> fromArrayCursor(Cursor cursor) {
        List<ThamSoDTO> list = new ArrayList<ThamSoDTO>();

        while (cursor.moveToNext()) {
            ThamSoDTO obj = ThamSoDTO.fromCursor(cursor);
            list.add(obj);
        }

        return list;
    }

    public static final ThamSoDTO fromCursor(Cursor cursor) {
        ThamSoDTO obj = new ThamSoDTO();

        int index;
        if ((index = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_PARAM_NAME)) != -1) {
            obj.mTenThamSo = cursor.getString(index);
        }
        if ((index = cursor.getColumnIndex(CL_VALUE)) != -1) {
            obj.mGiaTri = cursor.getString(index);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getTenThamSo() {
        return mTenThamSo;
    }

    public void setTenThamSo(String tenThamSo) {
        mTenThamSo = tenThamSo;
    }

    public String getGiaTri() {
        return mGiaTri;
    }

    public void setGiaTri(String giaTri) {
        mGiaTri = giaTri;
    }

}
