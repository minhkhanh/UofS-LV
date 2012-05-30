package client.menu.db.dto;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

public class TiGiaDTO {
    public static final String TABLE_NAME = "TiGia";

    public static final String CL_MA_TI_GIA = "MaTiGia";
    public static final String CL_KI_HIEU = "KiHieu";
    public static final String CL_GIA_TRI = "GiaTri";
    public static final String CL_CAP_NHAT = "ThoiDiemCapNhat";

    private Integer mId;
    private Integer mMaTiGia;
    private String mGiaTri;
    private Date mThoiDiemCapNhat;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_TI_GIA)) {
            values.put(CL_MA_TI_GIA, jsonObj.getInt(CL_MA_TI_GIA));
        }
        if (!jsonObj.isNull(CL_KI_HIEU)) {
            values.put(CL_KI_HIEU, jsonObj.getString(CL_KI_HIEU));
        }
        if (!jsonObj.isNull(CL_GIA_TRI)) {
            values.put(CL_GIA_TRI, jsonObj.getDouble(CL_GIA_TRI));
        }
        if (!jsonObj.isNull(CL_CAP_NHAT)) {
            values.put(CL_CAP_NHAT, jsonObj.getString(CL_CAP_NHAT));
        }
        return values;
    }
    
    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaTiGia() {
        return mMaTiGia;
    }

    public void setMaTiGia(Integer maTiGia) {
        mMaTiGia = maTiGia;
    }

    public String getGiaTri() {
        return mGiaTri;
    }

    public void setGiaTri(String giaTri) {
        mGiaTri = giaTri;
    }

    public Date getThoiDiemCapNhat() {
        return mThoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(Date thoiDiemCapNhat) {
        mThoiDiemCapNhat = thoiDiemCapNhat;
    }

}
