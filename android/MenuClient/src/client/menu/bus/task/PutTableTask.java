package client.menu.bus.task;

import client.menu.dao.BanDAO;
import client.menu.db.dto.BanDTO;

public class PutTableTask extends CustomAsyncTask<BanDTO, Void, Boolean> {
    @Override
    protected Boolean doInBackground(BanDTO... params) {
        return BanDAO.getInstance().put(params[0]);
    }
}
