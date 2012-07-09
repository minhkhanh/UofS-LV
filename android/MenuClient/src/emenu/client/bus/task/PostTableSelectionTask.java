package emenu.client.bus.task;

import org.apache.http.client.HttpClient;

import emenu.client.dao.BanDAO;
import emenu.client.db.dto.TableSelection;

public class PostTableSelectionTask extends
        CustomAsyncTask<TableSelection.TableIdSelection, Void, Boolean> {

    private HttpClient mClient;

    public PostTableSelectionTask(HttpClient client) {
        mClient = client;
    }

    @Override
    protected Boolean doInBackground(TableSelection.TableIdSelection... params) {
        try {
            return BanDAO.getInstance().postGroupTable(mClient, params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
