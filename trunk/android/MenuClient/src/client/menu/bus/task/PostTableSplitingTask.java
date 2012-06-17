package client.menu.bus.task;

import client.menu.dao.AbstractDAO;
import client.menu.util.U;
import android.content.Context;

public class PostTableSplitingTask extends CustomAsyncTask<Integer, Void, Boolean> {
    public static final String URL = AbstractDAO.LOCAL_SERVER_URL + "tachBanJson";
    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            String data = params[0].toString();
            String respString = U.loadPostResponseJson(URL, data);
            return Boolean.valueOf(respString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
