package client.menu.bus.loader;

import java.util.List;

import client.menu.app.MyAppSettings;
import client.menu.dao.MonAnDAO;
import client.menu.db.dto.NgonNguDTO;

import android.app.Activity;
import android.content.ContentValues;

public class DishListLoader extends CustomAsyncTaskLoader<List<ContentValues>> {

    Integer mMaDanhMuc;

    public DishListLoader(Activity context, Integer maDanhMuc) {
        super(context);
        mMaDanhMuc = maDanhMuc;
    }

    @Override
    public List<ContentValues> loadInBackground() {
        NgonNguDTO ngonNgu = MyAppSettings.getCurrentAppLocale(mHost).getLanguage();
        return MonAnDAO.getInstance().contentByMaDanhMuc(ngonNgu.getMaNgonNgu(),
                mMaDanhMuc);
    }

}