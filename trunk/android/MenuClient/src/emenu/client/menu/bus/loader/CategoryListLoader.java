package emenu.client.menu.bus.loader;

import java.util.List;

import android.app.Activity;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MyApplication;
import emenu.client.menu.dao.DanhMucDAO;

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
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(MyApplication.getInstance());
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
