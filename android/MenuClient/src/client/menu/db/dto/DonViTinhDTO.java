package client.menu.db.dto;

import android.provider.BaseColumns;

public class DonViTinhDTO {
    public static final String TABLE_NAME = "DonViTinh";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DON_VI_TINH = "MaDonViTinh";
    
    private int mId;
    private int mMaDonViTinh;

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

}
