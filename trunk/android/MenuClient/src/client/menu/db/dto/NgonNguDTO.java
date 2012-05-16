package client.menu.db.dto;

import client.menu.db.contract.NgonNguContract;
import android.database.Cursor;

public class NgonNguDTO {
    private Integer mId;
    private Integer mMaNgonNgu;
    private String mTenNgonNgu;
    private String mKiHieu;

    public static NgonNguDTO extractFrom(Cursor cursor) {
        NgonNguDTO ngonNgu = new NgonNguDTO();

        int index;
        if ((index = cursor.getColumnIndex(NgonNguContract._ID)) != -1) {
            ngonNgu.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(NgonNguContract.COL_MA_NGON_NGU)) != -1) {
            ngonNgu.mMaNgonNgu = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(NgonNguContract.COL_TEN_NGON_NGU)) != -1) {
            ngonNgu.mTenNgonNgu = cursor.getString(index);
        }
        if ((index = cursor.getColumnIndex(NgonNguContract.COL_KI_HIEU)) != -1) {
            ngonNgu.mKiHieu = cursor.getString(index);
        }

        return ngonNgu;
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
