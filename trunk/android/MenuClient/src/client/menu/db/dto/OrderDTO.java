package client.menu.db.dto;

public class OrderDTO {
    private int mId;
    private int mMaOrder;
    private int mMaTaiKhoan;
    private int mMaBan;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getMaOrder() {
        return mMaOrder;
    }

    public void setMaOrder(int maOrder) {
        mMaOrder = maOrder;
    }

    public int getMaTaiKhoan() {
        return mMaTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        mMaTaiKhoan = maTaiKhoan;
    }

    public int getMaBan() {
        return mMaBan;
    }

    public void setMaBan(int maBan) {
        mMaBan = maBan;
    }

}
