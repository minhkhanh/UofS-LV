package emenu.client.bus.task;

import java.util.List;

import org.apache.http.client.HttpClient;
import org.json.JSONException;

import emenu.client.dao.OrderDAO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceSession;

public class PostOrderTask extends CustomAsyncTask<Void, Void, Boolean> {

    List<ChiTietOrderDTO> mItems;
    private HttpClient mClient;

    public PostOrderTask(HttpClient client) {
        mClient = client;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ServiceSession session = SessionManager.getInstance().loadCurrentSession();

        mItems = session.bindOrder().getOrderItems();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            return OrderDAO.getInstance().postArrayChiTietOrder(mClient, mItems);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
