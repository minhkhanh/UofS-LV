package client.menu.bus.task;

import android.content.Context;
import client.menu.dao.AbstractDAO;
import client.menu.db.dto.YeuCauGhepBan;
import client.menu.util.U;

public class PostTableMergingTask extends CustomAsyncTask<Void, Integer, Integer> {

    public static final String POST_TABLE_MERGING_JSON_URL = AbstractDAO.LOCAL_SERVER_URL
            + "ghepBanJson";

    YeuCauGhepBan mYeuCau;

    public PostTableMergingTask(Context context, YeuCauGhepBan yc) {
        super(context);
        mYeuCau = yc;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        try {
            String jsonData = mYeuCau.toJsonString();
            String respString = U.loadPostResponseJson(POST_TABLE_MERGING_JSON_URL,
                    jsonData);
            return Integer.valueOf(respString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
