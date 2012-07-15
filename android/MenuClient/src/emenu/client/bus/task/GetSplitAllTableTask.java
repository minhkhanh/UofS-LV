package emenu.client.bus.task;

import emenu.client.dao.BanDAO;

public class GetSplitAllTableTask extends CustomAsyncTask<Integer, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            return BanDAO.getInstance().getTableSplittingAll(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
