package client.menu.bus.task;

import java.util.List;

import client.menu.R;
import client.menu.app.MyAppLocale;
import client.menu.dao.DonViTinhDAO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.ui.adapter.DishUnitsAdapter;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class LoadDishUnitsAsyncTask extends
        CustomAsyncTask<Void, Integer, DishUnitsAdapter> {

    Integer mMaMonAn;

    public LoadDishUnitsAsyncTask(Context context, Integer maMonAn) {
        super(context);
        mMaMonAn = maMonAn;
    }

    @Override
    protected DishUnitsAdapter doInBackground(Void... params) {
        NgonNguDTO ngonNgu = MyAppLocale.getCurrentLanguage((Activity) getContext());
        List<ContentValues> list = DonViTinhDAO.getInstance().contentByMaMonAn(
                ngonNgu.getMaNgonNgu(), mMaMonAn);

        DishUnitsAdapter adapter = new DishUnitsAdapter(getContext(), list);

        return adapter;
    }

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

}
