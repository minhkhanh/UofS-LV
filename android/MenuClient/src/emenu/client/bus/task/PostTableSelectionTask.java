package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import emenu.client.dao.AbstractDAO;
import emenu.client.db.dto.BanDTO;
import emenu.client.util.U;

public class PostTableSelectionTask extends CustomAsyncTask<Void, Void, Boolean> {

    List<Integer> mTabIdList;

    public PostTableSelectionTask(List<Integer> idList) {
        mTabIdList = idList;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        String url = AbstractDAO.SERVER_URL_SLASH + "ghepBanJson";

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
