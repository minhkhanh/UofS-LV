package client.menu.bus.loader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import client.menu.dao.AbstractDAO;
import client.menu.db.dto.OrderDTO;
import client.menu.util.U;

public class GetServingOrderListLoader extends CustomAsyncTaskLoader<List<OrderDTO>> {

    Integer mMaBan;

    public GetServingOrderListLoader(Activity context, Integer maBan) {
        super(context);
        mMaBan = maBan;
    }

    @Override
    public List<OrderDTO> loadInBackground() {
        final String url = AbstractDAO.LOCAL_SERVER_URL
                + "layDanhSachOrderChuaThanhToanJson?maBan=" + mMaBan;

        List<OrderDTO> list = new ArrayList<OrderDTO>();

        try {
            String jsonData = U.loadGetResponse(url);
            list = OrderDTO.fromJsonArray(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
