package client.menu.bus.task;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import client.menu.app.MyAppLocale;
import client.menu.app.MyApplication;
import client.menu.dao.DanhMucDAO;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.db.dto.NgonNguDTO;

public class LoadChildCategoryListTask extends
        CustomAsyncTask<Integer, Void, List<DanhMucDaNgonNguDTO>> {

    @Override
    protected List<DanhMucDaNgonNguDTO> doInBackground(Integer... params) {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage(MyApplication.getInstance());
        return DanhMucDAO.getInstance().listDanhMucCon(ngonNgu.getMaNgonNgu(),
                params[0]);
    }

}
