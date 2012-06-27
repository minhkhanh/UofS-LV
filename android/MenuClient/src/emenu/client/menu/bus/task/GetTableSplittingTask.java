package emenu.client.menu.bus.task;

import emenu.client.dao.AbstractDAO;
import emenu.client.dao.BanDAO;
import emenu.client.menu.util.U;

public class GetTableSplittingTask extends CustomAsyncTask<Integer, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            return BanDAO.getInstance().getTableSplitting(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
