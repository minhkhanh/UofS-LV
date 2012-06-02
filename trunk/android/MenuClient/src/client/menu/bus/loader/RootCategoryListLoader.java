package client.menu.bus.loader;

import java.util.List;

import client.menu.app.MyAppLocale;
import client.menu.dao.DanhMucDAO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.db.dto.NgonNguDTO;

import android.app.Activity;

public class RootCategoryListLoader extends
        CustomAsyncTaskLoader<List<DanhMucDaNgonNguDTO>> {

    public RootCategoryListLoader(Activity context) {
        super(context);
    }

    @Override
    public List<DanhMucDaNgonNguDTO> loadInBackground() {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(mHost);
        return DanhMucDAO.getInstance().listDanhMucGoc(ngonNgu.getMaNgonNgu());
    }

}
