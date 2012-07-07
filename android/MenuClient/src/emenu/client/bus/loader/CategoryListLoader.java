package emenu.client.bus.loader;

import java.util.List;

import android.app.Activity;
import emenu.client.dao.DanhMucDAO;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MenuApplication;

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
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(MenuApplication.getInstance());
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