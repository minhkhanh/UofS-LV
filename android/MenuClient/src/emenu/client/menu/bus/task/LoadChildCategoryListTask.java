package emenu.client.menu.bus.task;

import java.util.List;


import emenu.client.dao.DanhMucDAO;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MenuApplication;

public class LoadChildCategoryListTask extends
        CustomAsyncTask<Integer, Void, List<DanhMucDaNgonNguDTO>> {

    @Override
    protected List<DanhMucDaNgonNguDTO> doInBackground(Integer... params) {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(MenuApplication.getInstance());
        return DanhMucDAO.getInstance().listDanhMucCon(ngonNgu.getMaNgonNgu(),
                params[0]);
    }

}
