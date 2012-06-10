package client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import client.menu.app.MyAppLocale;
import client.menu.app.MyApplication;
import client.menu.dao.AbstractDAO;
import client.menu.dao.DonViTinhDAO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.db.dto.OrderDTO;
import client.menu.util.U;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;

public class GetServingOrderItemsTask extends
        CustomAsyncTask<Void, Integer, List<ContentValues>> {

    OrderDTO mOrder;

    public GetServingOrderItemsTask(Context context, OrderDTO order) {
        super(context);
        mOrder = order;
    }

    @Override
    protected List<ContentValues> doInBackground(Void... params) {
        String url = AbstractDAO.LOCAL_SERVER_URL
                + "layDanhSachChiTietOrderChuaThanhToanJson?maOrder="
                + mOrder.getMaOrder();

        NgonNguDTO nn = MyAppLocale.getCurrentLanguage((Activity) getContext());

        List<ContentValues> result = new ArrayList<ContentValues>();
        List<ChiTietOrderDTO> list = new ArrayList<ChiTietOrderDTO>();

        try {
            String response = U.loadGetResponse(url);
            JSONArray jsonArray = new JSONArray(response);
            list = ChiTietOrderDTO.fromArrayJson(jsonArray);
            for (ChiTietOrderDTO c : list) {
                ContentValues v = new ContentValues();
                v = c.toContentValues();
                v.putAll(DonViTinhDAO.getInstance().contentByDonViTinhMonAn(
                        nn.getMaNgonNgu(), c.getMaMonAn(), c.getMaDonViTinh()));
                
                result.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
