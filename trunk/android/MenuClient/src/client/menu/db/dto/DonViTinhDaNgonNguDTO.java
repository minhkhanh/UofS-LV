package client.menu.db.dto;

public class DonViTinhDaNgonNguDTO {
    private int mId;
    private int mMaDonViTinh;
    private int mMaNgonNgu;
    private String mTenDonViTinh;
    public int getId() {
        return mId;
    }
    public void setId(int id) {
        mId = id;
    }
    public int getMaDonViTinh() {
        return mMaDonViTinh;
    }
    public void setMaDonViTinh(int maDonViTinh) {
        mMaDonViTinh = maDonViTinh;
    }
    public int getMaNgonNgu() {
        return mMaNgonNgu;
    }
    public void setMaNgonNgu(int maNgonNgu) {
        mMaNgonNgu = maNgonNgu;
    }
    public String getTenDonViTinh() {
        return mTenDonViTinh;
    }
    public void setTenDonViTinh(String tenDonViTinh) {
        mTenDonViTinh = tenDonViTinh;
    }
    
    
}
