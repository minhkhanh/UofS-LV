package emenu.client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MyApplication;
import emenu.client.menu.dao.DonViTinhDAO;
import emenu.client.menu.db.dto.ChiTietOrderDTO;
import emenu.client.menu.db.dto.NgonNguDTO;

public class LoadBillItemsTask extends
        CustomAsyncTask<List<ChiTietOrderDTO>, Void, List<ContentValues>> {
    @Override
    protected List<ContentValues> doInBackground(List<ChiTietOrderDTO>... params) {
        List<ContentValues> result = new ArrayList<ContentValues>();
        NgonNguDTO n = MyAppLocale.getCurrentLanguage(MyApplication.getInstance());

        for (ChiTietOrderDTO c : params[0]) {
            ContentValues values = DonViTinhDAO.getInstance().contentByDonViTinhMonAn(
                    c.getMaMonAn(), c.getMaDonViTinh(), n.getMaNgonNgu());
            c.to(values);

            result.add(values);
        }

        return result;
    }

}
