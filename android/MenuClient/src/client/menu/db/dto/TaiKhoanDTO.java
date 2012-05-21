package client.menu.db.dto;

import java.sql.Date;

import android.provider.BaseColumns;

public class TaiKhoanDTO {
    public static final String TABLE_NAME = "NgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_TEN_NGON_NGU = "TenNgonNgu";
    public static final String CL_KI_HIEU = "KiHieu";
    
    private int mId;
    private int mMaTaiKhoan;
    private String mTenTaiKhoan;
    private String mMatKhau;
    private String mHoTen;
    private Date mNgaySinh;
    private int mGioiTinh;
    private String mCMND;
    private String mAvatar;
    private boolean mActive;
    private int mMaNhomTaiKhoan;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getMaTaiKhoan() {
        return mMaTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
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

    public int getGioiTinh() {
        return mGioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
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

    public boolean isActive() {
        return mActive;
    }

    public void setActive(boolean active) {
        mActive = active;
    }

    public int getMaNhomTaiKhoan() {
        return mMaNhomTaiKhoan;
    }

    public void setMaNhomTaiKhoan(int maNhomTaiKhoan) {
        mMaNhomTaiKhoan = maNhomTaiKhoan;
    }

}
