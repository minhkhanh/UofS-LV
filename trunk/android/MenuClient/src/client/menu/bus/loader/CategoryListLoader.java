package client.menu.bus.loader;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import client.menu.app.MyAppLocale;
import client.menu.dao.DanhMucDAO;
import client.menu.db.dto.NgonNguDTO;

public class CategoryListLoader extends CustomAsyncTaskLoader<Cursor> {

    public CategoryListLoader(Activity context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage((Activity) mHost);
        return DanhMucDAO.getInstance().cursorAll(ngonNgu.getMaNgonNgu());
    }
}
