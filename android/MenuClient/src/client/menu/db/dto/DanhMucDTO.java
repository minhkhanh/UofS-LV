package client.menu.db.dto;

import android.provider.BaseColumns;

public class DanhMucDTO {
    public static final String TABLE_NAME = "DanhMuc";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DANH_MUC = "MaDanhMuc";
    public static final String CL_MA_DANH_MUC_CHA = "MaDanhMucCha";

    Integer maDanhMuc;
    Integer maDanhMucCha;

    public Integer getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public Integer getMaDanhMucCha() {
        return maDanhMucCha;
    }

    public void setMaDanhMucCha(Integer maDanhMucCha) {
        this.maDanhMucCha = maDanhMucCha;
    }

    @Override
    public String toString() {
        return getMaDanhMuc() + "";
    }
}
