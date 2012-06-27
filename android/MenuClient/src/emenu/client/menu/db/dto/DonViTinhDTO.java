package client.menu.db.dto;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class DonViTinhDTO {
    public static final String TABLE_NAME = "DonViTinh";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DON_VI_TINH = "MaDonViTinh";

    private Integer mId;
    private Integer mMaDonViTinh;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_DON_VI_TINH)) {
            values.put(CL_MA_DON_VI_TINH, jsonObj.getInt(CL_MA_DON_VI_TINH));
        }
        return values;
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
}
