package emenu.client.menu.bus.task;

import emenu.client.menu.dao.BanDAO;
import emenu.client.menu.db.dto.BanDTO;

public class PutTableTask extends CustomAsyncTask<BanDTO, Void, Boolean> {
    @Override
    protected Boolean doInBackground(BanDTO... params) {
        return BanDAO.getInstance().put(params[0]);
    }
}
