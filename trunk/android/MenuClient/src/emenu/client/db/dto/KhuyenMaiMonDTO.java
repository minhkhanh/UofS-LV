package emenu.client.db.dto;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class KhuyenMaiMonDTO {
    public static final String TABLE_NAME = "KhuyenMaiMon";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_KM = "MaKhuyenMai";
    public static final String CL_MA_MON = "MaMonAn";

    public static final String CL_MA_KM_QN = TABLE_NAME + "." + "MaKhuyenMai";
    public static final String CL_MA_MON_QN = TABLE_NAME + "." + "MaMonAn";

    private Integer mId;
    private Integer mMaKhuyenMai;
    private Integer mMaMonAn;

    public static final ContentValues toContentValues(JSONObject jsonObject)
            throws JSONException {
        return KhuyenMaiMonDTO.fromJson(jsonObject).toContentValues();
    }

    public ContentValues toContentValues() {
        ContentValues c = new ContentValues();
        if (mId != null)
            c.put(CL_ID, mId);
        if (mMaKhuyenMai != null)
            c.put(CL_MA_KM, mMaKhuyenMai);
        if (mMaMonAn != null)
            c.put(CL_MA_MON, mMaMonAn);

        return c;
    }

    public static final KhuyenMaiMonDTO fromJson(JSONObject jsonObject)
            throws JSONException {
        KhuyenMaiMonDTO obj = new KhuyenMaiMonDTO();
        if (!jsonObject.isNull(CL_MA_KM))
            obj.mMaKhuyenMai = jsonObject.getInt(CL_MA_KM);
        if (!jsonObject.isNull(CL_MA_MON))
            obj.mMaMonAn = jsonObject.getInt(CL_MA_MON);

        return obj;
    }

    public Integer getMaKhuyenMai() {
        return mMaKhuyenMai;
    }

    public void setMaKhuyenMai(Integer maKhuyenMai) {
        mMaKhuyenMai = maKhuyenMai;
    }

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

    public void setMaMonAn(Integer maMonAn) {
        mMaMonAn = maMonAn;
    }

}
