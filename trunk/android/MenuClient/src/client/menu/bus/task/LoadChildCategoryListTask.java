package client.menu.bus.task;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import client.menu.app.MyAppLocale;
import client.menu.dao.DanhMucDAO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.db.dto.NgonNguDTO;

public class LoadChildCategoryListTask extends
        CustomAsyncTask<Void, Integer, List<DanhMucDaNgonNguDTO>> {

    private Integer mMaDanhMucCha;

    public LoadChildCategoryListTask(Context context, Integer maDanhMucCha) {
        super(context);

        mMaDanhMucCha = maDanhMucCha;
    }

    @Override
    protected List<DanhMucDaNgonNguDTO> doInBackground(Void... params) {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage((Activity) getContext());
        return DanhMucDAO.getInstance().listDanhMucCon(ngonNgu.getMaNgonNgu(),
                mMaDanhMucCha);
    }

}
