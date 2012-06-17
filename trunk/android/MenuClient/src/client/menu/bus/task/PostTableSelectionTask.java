package client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import client.menu.dao.AbstractDAO;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.YeuCauGhepBan;
import client.menu.util.U;

public class PostTableSelectionTask extends CustomAsyncTask<Void, Void, Boolean> {
    
    List<Integer> mTabIdList;

    public PostTableSelectionTask(List<BanDTO> tabList) {
        mTabIdList = new ArrayList<Integer>();
        for (BanDTO b : tabList) {
            mTabIdList.add(b.getMaBan());
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        String url = AbstractDAO.LOCAL_SERVER_URL + "ghepBanJson";

        try {
            JSONArray jsonArr = new JSONArray();
            for (Integer i : mTabIdList) {
                jsonArr.put(i);
            }

            String jsonData = jsonArr.toString();
            String respString = U.loadPostResponseJson(url, jsonData);

            return Boolean.valueOf(respString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
