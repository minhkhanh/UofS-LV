package client.menu.db.dto;

public class ThamSoDTO {
    private int mId;
    private int mMaThamSo;
    private String mTenThamSo;
    private String mKieuDuLieu;
    private String mGiaTri;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getMaThamSo() {
        return mMaThamSo;
    }

    public void setMaThamSo(int maThamSo) {
        mMaThamSo = maThamSo;
    }

    public String getTenThamSo() {
        return mTenThamSo;
    }

    public void setTenThamSo(String tenThamSo) {
        mTenThamSo = tenThamSo;
    }

    public String getKieuDuLieu() {
        return mKieuDuLieu;
    }

    public void setKieuDuLieu(String kieuDuLieu) {
        mKieuDuLieu = kieuDuLieu;
    }

    public String getGiaTri() {
        return mGiaTri;
    }

    public void setGiaTri(String giaTri) {
        mGiaTri = giaTri;
    }

}
