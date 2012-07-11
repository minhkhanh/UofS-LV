package emenu.client.bus.loader;

import java.util.List;

import emenu.client.dao.MonAnDAO;
import emenu.client.menu.app.CustomerLocale;
import emenu.client.menu.app.MenuApplication;

import android.app.Activity;
import android.content.ContentValues;

public class DishListLoader extends CustomAsyncTaskLoader<List<ContentValues>> {

    Integer mMaDanhMuc;
    Integer mMaNgonNgu;

    public DishListLoader(Activity context, Integer maDanhMuc) {
        super(context);
        mMaDanhMuc = maDanhMuc;
        mMaNgonNgu = MenuApplication.getInstance().customerLocale.getLanguage()
                .getMaNgonNgu();
    }

    @Override
    public List<ContentValues> loadInBackground() {
        return MonAnDAO.getInstance().contentByCatIdWithProm(mMaNgonNgu, mMaDanhMuc);
    }

}
