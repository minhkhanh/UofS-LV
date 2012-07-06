package emenu.client.bus.task;

import java.util.List;

import android.content.ContentValues;
import emenu.client.dao.DonViTinhDAO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MenuApplication;

public class LoadDishUnitsTask extends
        CustomAsyncTask<Integer, Void, List<ContentValues>> {

    @Override
    protected List<ContentValues> doInBackground(Integer... params) {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(MenuApplication.getInstance());
        List<ContentValues> list = DonViTinhDAO.getInstance().contentByMaMonAn(
                ngonNgu.getMaNgonNgu(), params[0]);

        return list;
    }
}
