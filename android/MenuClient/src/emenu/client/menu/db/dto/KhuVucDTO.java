package client.menu.db.dto;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class KhuVucDTO {
    public static final String TABLE_NAME = "KhuVuc";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_KHU_VUC = "MaKhuVuc";
    public static final String CL_TEN_KHU_VUC = "TenKhuVuc";
    public static final String CL_MO_TA = "MoTa";

    private Integer mId;
    private Integer mMaKhuVuc;
    private String mTenKhuVuc;
    private String mMoTa;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_KHU_VUC)) {
            values.put(CL_MA_KHU_VUC, jsonObj.getInt(CL_MA_KHU_VUC));
        }
        if (!jsonObj.isNull(CL_TEN_KHU_VUC)) {
            values.put(CL_TEN_KHU_VUC, jsonObj.getString(CL_TEN_KHU_VUC));
        }
        if (!jsonObj.isNull(CL_MO_TA)) {
            values.put(CL_MO_TA, jsonObj.getString(CL_MO_TA));
        }

        return values;
    }

    public static final KhuVucDTO extractFrom(Cursor cursor) {
        KhuVucDTO obj = new KhuVucDTO();
        Integer i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_KHU_VUC)) != -1) {
            obj.mMaKhuVuc = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_KHU_VUC)) != -1) {
            obj.mTenKhuVuc = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_MO_TA)) != -1) {
            obj.mMoTa = cursor.getString(i);
        }

        return obj;
    }

    public Integer getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(Integer maKhuVuc) {
        this.mMaKhuVuc = maKhuVuc;
    }

    public String getTenKhuVuc() {
        return mTenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.mTenKhuVuc = tenKhuVuc;
    }

    public String getMoTa() {
        return mMoTa;
    }

    public void setMoTa(String moTa) {
        this.mMoTa = moTa;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    @Override
    public String toString() {
        return getTenKhuVuc();
    }
}
