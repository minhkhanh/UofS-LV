package emenu.client.bus.loader;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import emenu.client.dao.AbstractDAO;
import emenu.client.db.dto.OrderDTO;
import emenu.client.util.U;

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
