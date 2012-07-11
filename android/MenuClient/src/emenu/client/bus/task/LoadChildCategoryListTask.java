package emenu.client.bus.task;

import java.util.List;

import emenu.client.dao.DanhMucDAO;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.CustomerLocale;
import emenu.client.menu.app.MenuApplication;

public class LoadChildCategoryListTask extends
        CustomAsyncTask<Integer, Void, List<DanhMucDaNgonNguDTO>> {

    @Override
    protected List<DanhMucDaNgonNguDTO> doInBackground(Integer... params) {
        Integer langId = MenuApplication.getInstance().customerLocale.getLanguage()
                .getMaNgonNgu();
        return DanhMucDAO.getInstance().listDanhMucCon(langId, params[0]);
    }

}
