package emenu.client.menu.db.dto;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

public class KhuyenMaiDTO {
    public static final String TABLE_NAME = "KhuyenMai";
    public static final String CL_MA_KHUYEN_MAI = "MaKhuyenMai";
    public static final String CL_TEN_KHUYEN_MAI = "TenKhuyenMai";
    public static final String CL_GIA_GIAM = "GiaGiam";
    public static final String CL_TI_LE_GIAM = "TiLeGiam";
    public static final String CL_BAT_DAU = "BatDau";
    public static final String CL_KET_THUC = "KetThuc";
    public static final String CL_LICH_KHUYEN_MAI = "LichKhuyenMai";
    
    public static final String CL_EX_PROM_TOTAL = "PromTotal";

    private Integer mMaKhuyenMai;
    private String mTenKhuyenMai;
    private Integer mGiaGiam;
    private Float mTiLeGiam;
    private Date mBatDau;
    private Date mKetThuc;
    private String mLichKhuyenMai;

    public static final ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        KhuyenMaiDTO obj = fromJson(jsonObj);
        return obj.toContentValues();
    }

    public static final KhuyenMaiDTO fromJson(JSONObject jsonObj) throws JSONException {
        KhuyenMaiDTO obj = new KhuyenMaiDTO();
        if (!jsonObj.isNull(CL_BAT_DAU)) {
            // obj.mBatDau = jsonObj.getString(CL_BAT_DAU);
        }
        if (!jsonObj.isNull(CL_KET_THUC)) {
            // obj.mBatDau = jsonObj.getString(CL_KET_THUC);
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
            obj.mTiLeGiam = (float) jsonObj.getDouble(CL_TI_LE_GIAM);
        }

        return obj;
    }

    public ContentValues toContentValues() {
        ContentValues c = new ContentValues();
        c.put(CL_KET_THUC, mKetThuc.toString());
        c.put(CL_BAT_DAU, mBatDau.toString());
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

    public Date getBatDau() {
        return mBatDau;
    }

    public void setBatDau(Date batDau) {
        mBatDau = batDau;
    }

    public Date getKetThuc() {
        return mKetThuc;
    }

    public void setKetThuc(Date ketThuc) {
        mKetThuc = ketThuc;
    }

    public String getLichKhuyenMai() {
        return mLichKhuyenMai;
    }

    public void setLichKhuyenMai(String lichKhuyenMai) {
        mLichKhuyenMai = lichKhuyenMai;
    }
}
