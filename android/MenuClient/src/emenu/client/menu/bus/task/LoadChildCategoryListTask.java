package emenu.client.menu.bus.task;

import java.util.List;


import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MyApplication;
import emenu.client.menu.dao.DanhMucDAO;
import emenu.client.menu.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.menu.db.dto.NgonNguDTO;

public class LoadChildCategoryListTask extends
        CustomAsyncTask<Integer, Void, List<DanhMucDaNgonNguDTO>> {

    @Override
    protected List<DanhMucDaNgonNguDTO> doInBackground(Integer... params) {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(MyApplication.getInstance());
        return DanhMucDAO.getInstance().listDanhMucCon(ngonNgu.getMaNgonNgu(),
                params[0]);
    }

}
