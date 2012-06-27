package client.menu.db.dto;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

public class MonLienQuanDTO {
    public static final String TABLE_NAME = "ChiTietMonLienQuan";

    public static final String CL_MA_MON = "MaMonAn";
    public static final String CL_MA_MON_LIEN_QUAN = "MaMonAnLienQuan";

    private Integer mId;
    private Integer mMaMonAn;
    private Integer mMaMonAnLienQuan;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_MON)) {
            values.put(CL_MA_MON, jsonObj.getInt(CL_MA_MON));
        }
        if (!jsonObj.isNull(CL_MA_MON_LIEN_QUAN)) {
            values.put(CL_MA_MON_LIEN_QUAN, jsonObj.getInt(CL_MA_MON_LIEN_QUAN));
        }
        return values;
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

    public Integer getMaMonAnLienQuan() {
        return mMaMonAnLienQuan;
    }

    public void setMaMonAnLienQuan(Integer maMonAnLienQuan) {
        mMaMonAnLienQuan = maMonAnLienQuan;
    }

}
