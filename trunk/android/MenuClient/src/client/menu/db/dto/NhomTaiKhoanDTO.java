package client.menu.db.dto;

public class NhomTaiKhoanDTO {
    private int mId;
    private int mMaNhom;
    private String mTenNhom;
    private String mMoTaNhom;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getMaNhom() {
        return mMaNhom;
    }

    public void setMaNhom(int maNhom) {
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
