package client.menu.db.dto;

import android.database.Cursor;
import android.provider.BaseColumns;

public class NgonNguDTO {

    public static final String TABLE_NAME = "NgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_TEN_NGON_NGU = "TenNgonNgu";
    public static final String CL_KI_HIEU = "KiHieu";

    private Integer mId;
    private Integer mMaNgonNgu;
    private String mTenNgonNgu;
    private String mKiHieu;

    public static NgonNguDTO extractFrom(Cursor cursor) {
        NgonNguDTO obj = new NgonNguDTO();

        int index;
        if ((index = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_NGON_NGU)) != -1) {
            obj.mMaNgonNgu = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_TEN_NGON_NGU)) != -1) {
            obj.mTenNgonNgu = cursor.getString(index);
        }
        if ((index = cursor.getColumnIndex(CL_KI_HIEU)) != -1) {
            obj.mKiHieu = cursor.getString(index);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaNgonNgu() {
        return mMaNgonNgu;
    }

    public void setMaNgonNgu(Integer maNgonNgu) {
        mMaNgonNgu = maNgonNgu;
    }

    public String getTenNgonNgu() {
        return mTenNgonNgu;
    }

    public void setTenNgonNgu(String tenNgonNgu) {
        mTenNgonNgu = tenNgonNgu;
    }

    public String getKiHieu() {
        return mKiHieu;
    }

    public void setKiHieu(String kiHieu) {
        mKiHieu = kiHieu;
    }

}
