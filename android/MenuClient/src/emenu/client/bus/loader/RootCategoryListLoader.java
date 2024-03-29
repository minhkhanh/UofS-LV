package emenu.client.bus.loader;

import java.util.List;

import emenu.client.dao.DanhMucDAO;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.CustomerLocale;
import emenu.client.menu.app.MenuApplication;

import android.app.Activity;

public class RootCategoryListLoader extends
        CustomAsyncTaskLoader<List<DanhMucDaNgonNguDTO>> {

    private Integer mMaNgonNgu = MenuApplication.getInstance().customerLocale.getLangId();

    public RootCategoryListLoader(Activity context) {
        super(context);
    }

    @Override
    public List<DanhMucDaNgonNguDTO> loadInBackground() {
        return DanhMucDAO.getInstance().listDanhMucGoc(mMaNgonNgu);
    }

}
