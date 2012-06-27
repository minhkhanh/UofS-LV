package emenu.client.menu.bus.task;

import emenu.client.db.dto.BanDTO;
import emenu.client.menu.dao.BanDAO;

public class PutTableTask extends CustomAsyncTask<BanDTO, Void, Boolean> {
    @Override
    protected Boolean doInBackground(BanDTO... params) {
        return BanDAO.getInstance().put(params[0]);
    }
}
