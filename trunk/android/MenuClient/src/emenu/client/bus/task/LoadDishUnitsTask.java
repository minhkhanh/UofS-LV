package emenu.client.bus.task;

import java.util.List;

import android.content.ContentValues;
import emenu.client.dao.DonViTinhDAO;
import emenu.client.menu.app.MenuApplication;

public class LoadDishUnitsTask extends
        CustomAsyncTask<Integer, Void, List<ContentValues>> {

    @Override
    protected List<ContentValues> doInBackground(Integer... params) {
        Integer langId = MenuApplication.getInstance().customerLocale.getLanguage()
                .getMaNgonNgu();
        List<ContentValues> list = DonViTinhDAO.getInstance().contentByMaMonAn(langId,
                params[0]);

        return list;
    }
}
