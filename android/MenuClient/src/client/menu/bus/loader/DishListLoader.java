package client.menu.bus.loader;

import java.util.List;

import client.menu.app.MyAppLocale;
import client.menu.app.MyApplication;
import client.menu.dao.MonAnDAO;

import android.app.Activity;
import android.content.ContentValues;

public class DishListLoader extends CustomAsyncTaskLoader<List<ContentValues>> {

    Integer mMaDanhMuc;
    Integer mMaNgonNgu;

    public DishListLoader(Activity context, Integer maDanhMuc) {
        super(context);
        mMaDanhMuc = maDanhMuc;
        mMaNgonNgu = MyAppLocale.getCurrentLanguage(MyApplication.getInstance())
                .getMaNgonNgu();
    }

    @Override
    public List<ContentValues> loadInBackground() {
        return MonAnDAO.getInstance().contentByMaDanhMuc(mMaNgonNgu, mMaDanhMuc);
    }

}
