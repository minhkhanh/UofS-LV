package emenu.client.bus.task;

import emenu.client.dao.OrderDAO;
import emenu.client.db.dto.ChiTietOrderDTO;

public class GetOrderItemTask extends CustomAsyncTask<Integer, Void, ChiTietOrderDTO> {
    @Override
    protected ChiTietOrderDTO doInBackground(Integer... params) {
        try {
            return OrderDAO.getInstance().getOrderItem(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
