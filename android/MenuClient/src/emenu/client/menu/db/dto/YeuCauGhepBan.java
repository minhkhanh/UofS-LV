package emenu.client.menu.db.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YeuCauGhepBan {
    public static final String CL_MA_BAN_CHINH = "MaBanChinh";
    public static final String CL_LIST_BAN_PHU = "MaBanPhuList";

    Integer mMaBanChinh;
    List<Integer> mMaBanPhuList;
    
    public static final YeuCauGhepBan fromTableList(List<BanDTO> list) {
        YeuCauGhepBan yc = new YeuCauGhepBan();
        
        Integer maBanChinh = list.get(0).getMaBan();
        list.remove(0);
        yc.mMaBanChinh = maBanChinh;
        List<Integer> listMaBanPhu = new ArrayList<Integer>();
        for (BanDTO b : list) {
            listMaBanPhu.add(b.getMaBan());
        }
        
        yc.mMaBanPhuList = listMaBanPhu;
        
        return yc;
    }

    public String toJsonString() throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(CL_MA_BAN_CHINH, mMaBanChinh);

        JSONArray array = new JSONArray();
        for (Integer i : mMaBanPhuList) {
            array.put(i);
        }

        jsonObj.put(CL_LIST_BAN_PHU, array);

        return jsonObj.toString();
    }

    public static final YeuCauGhepBan fromJson(JSONObject jsonObj) throws JSONException {
        YeuCauGhepBan obj = new YeuCauGhepBan();
        if (!jsonObj.isNull(CL_MA_BAN_CHINH)) {
            obj.mMaBanChinh = jsonObj.getInt(CL_MA_BAN_CHINH);
        }
        if (!jsonObj.isNull(CL_LIST_BAN_PHU)) {
            JSONArray array = jsonObj.getJSONArray(CL_LIST_BAN_PHU);
            obj.mMaBanPhuList = new ArrayList<Integer>();
            for (int i = 0; i < array.length(); ++i) {
                obj.mMaBanPhuList.add(array.getInt(i));
            }
        }

        return obj;
    }

    public Integer getMaBanChinh() {
        return mMaBanChinh;
    }

    public void setMaBanChinh(Integer maBanChinh) {
        mMaBanChinh = maBanChinh;
    }

    public List<Integer> getMaBanPhuList() {
        return mMaBanPhuList;
    }

    public void setMaBanPhuList(List<Integer> maBanPhuList) {
        mMaBanPhuList = maBanPhuList;
    }
}
