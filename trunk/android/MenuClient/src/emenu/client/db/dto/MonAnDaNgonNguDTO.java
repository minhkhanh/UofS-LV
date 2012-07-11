package emenu.client.db.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class MonAnDaNgonNguDTO {

    public static final String TABLE_NAME = "ChiTietMonAnDaNgonNgu";
    public static final String TABLE_NAME_FTS = "ChiTietMonAnDaNgonNgu_FTS";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_MON = "MaMonAn";
    public static final String CL_MA_MON_QN = TABLE_NAME + "." + "MaMonAn";
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_MA_NGON_NGU_QN = TABLE_NAME + ".MaNgonNgu";
    public static final String CL_TEN_MON = "TenMonAn";
    public static final String CL_TEN_MON_QN = TABLE_NAME + "." + "TenMonAn";
    public static final String CL_MO_TA_MON = "MoTaMonAn";

    public static final String CL_ID_QN = TABLE_NAME + "." + BaseColumns._ID;

    private Integer mId;
    private Integer mMaMonAn;
    private Integer mMaNgonNgu;
    private String mTenMonAn;
    private String mMoTaMonAn;

    public static final List<String> getAllColumns() {
        List<String> list = new ArrayList<String>();
        list.add(CL_ID);
        list.add(CL_MA_MON);
        list.add(CL_MA_NGON_NGU);
        list.add(CL_MO_TA_MON);
        list.add(CL_TEN_MON);

        return list;
    }

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_MON)) {
            values.put(CL_MA_MON, jsonObj.getInt(CL_MA_MON));
        }
        if (!jsonObj.isNull(CL_MA_NGON_NGU)) {
            values.put(CL_MA_NGON_NGU, jsonObj.getInt(CL_MA_NGON_NGU));
        }
        if (!jsonObj.isNull(CL_TEN_MON)) {
            values.put(CL_TEN_MON, jsonObj.getString(CL_TEN_MON));
        }
        if (!jsonObj.isNull(CL_MO_TA_MON)) {
            values.put(CL_MO_TA_MON, jsonObj.getString(CL_MO_TA_MON));
        }
        return values;
    }

    public static List<MonAnDaNgonNguDTO> fromArrayCursor(Cursor cursor) {
        List<MonAnDaNgonNguDTO> list = new ArrayList<MonAnDaNgonNguDTO>();
        while (cursor.moveToNext()) {
            MonAnDaNgonNguDTO obj = MonAnDaNgonNguDTO.fromCursor(cursor);
            list.add(obj);
        }

        return list;
    }

    public static MonAnDaNgonNguDTO fromCursor(Cursor cursor) {
        MonAnDaNgonNguDTO obj = new MonAnDaNgonNguDTO();

        int index;
        if ((index = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_MON)) != -1) {
            obj.mMaMonAn = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_NGON_NGU)) != -1) {
            obj.mMaNgonNgu = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_TEN_MON)) != -1) {
            obj.mTenMonAn = cursor.getString(index);
        }
        if ((index = cursor.getColumnIndex(CL_MO_TA_MON)) != -1) {
            obj.mMoTaMonAn = cursor.getString(index);
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
        this.mMaMonAn = maMonAn;
    }

    public Integer getMaNgonNgu() {
        return mMaNgonNgu;
    }

    public void setMaNgonNgu(Integer maNgonNgu) {
        this.mMaNgonNgu = maNgonNgu;
    }

    public String getTenMonAn() {
        return mTenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.mTenMonAn = tenMonAn;
    }

    public String getMoTaMonAn() {
        return mMoTaMonAn;
    }

    public void setMoTaMonAn(String moTaMonAn) {
        this.mMoTaMonAn = moTaMonAn;
    }
}
