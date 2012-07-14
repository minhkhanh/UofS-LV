package emenu.client.bus.task;

import java.util.List;

import org.json.JSONException;

import emenu.client.dao.OrderDAO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceSession;

public class PostOrderTask extends CustomAsyncTask<Void, Void, Boolean> {

    private List<ChiTietOrderDTO> mItems;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ServiceSession session = SessionManager.getInstance().loadCurrentSession();
        mItems = session.bindOrder().getItems();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            return OrderDAO.getInstance().postArrayChiTietOrder(mItems);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
