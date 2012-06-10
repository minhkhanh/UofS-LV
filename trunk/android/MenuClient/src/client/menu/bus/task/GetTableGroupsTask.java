package client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import client.menu.dao.AbstractDAO;
import client.menu.util.U;

import android.content.Context;

public class GetTableGroupsTask extends CustomAsyncTask<Void, Integer, List<JSONObject>> {

    public GetTableGroupsTask(Context context) {
        super(context);
    }

    @Override
    protected List<JSONObject> doInBackground(Void... params) {
        String url = AbstractDAO.LOCAL_SERVER_URL + "layDanhSachBanChinhJson";
        List<JSONObject> list = new ArrayList<JSONObject>();
        try {
            String response = U.loadGetResponse(url);
            JSONArray listGroup = new JSONArray(response);
            for (int i = 0; i < listGroup.length(); ++i) {
                JSONObject group = listGroup.getJSONObject(i);
                url = AbstractDAO.LOCAL_SERVER_URL + "layDanhSachBanThuocBanChinhJson";
                response = U.loadGetResponse(url);
                JSONArray listChild = new JSONArray(response);
                group.put("listChild", listChild);
                
                list.add(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }

}
