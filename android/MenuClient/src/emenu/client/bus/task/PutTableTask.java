package emenu.client.bus.task;

import emenu.client.dao.BanDAO;
import emenu.client.db.dto.BanDTO;

public class PutTableTask extends CustomAsyncTask<BanDTO, Void, Boolean> {
    @Override
    protected Boolean doInBackground(BanDTO... params) {
        return BanDAO.getInstance().put(params[0]);
    }
}
