package emenu.client.db.dto;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;

import emenu.client.util.U;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class KhuyenMaiDTO {
    public static final String TABLE_NAME = "KhuyenMai";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_KHUYEN_MAI = "MaKhuyenMai";
    public static final String CL_TEN_KHUYEN_MAI = "TenKhuyenMai";
    public static final String CL_GIA_GIAM = "GiaGiam";
    public static final String CL_TI_LE_GIAM = "TiLeGiam";
    public static final String CL_BAT_DAU = "BatDau";
    public static final String CL_KET_THUC = "KetThuc";
    public static final String CL_LICH_KHUYEN_MAI = "LichKhuyenMai";

    public static final String CL_MA_KHUYEN_MAI_QN = TABLE_NAME + "." + "MaKhuyenMai";

    public static final String CL_EX_PROM_TOTAL = "PromTotal";

    private Integer mId;

    private Integer mMaKhuyenMai;
    private String mTenKhuyenMai;
    private Integer mGiaGiam;
    private Float mTiLeGiam;
    private Long mBatDau;
    private Long mKetThuc;
    private String mLichKhuyenMai;

    public static final KhuyenMaiDTO fromCursor(Cursor cursor) {
        KhuyenMaiDTO obj = new KhuyenMaiDTO();
        Integer i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_BAT_DAU)) != -1) {
            obj.mBatDau = cursor.getLong(i);
        }
        if ((i = cursor.getColumnIndex(CL_GIA_GIAM)) != -1) {
            obj.mGiaGiam = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_KET_THUC)) != -1) {
            obj.mKetThuc = cursor.getLong(i);
        }
        if ((i = cursor.getColumnIndex(CL_LICH_KHUYEN_MAI)) != -1) {
            obj.mLichKhuyenMai = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_KHUYEN_MAI)) != -1) {
            obj.mMaKhuyenMai = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_KHUYEN_MAI)) != -1) {
            obj.mTenKhuyenMai = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_TI_LE_GIAM)) != -1) {
            obj.mTiLeGiam = cursor.getFloat(i);
        }

        return obj;
    }

    public static final ContentValues toContentValues(JSONObject jsonObj)
            throws JSONException {
        KhuyenMaiDTO obj = fromJson(jsonObj);
        return obj.toContentValues();
    }

    public static final KhuyenMaiDTO fromJson(JSONObject jsonObj) throws JSONException {
        KhuyenMaiDTO obj = new KhuyenMaiDTO();
        if (!jsonObj.isNull(CL_BAT_DAU)) {
            obj.mBatDau = U.getTimeFromJsonDate(jsonObj.getString(CL_BAT_DAU));
        }
        if (!jsonObj.isNull(CL_KET_THUC)) {
            obj.mKetThuc = U.getTimeFromJsonDate(jsonObj.getString(CL_KET_THUC));
        }
        if (!jsonObj.isNull(CL_GIA_GIAM)) {
            obj.mGiaGiam = jsonObj.getInt(CL_GIA_GIAM);
        }
        if (!jsonObj.isNull(CL_LICH_KHUYEN_MAI)) {
            obj.mLichKhuyenMai = jsonObj.getString(CL_LICH_KHUYEN_MAI);
        }
        if (!jsonObj.isNull(CL_MA_KHUYEN_MAI)) {
            obj.mMaKhuyenMai = jsonObj.getInt(CL_MA_KHUYEN_MAI);
        }
        if (!jsonObj.isNull(CL_TEN_KHUYEN_MAI)) {
            obj.mTenKhuyenMai = jsonObj.getString(CL_TEN_KHUYEN_MAI);
        }
        if (!jsonObj.isNull(CL_TI_LE_GIAM)) {
            obj.mTiLeGiam = (float) jsonObj.getDouble(CL_TI_LE_GIAM) / 100;
        }

        return obj;
    }

    public ContentValues toContentValues() {
        ContentValues c = new ContentValues();
        c.put(CL_ID, mId);
        c.put(CL_KET_THUC, mKetThuc);
        c.put(CL_BAT_DAU, mBatDau);
        c.put(CL_GIA_GIAM, mGiaGiam);
        c.put(CL_LICH_KHUYEN_MAI, mLichKhuyenMai);
        c.put(CL_MA_KHUYEN_MAI, mMaKhuyenMai);
        c.put(CL_TEN_KHUYEN_MAI, mTenKhuyenMai);
        c.put(CL_TI_LE_GIAM, mTiLeGiam);

        return c;
    }

    public Integer getMaKhuyenMai() {
        return mMaKhuyenMai;
    }

    public void setMaKhuyenMai(Integer maKhuyenMai) {
        mMaKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return mTenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        mTenKhuyenMai = tenKhuyenMai;
    }

    public Integer getGiaGiam() {
        return mGiaGiam;
    }

    public void setGiaGiam(Integer giaGiam) {
        mGiaGiam = giaGiam;
    }

    public Float getTiLeGiam() {
        return mTiLeGiam;
    }

    public void setTiLeGiam(Float tiLeGiam) {
        mTiLeGiam = tiLeGiam;
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

    public String getLichKhuyenMai() {
        return mLichKhuyenMai;
    }

    public void setLichKhuyenMai(String lichKhuyenMai) {
        mLichKhuyenMai = lichKhuyenMai;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }
}
