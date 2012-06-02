package client.menu.db.dto;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class DanhMucDTO {
    public static final String TABLE_NAME = "DanhMuc";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DANH_MUC = "MaDanhMuc";
    public static final String CL_MA_DANH_MUC_CHA = "MaDanhMucCha";
    
    public static final String CL_MA_DANH_MUC_QN = TABLE_NAME + ".MaDanhMuc";

    Integer mId;
    Integer mMaDanhMuc;
    Integer mMaDanhMucCha;

    public static final ContentValues toContentValues(JSONObject jsonObj)
            throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_DANH_MUC)) {
            values.put(CL_MA_DANH_MUC, jsonObj.getInt(CL_MA_DANH_MUC));
        }
        if (!jsonObj.isNull(CL_MA_DANH_MUC_CHA)) {
            values.put(CL_MA_DANH_MUC_CHA, jsonObj.getInt(CL_MA_DANH_MUC_CHA));
        }

        return values;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(CL_ID, mId);
        values.put(CL_MA_DANH_MUC, mMaDanhMuc);
        values.put(CL_MA_DANH_MUC_CHA, mMaDanhMucCha);

        return values;
    }

    public static final List<DanhMucDTO> fromJsonArray(String jsonData)
            throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonData);
        List<DanhMucDTO> list = new ArrayList<DanhMucDTO>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            list.add(DanhMucDTO.fromJson(jsonObj));
        }

        return list;
    }

    public static final DanhMucDTO fromJson(JSONObject json) throws JSONException {
        DanhMucDTO obj = new DanhMucDTO();
        obj.mMaDanhMuc = json.getInt(CL_MA_DANH_MUC);
        obj.mMaDanhMucCha = json.getInt(CL_MA_DANH_MUC_CHA);

        return obj;
    }

    public Integer getMaDanhMuc() {
        return mMaDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.mMaDanhMuc = maDanhMuc;
    }

    public Integer getMaDanhMucCha() {
        return mMaDanhMucCha;
    }

    public void setMaDanhMucCha(Integer maDanhMucCha) {
        this.mMaDanhMucCha = maDanhMucCha;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    @Override
    public String toString() {
        return getMaDanhMuc() + "";
    }
}
