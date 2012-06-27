package emenu.client.db.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class MonLienQuanDTO {
    public static final String TABLE_NAME = "ChiTietMonLienQuan";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_MON = "MaMonAn";
    public static final String CL_MA_MON_LIEN_QUAN = "MaMonAnLienQuan";

    public static final String CL_MA_MON_QN = TABLE_NAME + "." + "MaMonAn";

    private Integer mId;
    private Integer mMaMonAn;
    private Integer mMaMonAnLienQuan;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_MON)) {
            values.put(CL_MA_MON, jsonObj.getInt(CL_MA_MON));
        }
        if (!jsonObj.isNull(CL_MA_MON_LIEN_QUAN)) {
            values.put(CL_MA_MON_LIEN_QUAN, jsonObj.getInt(CL_MA_MON_LIEN_QUAN));
        }
        return values;
    }

    public static final List<MonLienQuanDTO> fromArrayCursor(Cursor cursor) {
        List<MonLienQuanDTO> list = new ArrayList<MonLienQuanDTO>();

        while (cursor.moveToNext()) {
            MonLienQuanDTO obj = MonLienQuanDTO.fromCursor(cursor);
            list.add(obj);
        }

        return list;
    }

    public static final MonLienQuanDTO fromCursor(Cursor cursor) {
        MonLienQuanDTO obj = new MonLienQuanDTO();
        Integer i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_MON)) != -1) {
            obj.mMaMonAn = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_MON_LIEN_QUAN)) != -1) {
            obj.mMaMonAnLienQuan = cursor.getInt(i);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

    public void setMaMonAn(Integer maMonAn) {
        mMaMonAn = maMonAn;
    }

    public Integer getMaMonAnLienQuan() {
        return mMaMonAnLienQuan;
    }

    public void setMaMonAnLienQuan(Integer maMonAnLienQuan) {
        mMaMonAnLienQuan = maMonAnLienQuan;
    }

}
