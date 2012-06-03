package client.menu.bus.loader;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import client.menu.app.MyAppLocale;
import client.menu.dao.DanhMucDAO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.db.dto.NgonNguDTO;

public class CategoryListLoader extends CustomAsyncTaskLoader<List<DanhMucDaNgonNguDTO>> {

    Integer mMaDanhMucCha;
    int mAppendPosition;

    public CategoryListLoader(Activity context, Integer maDanhMucCha, int appendPos) {
        super(context);
        mMaDanhMucCha = maDanhMucCha;
        mAppendPosition = appendPos;
    }

    @Override
    public List<DanhMucDaNgonNguDTO> loadInBackground() {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage((Activity) mHost);
        return DanhMucDAO.getInstance().listDanhMucCon(ngonNgu.getMaNgonNgu(),
                mMaDanhMucCha);
    }

    public int getAppendPosition() {
        return mAppendPosition;
    }

    public void setAppendPosition(int appendPosition) {
        mAppendPosition = appendPosition;
    }
}
