package client.menu.db.dto;

import client.menu.db.contract.DonViTinhMonAnContract;
import android.database.Cursor;

public class DonViTinhMonAnDTO {
    private Integer mId;
    private Integer mMaMonAn;
    private Integer mMaDonViTinh;
    private Float mDonGia;

    public static DonViTinhMonAnDTO extractFrom(Cursor cursor) {
        DonViTinhMonAnDTO obj = new DonViTinhMonAnDTO();

        int colIndex;
        if ((colIndex = cursor.getColumnIndex(DonViTinhMonAnContract._ID)) != -1) {
            obj.mId = cursor.getInt(colIndex);
        }
        if ((colIndex = cursor.getColumnIndex(DonViTinhMonAnContract.COL_MA_MON_AN)) != -1) {
            obj.mMaMonAn = cursor.getInt(colIndex);
        }
        if ((colIndex = cursor.getColumnIndex(DonViTinhMonAnContract.COL_MA_DON_VI)) != -1) {
            obj.mMaDonViTinh = cursor.getInt(colIndex);
        }
        if ((colIndex = cursor.getColumnIndex(DonViTinhMonAnContract.COL_DON_GIA)) != -1) {
            obj.mDonGia = cursor.getFloat(colIndex);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

    public void setMaMonAn(Integer maMonAn) {
        mMaMonAn = maMonAn;
    }

    public Integer getMaDonViTinh() {
        return mMaDonViTinh;
    }

    public void setMaDonViTinh(Integer maDonViTinh) {
        mMaDonViTinh = maDonViTinh;
    }

    public Float getDonGia() {
        return mDonGia;
    }

    public void setDonGia(Float donGia) {
        mDonGia = donGia;
    }

}
