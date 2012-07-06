package emenu.client.bus.task;

import java.util.List;

import emenu.client.dao.BanDAO;
import emenu.client.db.dto.BanDTO;

public class GetTableInGroupTask extends CustomAsyncTask<Integer, Void, List<BanDTO>> {
    @Override
    protected List<BanDTO> doInBackground(Integer... params) {
        return BanDAO.getInstance().getTableInGroup(params[0]);
    }
}
