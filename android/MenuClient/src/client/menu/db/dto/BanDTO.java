package client.menu.db.dto;

public class BanDTO
// implements Parcelable
{

    int mId;
    int mMaBan;
    int mMaKhuVuc;
    int mMaBanChinh;
    String mTenBan;
    String mGhiChu;
    boolean mActive;
    boolean mTinhTrang;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getMaBan() {
        return mMaBan;
    }

    public void setMaBan(int maBan) {
        this.mMaBan = maBan;
    }

    public int getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(int maKhuVuc) {
        this.mMaKhuVuc = maKhuVuc;
    }

    public String getTenBan() {
        return mTenBan;
    }

    public void setTenBan(String tenBan) {
        this.mTenBan = tenBan;
    }

    public String getGhiChu() {
        return mGhiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.mGhiChu = ghiChu;
    }

    public boolean isActive() {
        return mActive;
    }

    public void setActive(boolean active) {
        this.mActive = active;
    }

    public boolean getTinhTrang() {
        return mTinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.mTinhTrang = tinhTrang;
    }

    public int getMaBanChinh() {
        return mMaBanChinh;
    }

    public void setMaBanChinh(int maBanChinh) {
        this.mMaBanChinh = maBanChinh;
    }

    @Override
    public String toString() {
        return getTenBan();
    }

    // @Override
    // public int describeContents() {
    // return 0;
    // }
    //
    // @Override
    // public void writeToParcel(Parcel dest, int flags) {
    // dest.writeValue(mActive);
    // dest.writeValue(mGhiChu);
    // dest.writeValue(mId);
    // dest.writeValue(mMaBan);
    // dest.writeValue(mMaBanChinh);
    // dest.writeValue(mMaKhuVuc);
    // dest.writeValue(mTenBan);
    // dest.writeValue(mTinhTrang);
    // }
    //
    // public BanDTO(Parcel src) {
    // mActive = (Boolean) src.readValue(Boolean.class.getClassLoader());
    // mGhiChu = src.readString();
    // mId = src.readInt();
    // mMaBan = src.readInt();
    // mMaBanChinh = (Integer) data[i++];
    // mMaKhuVuc = (Integer) data[i++];
    // mTenBan = (String) data[i++];
    // mTinhTrang = (Boolean) data[i++];
    // }
    //
    // public static final Parcelable.Creator<BanDTO> CREATOR = new
    // Creator<BanDTO>() {
    //
    // @Override
    // public BanDTO createFromParcel(Parcel source) {
    // BanDTO ban = new BanDTO();
    //
    // Object[] data = source.readArray(Object.class.getClassLoader());
    // int i = 0;
    // ban.mActive = (Boolean) data[i++];
    // ban.mGhiChu = (String) data[i++];
    // ban.mId = (Integer) data[i++];
    // ban.mMaBan = (Integer) data[i++];
    // ban.mMaBanChinh = (Integer) data[i++];
    // ban.mMaKhuVuc = (Integer) data[i++];
    // ban.mTenBan = (String) data[i++];
    // ban.mTinhTrang = (Boolean) data[i++];
    //
    // return ban;
    // }
    //
    // @Override
    // public BanDTO[] newArray(int size) {
    // return new BanDTO[size];
    // }
    // };
}
