package emenu.client.bus.task;

import emenu.client.dao.BanDAO;
import emenu.client.db.dto.TableSelection;

public class PostTableSelectionTask extends
        CustomAsyncTask<TableSelection.TableIdSelection, Void, Boolean> {

    @Override
    protected Boolean doInBackground(TableSelection.TableIdSelection... params) {
        try {
            return BanDAO.getInstance().postGroupTable(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
