package emenu.client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.content.ContentValues;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MenuApplication;
import emenu.client.menu.bus.SessionManager;
import emenu.client.menu.bus.SessionManager.ServiceSession;
import emenu.client.menu.dao.AbstractDAO;
import emenu.client.menu.dao.DonViTinhDAO;
import emenu.client.menu.util.U;

public class GetServingOrderItemsTask extends
        CustomAsyncTask<Integer, Void, List<ContentValues>> {
    public static final int FLAG_UNORDERED_ONLY = 0;
    public static final int FLAG_ORDERED_ONLY = 1;
    public static final int FLAG_BOTH = 2;

    private int mFlag;
    private List<ChiTietOrderDTO> mLocalItems = null;

    public GetServingOrderItemsTask(int flag) {
        mFlag = flag;

        if (flag == FLAG_UNORDERED_ONLY || flag == FLAG_BOTH) {
            ServiceSession session = SessionManager.getInstance().loadCurrentSession();
            mLocalItems = session.getOrder().getOrderItems();
        }
    }

    public static void addMixedValues(List<ContentValues> listValues,
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
        NgonNguDTO nn = MyAppLocale.getCurrentLanguage(MenuApplication.getInstance());

        List<ContentValues> result = new ArrayList<ContentValues>();
        List<ChiTietOrderDTO> list = new ArrayList<ChiTietOrderDTO>();

        try {
            if (mFlag == FLAG_ORDERED_ONLY || mFlag == FLAG_BOTH) {
                String url = AbstractDAO.LOCAL_SERVER_URL
                        + "layDanhSachChiTietOrderJson?maOrder=" + params[0];
                
                String response = U.loadGetResponse(url);
                JSONArray jsonArray = new JSONArray(response);
                list = ChiTietOrderDTO.fromArrayJson(jsonArray);
                addMixedValues(result, list, nn.getMaNgonNgu());
            }

            if (mFlag == FLAG_UNORDERED_ONLY || mFlag == FLAG_BOTH) {
                addMixedValues(result, mLocalItems, nn.getMaNgonNgu());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ContentValues>();
        }

        return result;
    }
}
