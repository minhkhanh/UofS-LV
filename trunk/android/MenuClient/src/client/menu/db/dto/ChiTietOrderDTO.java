package client.menu.db.dto;

import java.util.Map;

import client.menu.db.contract.ChiTietOrderContract;

public class ChiTietOrderDTO {
    private Integer mId;
    private Integer mMaChiTiet;
    private Integer mMaOrder;
    private Integer mSoLuong;
    private String mGhiChu;
    private Integer mMaBoPhanCheBien;
    private Integer mTinhTrang = 0;
    private Integer mMaMonAn;
    private Integer mMaDonViTinh;

    public void extractTo(Map<String, Object> map) {
        map.put(ChiTietOrderContract.CL_ID, mId);
        map.put(ChiTietOrderContract.CL_SID, mMaChiTiet);
        map.put(ChiTietOrderContract.CL_MA_ORDER, mMaOrder);
        map.put(ChiTietOrderContract.CL_SO_LUONG, mSoLuong);
        map.put(ChiTietOrderContract.CL_GHI_CHU, mGhiChu);
        map.put(ChiTietOrderContract.CL_MA_BO_PHAN_CHE_BIEN, mMaBoPhanCheBien);
        map.put(ChiTietOrderContract.CL_TINH_TRANG, mTinhTrang);
        map.put(ChiTietOrderContract.CL_MA_MON_AN, mMaMonAn);
        map.put(ChiTietOrderContract.CL_MA_DON_VI_TINH, mMaDonViTinh);
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
