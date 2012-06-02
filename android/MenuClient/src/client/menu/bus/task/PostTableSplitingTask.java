package client.menu.bus.task;

import client.menu.dao.AbstractDAO;
import client.menu.util.U;
import android.content.Context;

public class PostTableSplitingTask extends CustomAsyncTask<Void, Void, Boolean> {
    public static final String URL = AbstractDAO.LOCAL_SERVER_URL + "tachBanJson";

    Integer mMaBanChinh;

    public PostTableSplitingTask(Context context, int id, Integer maBanChinh) {
        super(context, id);
        mMaBanChinh = maBanChinh;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            String data = mMaBanChinh.toString();
            String respString = U.loadPostResponseJson(URL, data);
            return Boolean.valueOf(respString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
