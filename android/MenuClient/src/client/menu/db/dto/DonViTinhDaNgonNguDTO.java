package client.menu.db.dto;

import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.MonAnContract;
import android.database.Cursor;

public class DonViTinhDaNgonNguDTO {
    private Integer mId;
    private Integer mMaDonViTinh;
    private Integer mMaNgonNgu;
    private String mTenDonViTinh;

    public static DonViTinhDaNgonNguDTO extractFrom(Cursor cursor) {
        DonViTinhDaNgonNguDTO obj = new DonViTinhDaNgonNguDTO();

        int index;
        if ((index = cursor.getColumnIndex(DonViTinhDaNgonNguContract._ID)) != -1) {
            obj.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(DonViTinhDaNgonNguContract.COL_MA_NGON_NGU)) != -1) {
            obj.mMaNgonNgu = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(DonViTinhDaNgonNguContract.COL_MA_DON_VI)) != -1) {
            obj.mMaDonViTinh = cursor.getInt(index);
        }
        if ((index = cursor
                .getColumnIndex(DonViTinhDaNgonNguContract.COL_TEN_DON_VI)) != -1) {
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
