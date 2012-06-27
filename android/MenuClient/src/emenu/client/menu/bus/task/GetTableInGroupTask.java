package emenu.client.menu.bus.task;

import java.util.List;

import emenu.client.db.dto.BanDTO;
import emenu.client.menu.dao.BanDAO;

public class GetTableInGroupTask extends CustomAsyncTask<Integer, Void, List<BanDTO>> {
    @Override
    protected List<BanDTO> doInBackground(Integer... params) {
        return BanDAO.getInstance().getTableInGroup(params[0]);
    }
}
