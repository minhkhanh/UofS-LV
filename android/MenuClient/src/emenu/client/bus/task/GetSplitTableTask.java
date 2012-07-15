package emenu.client.bus.task;

import emenu.client.dao.BanDAO;

public class GetSplitTableTask extends CustomAsyncTask<Integer, Void, Boolean> {
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
