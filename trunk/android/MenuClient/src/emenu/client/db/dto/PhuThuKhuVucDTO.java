package emenu.client.db.dto;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class PhuThuKhuVucDTO {

    public static final String TABLE_NAME = "PhuThuKhuVuc";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_PHU_THU = "MaPhuThu";
    public static final String CL_MA_KHU_VUC = "MaKhuVuc";

    public static final String CL_MA_KHU_VUC_QN = TABLE_NAME + "." + CL_MA_KHU_VUC;
    public static final String CL_MA_PHU_THU_QN = TABLE_NAME + "." + CL_MA_PHU_THU;

    private Integer mId;
    private Integer mMaPhuThu;
    private Integer mMaKhuVuc;

    public static final PhuThuKhuVucDTO fromJson(JSONObject jsonObject)
            throws JSONException {
        PhuThuKhuVucDTO obj = new PhuThuKhuVucDTO();

        if (!jsonObject.isNull(CL_ID))
            obj.mId = jsonObject.getInt(CL_ID);
        if (!jsonObject.isNull(CL_MA_KHU_VUC))
            obj.mMaKhuVuc = jsonObject.getInt(CL_MA_KHU_VUC);
        if (!jsonObject.isNull(CL_MA_PHU_THU))
            obj.mMaPhuThu = jsonObject.getInt(CL_MA_PHU_THU);

        return obj;
    }

    public ContentValues toContentValues() {
        ContentValues c = new ContentValues();
        c.put(CL_ID, mId);
        c.put(CL_MA_KHU_VUC, mMaKhuVuc);
        c.put(CL_MA_PHU_THU, mMaPhuThu);

        return c;
    }

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        return PhuThuKhuVucDTO.fromJson(jsonObj).toContentValues();
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaPhuThu() {
        return mMaPhuThu;
    }

    public void setMaPhuThu(Integer maPhuThu) {
        mMaPhuThu = maPhuThu;
    }

    public Integer getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(Integer maKhuVuc) {
        mMaKhuVuc = maKhuVuc;
    }
}
