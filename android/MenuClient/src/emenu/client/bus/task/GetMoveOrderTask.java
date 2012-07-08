package emenu.client.bus.task;

import emenu.client.dao.OrderDAO;

public class GetMoveOrderTask extends CustomAsyncTask<Void, Void, Boolean> {

    private Integer mOrderId;
    private Integer mTableId;

    public GetMoveOrderTask(Integer orderId, Integer tableId) {
        mOrderId = orderId;
        mTableId = tableId;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            return OrderDAO.getInstance().getMoveOrder(mOrderId, mTableId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
