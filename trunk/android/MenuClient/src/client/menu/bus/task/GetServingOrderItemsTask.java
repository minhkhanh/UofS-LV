package client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.ContentValues;
import client.menu.app.MyAppLocale;
import client.menu.app.MyApplication;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceSession;
import client.menu.dao.AbstractDAO;
import client.menu.dao.DonViTinhDAO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.util.U;

public class GetServingOrderItemsTask extends
        CustomAsyncTask<Integer, Void, List<ContentValues>> {
    List<ChiTietOrderDTO> mLocalItems = null;

    public GetServingOrderItemsTask(boolean includeLocal) {
        if (includeLocal) {
            ServiceSession session = SessionManager.getInstance().loadCurrentSession();
            mLocalItems = session.getOrder().getOrderItems();
        }
    }

    private void addMixedValues(List<ContentValues> listValues,
            List<ChiTietOrderDTO> listDto, Integer langId) {
        for (ChiTietOrderDTO c : listDto) {
            ContentValues v = new ContentValues();
            v = c.toContentValues();
            v.putAll(DonViTinhDAO.getInstance().contentByDonViTinhMonAn(langId,
                    c.getMaMonAn(), c.getMaDonViTinh()));

            listValues.add(v);
        }
    }

    @Override
    protected List<ContentValues> doInBackground(Integer... params) {
        String url = AbstractDAO.LOCAL_SERVER_URL
                + "layDanhSachChiTietOrderJson?maOrder=" + params[0];

        NgonNguDTO nn = MyAppLocale.getCurrentLanguage(MyApplication.getInstance());

        List<ContentValues> result = new ArrayList<ContentValues>();
        List<ChiTietOrderDTO> list = new ArrayList<ChiTietOrderDTO>();

        try {
            String response = U.loadGetResponse(url);
            JSONArray jsonArray = new JSONArray(response);
            list = ChiTietOrderDTO.fromArrayJson(jsonArray);
            addMixedValues(result, list, nn.getMaNgonNgu());

            if (mLocalItems != null) {
                addMixedValues(result, mLocalItems, nn.getMaNgonNgu());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
