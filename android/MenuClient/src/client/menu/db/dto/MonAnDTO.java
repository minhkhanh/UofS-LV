package client.menu.db.dto;

import client.menu.db.contract.MonAnContract;
import android.database.Cursor;

public class MonAnDTO {
    private Integer mId;
    private Integer mMaMonAn;
    private String mHinhAnh;
    private Float mDiemDanhGia;
    private Integer mSoLuotDanhGia;
    private Integer mMaDonViTinhMacDinh;
    private Integer mMaDanhMuc;
    private Boolean mNgungBan;

    public static MonAnDTO extractFrom(Cursor cursor) {
        MonAnDTO monAn = new MonAnDTO();

        int index;

        if ((index = cursor.getColumnIndex(MonAnContract.CL_ID)) != -1) {
            monAn.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(MonAnContract.CL_MA_MON_AN)) != -1) {
            monAn.mMaMonAn = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(MonAnContract.CL_HINH_ANH)) != -1) {
            monAn.mHinhAnh = cursor.getString(index);
        }
        if ((index = cursor.getColumnIndex(MonAnContract.CL_DIEM_DANH_GIA)) != -1) {
            monAn.mDiemDanhGia = cursor.getFloat(index);
        }
        if ((index = cursor.getColumnIndex(MonAnContract.CL_SO_LUOT_RATE)) != -1) {
            monAn.mSoLuotDanhGia = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(MonAnContract.CL_DEFAULT_UNIT_ID)) != -1) {
            monAn.mMaDonViTinhMacDinh = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(MonAnContract.CL_MA_DANH_MUC)) != -1) {
            monAn.mMaDanhMuc = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(MonAnContract.CL_NGUNG_BAN)) != -1) {
            monAn.mNgungBan = Boolean.valueOf(cursor.getString(index));
        }

        return monAn;
    }

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

    public void setMaMonAn(Integer maMonAn) {
        this.mMaMonAn = maMonAn;
    }

    public String getHinhAnh() {
        return mHinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.mHinhAnh = hinhAnh;
    }

    public Float getDiemDanhGia() {
        return mDiemDanhGia;
    }

    public void setDiemDanhGia(Float diemDanhGia) {
        this.mDiemDanhGia = diemDanhGia;
    }

    public Integer getSoLuotDanhGia() {
        return mSoLuotDanhGia;
    }

    public void setSoLuotDanhGia(Integer soLuotDanhGia) {
        this.mSoLuotDanhGia = soLuotDanhGia;
    }

    public Integer getMaDonViTinhMacDinh() {
        return mMaDonViTinhMacDinh;
    }

    public void setMaDonViTinhMacDinh(Integer maDonViTinhMacDinh) {
        this.mMaDonViTinhMacDinh = maDonViTinhMacDinh;
    }

    public Integer getMaDanhMuc() {
        return mMaDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.mMaDanhMuc = maDanhMuc;
    }

    public Boolean isNgungBan() {
        return mNgungBan;
    }

    public void setNgungBan(Boolean ngungBan) {
        this.mNgungBan = ngungBan;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Boolean getNgungBan() {
        return mNgungBan;
    }

}
