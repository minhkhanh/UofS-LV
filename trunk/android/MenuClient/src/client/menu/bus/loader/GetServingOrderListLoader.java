package client.menu.bus.loader;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import client.menu.dao.AbstractDAO;
import client.menu.db.dto.OrderDTO;
import client.menu.util.U;

public class GetServingOrderListLoader extends CustomAsyncTaskLoader<List<OrderDTO>> {

    Integer mMaBanChinh;

    public GetServingOrderListLoader(Context context, Integer maBan) {
        super(context);
        mMaBanChinh = maBan;
    }

    @Override
    public List<OrderDTO> loadInBackground() {
        final String url = AbstractDAO.LOCAL_SERVER_URL
                + "layDanhSachOrderChuaThanhToanJson?maBan=" + mMaBanChinh;

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
