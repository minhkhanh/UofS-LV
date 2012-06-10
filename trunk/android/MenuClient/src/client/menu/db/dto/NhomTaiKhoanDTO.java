package client.menu.db.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class NhomTaiKhoanDTO {
    public static final String TABLE_NAME = "NhomTaiKhoan";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_NHOM = "MaNhomTaiKhoan";
    public static final String CL_TEN_NHOM = "TenNhom";
    public static final String CL_MO_TA_NHOM = "MoTaNhom";

    private Integer mId;
    private Integer mMaNhom;
    private String mTenNhom;
    private String mMoTaNhom;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_NHOM)) {
            values.put(CL_MA_NHOM, jsonObj.getInt(CL_MA_NHOM));
        }
        if (!jsonObj.isNull(CL_TEN_NHOM)) {
            values.put(CL_TEN_NHOM, jsonObj.getString(CL_TEN_NHOM));
        }
        if (!jsonObj.isNull(CL_MO_TA_NHOM)) {
            values.put(CL_MO_TA_NHOM, jsonObj.getString(CL_MO_TA_NHOM));
        }
        return values;
    }
    
    public static List<NhomTaiKhoanDTO> fromArrayCursor(Cursor cursor) {
        List<NhomTaiKhoanDTO> list = new ArrayList<NhomTaiKhoanDTO>();
        
        while (cursor.moveToNext()) {
            NhomTaiKhoanDTO obj = NhomTaiKhoanDTO.fromCursor(cursor);
            list.add(obj);
        }
        
        return list;
    }

    public static final NhomTaiKhoanDTO fromCursor(Cursor cursor) {
        NhomTaiKhoanDTO obj = new NhomTaiKhoanDTO();
        Integer i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_NHOM)) != -1) {
            obj.mMaNhom = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_NHOM)) != -1) {
            obj.mTenNhom = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_MO_TA_NHOM)) != -1) {
            obj.mMoTaNhom = cursor.getString(i);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaNhom() {
        return mMaNhom;
    }

    public void setMaNhom(Integer maNhom) {
        mMaNhom = maNhom;
    }

    public String getTenNhom() {
        return mTenNhom;
    }

    public void setTenNhom(String tenNhom) {
        mTenNhom = tenNhom;
    }

    public String getMoTaNhom() {
        return mMoTaNhom;
    }

    public void setMoTaNhom(String moTaNhom) {
        mMoTaNhom = moTaNhom;
    }

}
