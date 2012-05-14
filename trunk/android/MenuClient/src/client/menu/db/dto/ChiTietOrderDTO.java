package client.menu.db.dto;

import java.util.Map;

import client.menu.db.contract.ChiTietOrderContract;

public class ChiTietOrderDTO {
    private Integer mId;
    private Integer mMaChiTiet;
    private Integer mMaOrder;
    private Integer mSoLuong;
    private Integer mMaMonAn;
    private String mGhiChu;
    private Integer mMaBoPhanCheBien;
    private Integer mMaDonViTinh;
    private Integer mTinhTrang = 0;
    
    public ChiTietOrderDTO() {    
    }
    
    public void extract(Map<String, Object> map) {
        map.put(ChiTietOrderContract._ID, mId);
        map.put(ChiTietOrderContract.COL_SID, mMaChiTiet);
        map.put(ChiTietOrderContract.COL_MA_ORDER, mMaOrder);
        map.put(ChiTietOrderContract.COL_SO_LUONG, mSoLuong);
        map.put(ChiTietOrderContract.COL_MA_MON, mMaMonAn);
        map.put(ChiTietOrderContract.COL_GHI_CHU, mGhiChu);
        map.put(ChiTietOrderContract.COL_MA_BO_PHAN_CHE_BIEN, mMaBoPhanCheBien);
        map.put(ChiTietOrderContract.COL_MA_DON_VI_TINH, mMaDonViTinh);
        map.put(ChiTietOrderContract.COL_TINH_TRANG, mTinhTrang);
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

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

    public void setMaMonAn(Integer maMonAn) {
        mMaMonAn = maMonAn;
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

    public Integer getMaDonViTinh() {
        return mMaDonViTinh;
    }

    public void setMaDonViTinh(Integer maDonViTinh) {
        mMaDonViTinh = maDonViTinh;
    }

    public Integer getTinhTrang() {
        return mTinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        mTinhTrang = tinhTrang;
    }

}
