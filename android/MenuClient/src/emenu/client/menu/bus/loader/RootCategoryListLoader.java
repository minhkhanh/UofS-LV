package emenu.client.menu.bus.loader;

import java.util.List;

import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MyApplication;
import emenu.client.menu.dao.DanhMucDAO;
import emenu.client.menu.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.menu.db.dto.NgonNguDTO;

import android.app.Activity;

public class RootCategoryListLoader extends
        CustomAsyncTaskLoader<List<DanhMucDaNgonNguDTO>> {

    public RootCategoryListLoader(Activity context) {
        super(context);
    }

    @Override
    public List<DanhMucDaNgonNguDTO> loadInBackground() {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(MyApplication.getInstance());
        return DanhMucDAO.getInstance().listDanhMucGoc(ngonNgu.getMaNgonNgu());
    }

}
