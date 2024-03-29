package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.ContentValues;
import android.os.Bundle;
import emenu.client.dao.AbstractDAO;
import emenu.client.dao.DonViTinhDAO;
import emenu.client.dao.OrderDAO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.db.dto.OrderDTO;
import emenu.client.menu.app.CustomerLocale;
import emenu.client.menu.app.MenuApplication;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceSession;
import emenu.client.util.U;

public class GetServingOrderItemsTask extends
        CustomAsyncTask<Integer, Void, List<ContentValues>> {
    public static final int FLAG_UNORDERED_ONLY = 0;
    public static final int FLAG_ORDERED_ONLY = 1;
    public static final int FLAG_BOTH = 2;

    private OrderFlag mFlag;
    private List<ChiTietOrderDTO> mLocalItems = null;

    public enum OrderFlag {
        UnorderedOnly, OrderedOnly, Both
    }

    public GetServingOrderItemsTask(OrderFlag flag) {
        mFlag = flag;

        if (flag == OrderFlag.UnorderedOnly || flag == OrderFlag.Both) {
            ServiceSession session = SessionManager.getInstance().loadCurrentSession();
            mLocalItems = session.getOrder().getItems();
        }
    }

    public static void addMixedValues(List<ContentValues> listValues,
            List<ChiTietOrderDTO> listDto, Integer langId) {
        for (ChiTietOrderDTO c : listDto) {
            ContentValues v = new ContentValues();
            v = c.toContentValues();
            v.putAll(DonViTinhDAO.getInstance().contentByDishUnitWithProm(langId,
                    c.getMaMonAn(), c.getMaDonViTinh()));

            listValues.add(v);
        }
    }

    @Override
    protected List<ContentValues> doInBackground(Integer... params) {
        Integer langId = MenuApplication.getInstance().customerLocale.getLanguage()
                .getMaNgonNgu();

        List<ContentValues> result = new ArrayList<ContentValues>();
        List<ChiTietOrderDTO> list = new ArrayList<ChiTietOrderDTO>();

        try {
            if (mFlag == OrderFlag.OrderedOnly || mFlag == OrderFlag.Both) {
                String url = AbstractDAO.SERVER_URL_SLASH
                        + "layDanhSachChiTietOrderJson?maOrder=" + params[0];

                String response = U.loadGetResponse(url);
                JSONArray jsonArray = new JSONArray(response);
                list = ChiTietOrderDTO.fromArrayJson(jsonArray);
                addMixedValues(result, list, langId);

                OrderDTO order = OrderDAO.getInstance().getOrder(params[0]);
                ContentValues c = order.toContentValues();
                Bundle b = getExtras();
                b.putParcelable(OrderDTO.TABLE_NAME, c);
            }

            if (mFlag == OrderFlag.UnorderedOnly || mFlag == OrderFlag.Both) {
                addMixedValues(result, mLocalItems, langId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ContentValues>();
        }

        return result;
    }

    public OrderFlag getFlag() {
        return mFlag;
    }

    public void setFlag(OrderFlag flag) {
        mFlag = flag;
    }
}
