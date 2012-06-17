package client.menu.bus.task;

import java.util.List;

import android.content.ContentValues;
import client.menu.app.MyAppLocale;
import client.menu.app.MyApplication;
import client.menu.dao.DonViTinhDAO;
import client.menu.db.dto.NgonNguDTO;

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
