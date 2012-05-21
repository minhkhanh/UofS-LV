package client.menu.db.dto;

import android.database.Cursor;
import android.provider.BaseColumns;

public class DanhMucDaNgonNguDTO {
    public static final String TABLE_NAME = "ChiTietDanhMucDaNgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DANH_MUC = "MaDanhMuc";
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_TEN_DANH_MUC = "TenDanhMuc";
    public static final String CL_MO_TA_DANH_MUC = "MoTaDanhMuc";

    private Integer mId;
    private Integer mMaDanhMuc;
    private Integer mMaNgonNgu;
    private String mTenDanhMuc;
    private String mMoTaDanhMuc;

    public static final DanhMucDaNgonNguDTO extractFrom(Cursor cursor) {
        DanhMucDaNgonNguDTO obj = new DanhMucDaNgonNguDTO();
        int i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_DANH_MUC)) != -1) {
            obj.mMaDanhMuc = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_NGON_NGU)) != -1) {
            obj.mMaNgonNgu = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_DANH_MUC)) != -1) {
            obj.mTenDanhMuc = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_MO_TA_DANH_MUC)) != -1) {
            obj.mMoTaDanhMuc = cursor.getString(i);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaDanhMuc() {
        return mMaDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.mMaDanhMuc = maDanhMuc;
    }

    public Integer getMaNgonNgu() {
        return mMaNgonNgu;
    }

    public void setMaNgonNgu(Integer maNgonNgu) {
        this.mMaNgonNgu = maNgonNgu;
    }

    public String getTenDanhMuc() {
        return mTenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.mTenDanhMuc = tenDanhMuc;
    }

    public String getMoTaDanhMuc() {
        return mMoTaDanhMuc;
    }

    public void setMoTaDanhMuc(String moTaDanhMuc) {
        this.mMoTaDanhMuc = moTaDanhMuc;
    }

    @Override
    public String toString() {
        return getTenDanhMuc();
    }
}
