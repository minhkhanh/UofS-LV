package client.menu.db.dto;

public class ChiTietOrderDTO {
    private int mId;
    private int mMaChiTiet;
    private int mMaOrder;
    private int mSoLuong;
    private int mMaMonAn;
    private String mGhiChu;
    private int mMaBoPhanCheBien;
    public int getId() {
        return mId;
    }
    public void setId(int id) {
        mId = id;
    }
    public int getMaChiTiet() {
        return mMaChiTiet;
    }
    public void setMaChiTiet(int maChiTiet) {
        mMaChiTiet = maChiTiet;
    }
    public int getMaOrder() {
        return mMaOrder;
    }
    public void setMaOrder(int maOrder) {
        mMaOrder = maOrder;
    }
    public int getSoLuong() {
        return mSoLuong;
    }
    public void setSoLuong(int soLuong) {
        mSoLuong = soLuong;
    }
    public int getMaMonAn() {
        return mMaMonAn;
    }
    public void setMaMonAn(int maMonAn) {
        mMaMonAn = maMonAn;
    }
    public String getGhiChu() {
        return mGhiChu;
    }
    public void setGhiChu(String ghiChu) {
        mGhiChu = ghiChu;
    }
    public int getMaBoPhanCheBien() {
        return mMaBoPhanCheBien;
    }
    public void setMaBoPhanCheBien(int maBoPhanCheBien) {
        mMaBoPhanCheBien = maBoPhanCheBien;
    }
    
    
}
