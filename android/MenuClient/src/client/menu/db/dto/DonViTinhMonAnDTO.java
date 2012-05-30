package client.menu.db.dto;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class DonViTinhMonAnDTO {

    public static final String TABLE_NAME = "ChiTietMonAnDonViTinh";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_MON_AN = "MaMonAn";
    public static final String CL_MA_MON_AN_QN = TABLE_NAME + ".MaMonAn";
    public static final String CL_MA_DON_VI = "MaDonViTinh";
    public static final String CL_MA_DON_VI_QN = TABLE_NAME + ".MaDonViTinh";
    public static final String CL_DON_GIA = "DonGia";

    private Integer mId;
    private Integer mMaMonAn;
    private Integer mMaDonViTinh;
    private Integer mDonGia;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_DON_VI)) {
            values.put(CL_MA_DON_VI, jsonObj.getInt(CL_MA_DON_VI));
        }
        if (!jsonObj.isNull(CL_MA_MON_AN)) {
            values.put(CL_MA_MON_AN, jsonObj.getInt(CL_MA_MON_AN));
        }
        if (!jsonObj.isNull(CL_DON_GIA)) {
            values.put(CL_DON_GIA, jsonObj.getInt(CL_DON_GIA));
        }

        return values;
    }

    public static DonViTinhMonAnDTO extractFrom(Cursor cursor) {
        DonViTinhMonAnDTO obj = new DonViTinhMonAnDTO();

        int index;
        if ((index = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_MON_AN)) != -1) {
            obj.mMaMonAn = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_DON_VI)) != -1) {
            obj.mMaDonViTinh = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_DON_GIA)) != -1) {
            obj.mDonGia = cursor.getInt(index);
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

    public Integer getDonGia() {
        return mDonGia;
    }

    public void setDonGia(Integer donGia) {
        mDonGia = donGia;
    }
}
