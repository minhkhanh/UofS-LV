package client.menu.db.dto;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class ChiTietOrderDTO {
    public static final String TABLE_NAME = "ChiTietOrder";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_SID = "MaChiTietOrder";
    public static final String CL_MA_ORDER = "MaOrder";
    public static final String CL_SO_LUONG = "SoLuong";
    public static final String CL_GHI_CHU = "GhiChu";
    public static final String CL_MA_BO_PHAN_CHE_BIEN = "MaBoPhanCheBien";
    public static final String CL_TINH_TRANG = "TinhTrang";
    public static final String CL_MA_MON_AN = "MaMonAn";
    public static final String CL_MA_DON_VI_TINH = "MaDonViTinh";

    private Integer mId;
    private Integer mMaChiTiet;
    private Integer mMaOrder;
    private Integer mSoLuong;
    private String mGhiChu;
    private Integer mMaBoPhanCheBien;
    private Integer mTinhTrang = 0;
    private Integer mMaMonAn;
    private Integer mMaDonViTinh;

    public void to(ContentValues values) {
        values.put(CL_ID, mId);
        values.put(CL_SID, mMaChiTiet);
        values.put(CL_MA_ORDER, mMaOrder);
        values.put(CL_SO_LUONG, mSoLuong);
        values.put(CL_GHI_CHU, mGhiChu);
        values.put(CL_MA_BO_PHAN_CHE_BIEN, mMaBoPhanCheBien);
        values.put(CL_TINH_TRANG, mTinhTrang);
        values.put(CL_MA_MON_AN, mMaMonAn);
        values.put(CL_MA_DON_VI_TINH, mMaDonViTinh);
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaChiTiet() {
        return mMaChiTiet;
    }

    public void setMaChiTiet(Integer maChiTiet) {
        mMaChiTiet = maChiTiet;
    }

    public Integer getMaOrder() {
        return mMaOrder;
    }

    public void setMaOrder(Integer maOrder) {
        mMaOrder = maOrder;
    }

    public Integer getSoLuong() {
        return mSoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        mSoLuong = soLuong;
    }

    public String getGhiChu() {
        return mGhiChu;
    }

    public void setGhiChu(String ghiChu) {
        mGhiChu = ghiChu;
    }

    public Integer getMaBoPhanCheBien() {
        return mMaBoPhanCheBien;
    }

    public void setMaBoPhanCheBien(Integer maBoPhanCheBien) {
        mMaBoPhanCheBien = maBoPhanCheBien;
    }

    public Integer getTinhTrang() {
        return mTinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        mTinhTrang = tinhTrang;
    }

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

    public void setMaMonAn(Integer maMonAn) {
        mMaMonAn = maMonAn;
    }

    public Integer getMaDonViTinh() {
        return mMaDonViTinh;
    }

    public void setMaDonViTinh(Integer maDonViTinh) {
        mMaDonViTinh = maDonViTinh;
    }
}
