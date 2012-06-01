package client.menu.bus.task;

import org.json.JSONObject;

import android.content.Context;
import client.menu.dao.AbstractDAO;
import client.menu.db.dto.YeuCauGhepBan;
import client.menu.util.U;

public class PostTableMergingTask extends
        CustomAsyncTask<YeuCauGhepBan, Integer, Boolean> {

    public static final String POST_TABLE_MERGING_JSON_URL = AbstractDAO.LOCAL_SERVER_URL
            + "ghepBanJson";

    public PostTableMergingTask(Context context, int id) {
        super(context, id);
    }

    @Override
    protected Boolean doInBackground(YeuCauGhepBan... params) {
        for (YeuCauGhepBan yc : params) {
            try {
                String jsonData = yc.toJsonString();
                String respString = U.loadPostResponseJson(POST_TABLE_MERGING_JSON_URL,
                        jsonData);
                JSONObject jsonObj = new JSONObject(respString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
