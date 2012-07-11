package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import emenu.client.dao.DonViTinhDAO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.CustomerLocale;
import emenu.client.menu.app.MenuApplication;

public class LoadBillItemsTask extends
        CustomAsyncTask<List<ChiTietOrderDTO>, Void, List<ContentValues>> {
    @Override
    protected List<ContentValues> doInBackground(List<ChiTietOrderDTO>... params) {
        List<ContentValues> result = new ArrayList<ContentValues>();
        Integer langId = MenuApplication.getInstance().customerLocale.getLanguage()
                .getMaNgonNgu();

        for (ChiTietOrderDTO c : params[0]) {
            ContentValues values = DonViTinhDAO.getInstance().contentByDishUnitWithProm(
                    c.getMaMonAn(), c.getMaDonViTinh(), langId);
            c.toContentValues(values);

            result.add(values);
        }

        return result;
    }

}
