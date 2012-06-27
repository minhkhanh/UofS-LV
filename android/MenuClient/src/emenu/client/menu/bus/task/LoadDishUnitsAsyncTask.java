package emenu.client.menu.bus.task;

import java.util.List;

import android.content.ContentValues;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MyApplication;
import emenu.client.menu.dao.DonViTinhDAO;

public class LoadDishUnitsAsyncTask extends
        CustomAsyncTask<Integer, Void, List<ContentValues>> {

    @Override
    protected List<ContentValues> doInBackground(Integer... params) {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(MyApplication.getInstance());
        List<ContentValues> list = DonViTinhDAO.getInstance().contentByMaMonAn(
                ngonNgu.getMaNgonNgu(), params[0]);

        return list;
    }
}
