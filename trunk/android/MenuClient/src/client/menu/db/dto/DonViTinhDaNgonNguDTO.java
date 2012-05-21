package client.menu.db.dto;

import android.database.Cursor;
import android.provider.BaseColumns;

public class DonViTinhDaNgonNguDTO {
    public static final String TABLE_NAME = "ChiTietDonViTinhDaNgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_MA_NGON_NGU_QN = TABLE_NAME + ".MaNgonNgu";
    public static final String CL_MA_DON_VI = "MaDonViTinh";
    public static final String CL_MA_DON_VI_QN = TABLE_NAME + ".MaDonViTinh";
    public static final String CL_TEN_DON_VI = "TenDonViTinh";

    private Integer mId;
    private Integer mMaDonViTinh;
    private Integer mMaNgonNgu;
    private String mTenDonViTinh;

    public static DonViTinhDaNgonNguDTO extractFrom(Cursor cursor) {
        DonViTinhDaNgonNguDTO obj = new DonViTinhDaNgonNguDTO();

        int index;
        if ((index = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_NGON_NGU)) != -1) {
            obj.mMaNgonNgu = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_DON_VI)) != -1) {
            obj.mMaDonViTinh = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_TEN_DON_VI)) != -1) {
            obj.mTenDonViTinh = cursor.getString(index);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaDonViTinh() {
        return mMaDonViTinh;
    }

    public void setMaDonViTinh(Integer maDonViTinh) {
        mMaDonViTinh = maDonViTinh;
    }

    public Integer getMaNgonNgu() {
        return mMaNgonNgu;
    }

    public void setMaNgonNgu(Integer maNgonNgu) {
        mMaNgonNgu = maNgonNgu;
    }

    public String getTenDonViTinh() {
        return mTenDonViTinh;
    }

    public void setTenDonViTinh(String tenDonViTinh) {
        mTenDonViTinh = tenDonViTinh;
    }

}
