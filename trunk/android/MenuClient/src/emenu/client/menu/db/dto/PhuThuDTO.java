package emenu.client.menu.db.dto;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class PhuThuDTO {
    public static final String TABLE_NAME = "PhuThu";
    public static final String CL_MA_PHU_THU = "MaPhuThu";
    public static final String CL_TEN_PHU_THU = "TenPhuThu";
    public static final String CL_GIA_TANG = "GiaTang";
    public static final String CL_TI_LE_TANG = "TiLeTang";
    public static final String CL_BAT_DAU = "BatDau";
    public static final String CL_KET_THUC = "KetThuc";
    public static final String CL_LICH_PHU_THU = "LichPhuThu";

    private Integer mMaPhuThu;
    private String mTenPhuThu;
    private Integer mGiaTang;
    private Float mTiLeTang;
    private Date mBatDau;
    private Date mKetThuc;
    private String mLichPhuThu;

    public static final PhuThuDTO fromJson(JSONObject jsonObj) throws JSONException {
        PhuThuDTO obj = new PhuThuDTO();
        if (!jsonObj.isNull(CL_BAT_DAU)) {
            // obj.mBatDau = jsonObj.getString(CL_BAT_DAU);
        }
        if (!jsonObj.isNull(CL_KET_THUC)) {
            // obj.mBatDau = jsonObj.getString(CL_KET_THUC);
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
            obj.mTiLeTang = (float) jsonObj.getDouble(CL_TI_LE_TANG);
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

    public String getLichPhuThu() {
        return mLichPhuThu;
    }

    public void setLichPhuThu(String lichPhuThu) {
        mLichPhuThu = lichPhuThu;
    }
}
