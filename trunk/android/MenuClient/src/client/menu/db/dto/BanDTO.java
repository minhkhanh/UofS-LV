package client.menu.db.dto;

import client.menu.db.dao.BanDAO;
import client.menu.db.dao.KhuVucDAO;
import android.database.Cursor;
import android.provider.BaseColumns;

public class BanDTO
// implements Parcelable
{
    public static final String TABLE_NAME = "Ban";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_BAN = "MaBan";
    public static final String CL_MA_KHU_VUC = "MaKhuVuc";
    public static final String CL_TEN_BAN = "TenBan";
    public static final String CL_GHI_CHU = "GhiChu";
    public static final String CL_ACTIVE = "Active";
    public static final String CL_TINH_TRANG = "TinhTrang";
    public static final String CL_MA_BAN_CHINH = "MaBanChinh";

    private Integer mId;
    Integer mMaBan;
    Integer mMaKhuVuc;
    Integer mMaBanChinh;
    String mTenBan;
    String mGhiChu;
    Integer mActive;
    Integer mTinhTrang;
    
    public static final BanDTO extractFrom(Cursor cursor) {
        BanDTO obj = new BanDTO();
        int i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_BAN)) != -1) {
            obj.mMaBan = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_KHU_VUC)) != -1) {
            obj.mMaKhuVuc = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_BAN_CHINH)) != -1) {
            obj.mMaBanChinh = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_BAN)) != -1) {
            obj.mTenBan = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_GHI_CHU)) != -1) {
            obj.mGhiChu = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_ACTIVE)) != -1) {
            obj.mActive = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TINH_TRANG)) != -1) {
            obj.mTinhTrang = cursor.getInt(i);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaBan() {
        return mMaBan;
    }

    public void setMaBan(Integer maBan) {
        this.mMaBan = maBan;
    }

    public Integer getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(Integer maKhuVuc) {
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

    public Integer isActive() {
        return mActive;
    }

    public void setActive(Integer active) {
        this.mActive = active;
    }

    public Integer getTinhTrang() {
        return mTinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.mTinhTrang = tinhTrang;
    }

    public Integer getMaBanChinh() {
        return mMaBanChinh;
    }

    public void setMaBanChinh(Integer maBanChinh) {
        this.mMaBanChinh = maBanChinh;
    }

    @Override
    public String toString() {
        return getTenBan();
    }

    // @Override
    // public Integer describeContents() {
    // return 0;
    // }
    //
    // @Override
    // public void writeToParcel(Parcel dest, Integer flags) {
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
    // Integer i = 0;
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
    // public BanDTO[] newArray(Integer size) {
    // return new BanDTO[size];
    // }
    // };
}
