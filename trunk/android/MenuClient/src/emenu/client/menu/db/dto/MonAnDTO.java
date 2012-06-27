package client.menu.db.dto;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class MonAnDTO {

    public static final String TABLE_NAME = "MonAn";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_MON_AN = "MaMonAn";
    public static final String CL_HINH_ANH = "HinhAnh";
    public static final String CL_DIEM_DANH_GIA = "DiemDanhGia";
    public static final String CL_SO_LUOT_RATE = "SoLuotDanhGia";
    public static final String CL_DEFAULT_UNIT_ID = "MaDonViTinhMacDinh";
    public static final String CL_MA_DANH_MUC = "MaDanhMuc";
    public static final String CL_NGUNG_BAN = "NgungBan";

    private Integer mId;
    private Integer mMaMonAn;
    private String mHinhAnh;
    private Float mDiemDanhGia;
    private Integer mSoLuotDanhGia;
    private Integer mMaDonViTinhMacDinh;
    private Integer mMaDanhMuc;
    private Boolean mNgungBan;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_MON_AN)) {
            values.put(CL_MA_MON_AN, jsonObj.getInt(CL_MA_MON_AN));
        }
        if (!jsonObj.isNull(CL_HINH_ANH)) {
            values.put(CL_HINH_ANH, jsonObj.getString(CL_HINH_ANH));
        }
        if (!jsonObj.isNull(CL_DIEM_DANH_GIA)) {
            values.put(CL_DIEM_DANH_GIA, jsonObj.getDouble(CL_DIEM_DANH_GIA));
        }
        if (!jsonObj.isNull(CL_SO_LUOT_RATE)) {
            values.put(CL_SO_LUOT_RATE, jsonObj.getInt(CL_SO_LUOT_RATE));
        }
        if (!jsonObj.isNull(CL_DEFAULT_UNIT_ID)) {
            values.put(CL_DEFAULT_UNIT_ID, jsonObj.getInt(CL_DEFAULT_UNIT_ID));
        }
        if (!jsonObj.isNull(CL_MA_DANH_MUC)) {
            values.put(CL_MA_DANH_MUC, jsonObj.getInt(CL_MA_DANH_MUC));
        }
        if (!jsonObj.isNull(CL_NGUNG_BAN)) {
            values.put(CL_NGUNG_BAN, jsonObj.getBoolean(CL_NGUNG_BAN));
        }
        return values;
    }

    public void to(ContentValues values) {
        values.put(CL_ID, mId);
        values.put(CL_MA_MON_AN, mMaMonAn);
        values.put(CL_HINH_ANH, mHinhAnh);
        values.put(CL_DIEM_DANH_GIA, mDiemDanhGia);
        values.put(CL_SO_LUOT_RATE, mSoLuotDanhGia);
        values.put(CL_DEFAULT_UNIT_ID, mMaDonViTinhMacDinh);
        values.put(CL_MA_DANH_MUC, mMaDanhMuc);
        values.put(CL_NGUNG_BAN, mNgungBan);
    }

    public static MonAnDTO from(Cursor cursor) {
        MonAnDTO monAn = new MonAnDTO();

        int index;

        if ((index = cursor.getColumnIndex(CL_ID)) != -1) {
            monAn.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_MON_AN)) != -1) {
            monAn.mMaMonAn = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_HINH_ANH)) != -1) {
            monAn.mHinhAnh = cursor.getString(index);
        }
        if ((index = cursor.getColumnIndex(CL_DIEM_DANH_GIA)) != -1) {
            monAn.mDiemDanhGia = cursor.getFloat(index);
        }
        if ((index = cursor.getColumnIndex(CL_SO_LUOT_RATE)) != -1) {
            monAn.mSoLuotDanhGia = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_DEFAULT_UNIT_ID)) != -1) {
            monAn.mMaDonViTinhMacDinh = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_DANH_MUC)) != -1) {
            monAn.mMaDanhMuc = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_NGUNG_BAN)) != -1) {
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
