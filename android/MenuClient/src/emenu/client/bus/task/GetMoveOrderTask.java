package emenu.client.bus.task;

import org.apache.http.client.HttpClient;

import emenu.client.dao.OrderDAO;

public class GetMoveOrderTask extends CustomAsyncTask<Void, Void, Boolean> {

    private Integer mOrderId;
    private Integer mTableId;
    private HttpClient mClient;

    public GetMoveOrderTask(HttpClient client, Integer orderId, Integer tableId) {
        mOrderId = orderId;
        mTableId = tableId;
        mClient = client;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            return OrderDAO.getInstance().getMoveOrder(mClient, mOrderId, mTableId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
