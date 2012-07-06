package emenu.client.db.dto;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;

import emenu.client.util.U;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class PhuThuDTO {
    public static final String TABLE_NAME = "PhuThu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_PHU_THU = "MaPhuThu";
    public static final String CL_TEN_PHU_THU = "TenPhuThu";
    public static final String CL_GIA_TANG = "GiaTang";
    public static final String CL_TI_LE_TANG = "TiLeTang";
    public static final String CL_BAT_DAU = "BatDau";
    public static final String CL_KET_THUC = "KetThuc";
    public static final String CL_LICH_PHU_THU = "LichPhuThu";

    public static final String CL_MA_PHU_THU_QN = TABLE_NAME + "." + CL_MA_PHU_THU;

    private Integer mId;
    private Integer mMaPhuThu;
    private String mTenPhuThu;
    private Integer mGiaTang;
    private Float mTiLeTang;
    private Long mBatDau;
    private Long mKetThuc;
    private String mLichPhuThu;

    public static final PhuThuDTO fromCursor(Cursor cursor) {
        PhuThuDTO obj = new PhuThuDTO();
        int i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_BAT_DAU)) != -1) {
            obj.mBatDau = cursor.getLong(i);
        }
        if ((i = cursor.getColumnIndex(CL_KET_THUC)) != -1) {
            obj.mKetThuc = cursor.getLong(i);
        }
        if ((i = cursor.getColumnIndex(CL_GIA_TANG)) != -1) {
            obj.mGiaTang = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_LICH_PHU_THU)) != -1) {
            obj.mLichPhuThu = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_PHU_THU)) != -1) {
            obj.mMaPhuThu = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_PHU_THU)) != -1) {
            obj.mTenPhuThu = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_TI_LE_TANG)) != -1) {
            obj.mTiLeTang = cursor.getFloat(i);
        }

        return obj;
    }

    public ContentValues toContentValues() {
        ContentValues c = new ContentValues();
        if (mKetThuc != null)
            c.put(CL_KET_THUC, mKetThuc);
        if (mBatDau != null)
            c.put(CL_BAT_DAU, mBatDau);
        if (mGiaTang != null)
            c.put(CL_GIA_TANG, mGiaTang);
        if (mId != null)
            c.put(CL_ID, mId);
        if (mLichPhuThu != null)
            c.put(CL_LICH_PHU_THU, mLichPhuThu);
        if (mMaPhuThu != null)
            c.put(CL_MA_PHU_THU, mMaPhuThu);
        if (mTenPhuThu != null)
            c.put(CL_TEN_PHU_THU, mTenPhuThu);
        if (mTiLeTang != null)
            c.put(CL_TI_LE_TANG, mTiLeTang);

        return c;
    }

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        return fromJson(jsonObj).toContentValues();
    }

    public static final PhuThuDTO fromJson(JSONObject jsonObj) throws JSONException {
        PhuThuDTO obj = new PhuThuDTO();
        if (!jsonObj.isNull(CL_BAT_DAU)) {
            obj.mBatDau = U.getTimeFromJsonDate(jsonObj.getString(CL_BAT_DAU));
        }
        if (!jsonObj.isNull(CL_KET_THUC)) {
            obj.mKetThuc = U.getTimeFromJsonDate(jsonObj.getString(CL_KET_THUC));
        }
        if (!jsonObj.isNull(CL_GIA_TANG)) {
            obj.mGiaTang = jsonObj.getInt(CL_GIA_TANG);
        }
        if (!jsonObj.isNull(CL_LICH_PHU_THU)) {
            obj.mLichPhuThu = jsonObj.getString(CL_LICH_PHU_THU);
        }
        if (!jsonObj.isNull(CL_MA_PHU_THU)) {
            obj.mMaPhuThu = jsonObj.getInt(CL_MA_PHU_THU);
        }
        if (!jsonObj.isNull(CL_TEN_PHU_THU)) {
            obj.mTenPhuThu = jsonObj.getString(CL_TEN_PHU_THU);
        }
        if (!jsonObj.isNull(CL_TI_LE_TANG)) {
            // divide by 100 because server stores [0..100]
            obj.mTiLeTang = (float) (jsonObj.getDouble(CL_TI_LE_TANG) / 100);
        }

        return obj;
    }

    public Integer getMaPhuThu() {
        return mMaPhuThu;
    }

    public void setMaPhuThu(Integer maPhuThu) {
        mMaPhuThu = maPhuThu;
    }

    public String getTenPhuThu() {
        return mTenPhuThu;
    }

    public void setTenPhuThu(String tenPhuThu) {
        mTenPhuThu = tenPhuThu;
    }

    public Integer getGiaTang() {
        return mGiaTang;
    }

    public void setGiaTang(Integer giaTang) {
        mGiaTang = giaTang;
    }

    public Float getTiLeTang() {
        return mTiLeTang;
    }

    public void setTiLeTang(Float tiLeTang) {
        mTiLeTang = tiLeTang;
    }

    public Long getBatDau() {
        return mBatDau;
    }

    public void setBatDau(Long batDau) {
        mBatDau = batDau;
    }

    public Long getKetThuc() {
        return mKetThuc;
    }

    public void setKetThuc(Long ketThuc) {
        mKetThuc = ketThuc;
    }

    public String getLichPhuThu() {
        return mLichPhuThu;
    }

    public void setLichPhuThu(String lichPhuThu) {
        mLichPhuThu = lichPhuThu;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
}
