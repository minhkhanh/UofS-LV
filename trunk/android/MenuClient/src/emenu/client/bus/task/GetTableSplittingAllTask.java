package emenu.client.bus.task;

import emenu.client.dao.BanDAO;

public class GetTableSplittingAllTask extends CustomAsyncTask<Integer, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Integer... params) {
        return BanDAO.getInstance().getTableSplittingAll(params[0]);
    }

}
