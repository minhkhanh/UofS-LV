package emenu.client.db.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import emenu.client.menu.util.U;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class TaiKhoanDTO {
    public static final String TABLE_NAME = "TaiKhoan";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_TAI_KHOAN = "MaTaiKhoan";
    public static final String CL_TEN_TAI_KHOAN = "TenTaiKhoan";
    public static final String CL_MAT_KHAU = "MatKhau";
    public static final String CL_HO_TEN = "HoTen";
    public static final String CL_NGAY_SINH = "NgaySinh";
    public static final String CL_GIOI_TINH = "GioiTinh";
    public static final String CL_CMND = "CMND";
    public static final String CL_AVATAR = "Avatar";
    public static final String CL_ACTIVE = "Active";
    public static final String CL_MA_NHOM_TAI_KHOAN = "MaNhomTaiKhoan";

    private Integer mId;
    private Integer mMaTaiKhoan;
    private String mTenTaiKhoan;
    private String mMatKhau;
    private String mHoTen;
    private Date mNgaySinh;
    private Integer mGioiTinh;
    private String mCMND;
    private String mAvatar;
    private Boolean mActive;
    private Integer mMaNhomTaiKhoan;
    
    public static List<TaiKhoanDTO> fromArrayCursor(Cursor cursor) {
        List<TaiKhoanDTO> list = new ArrayList<TaiKhoanDTO>();
        
        while (cursor.moveToNext()) {
            TaiKhoanDTO obj = TaiKhoanDTO.fromCursor(cursor);
            list.add(obj);
        }
        
        return list;
    }

    public static final TaiKhoanDTO fromCursor(Cursor cursor) {
        TaiKhoanDTO obj = new TaiKhoanDTO();
        Integer i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_TAI_KHOAN)) != -1) {
            obj.mMaTaiKhoan = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_ACTIVE)) != -1) {
            obj.mActive = U.getCursorBool(cursor, i);
        }
        if ((i = cursor.getColumnIndex(CL_AVATAR)) != -1) {
            obj.mAvatar = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_CMND)) != -1) {
            obj.mCMND = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_GIOI_TINH)) != -1) {
            obj.mGioiTinh = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_HO_TEN)) != -1) {
            obj.mHoTen = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_NHOM_TAI_KHOAN)) != -1) {
            obj.mMaNhomTaiKhoan = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_NGAY_SINH)) != -1) {
//            obj.mNgaySinh = cursor.get(i);
        }
        if ((i = cursor.getColumnIndex(CL_MAT_KHAU)) != -1) {
            obj.mMatKhau = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_TAI_KHOAN)) != -1) {
            obj.mTenTaiKhoan = cursor.getString(i);
        }

        return obj;
    }

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_TAI_KHOAN)) {
            values.put(CL_MA_TAI_KHOAN, jsonObj.getInt(CL_MA_TAI_KHOAN));
        }
        if (!jsonObj.isNull(CL_TEN_TAI_KHOAN)) {
            values.put(CL_TEN_TAI_KHOAN, jsonObj.getString(CL_TEN_TAI_KHOAN));
        }
        if (!jsonObj.isNull(CL_MAT_KHAU)) {
            values.put(CL_MAT_KHAU, jsonObj.getString(CL_MAT_KHAU));
        }
        if (!jsonObj.isNull(CL_HO_TEN)) {
            values.put(CL_HO_TEN, jsonObj.getString(CL_HO_TEN));
        }
        if (!jsonObj.isNull(CL_NGAY_SINH)) {
//            values.put(CL_NGAY_SINH, jsonObj.getString(CL_NGAY_SINH));
        }
        if (!jsonObj.isNull(CL_GIOI_TINH)) {
            values.put(CL_GIOI_TINH, jsonObj.getInt(CL_GIOI_TINH));
        }
        if (!jsonObj.isNull(CL_CMND)) {
            values.put(CL_CMND, jsonObj.getString(CL_CMND));
        }
        if (!jsonObj.isNull(CL_AVATAR)) {
            values.put(CL_AVATAR, jsonObj.getString(CL_AVATAR));
        }
        if (!jsonObj.isNull(CL_ACTIVE)) {
            values.put(CL_ACTIVE, jsonObj.getBoolean(CL_ACTIVE));
        }
        if (!jsonObj.isNull(CL_MA_NHOM_TAI_KHOAN)) {
            values.put(CL_MA_NHOM_TAI_KHOAN, jsonObj.getInt(CL_MA_NHOM_TAI_KHOAN));
        }

        return values;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaTaiKhoan() {
        return mMaTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        mMaTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return mTenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        mTenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return mMatKhau;
    }

    public void setMatKhau(String matKhau) {
        mMatKhau = matKhau;
    }

    public String getHoTen() {
        return mHoTen;
    }

    public void setHoTen(String hoTen) {
        mHoTen = hoTen;
    }

    public Date getNgaySinh() {
        return mNgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        mNgaySinh = ngaySinh;
    }

    public Integer getGioiTinh() {
        return mGioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        mGioiTinh = gioiTinh;
    }

    public String getCMND() {
        return mCMND;
    }

    public void setCMND(String cMND) {
        mCMND = cMND;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public Boolean isActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }

    public Integer getMaNhomTaiKhoan() {
        return mMaNhomTaiKhoan;
    }

    public void setMaNhomTaiKhoan(Integer maNhomTaiKhoan) {
        mMaNhomTaiKhoan = maNhomTaiKhoan;
    }

}
