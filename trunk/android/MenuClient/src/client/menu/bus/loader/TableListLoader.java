package client.menu.bus.loader;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import client.menu.dao.BanDAO;
import client.menu.db.dto.BanDTO;

public class TableListLoader extends CustomAsyncTaskLoader<List<BanDTO>> {
    private Integer mAreaId;

    public TableListLoader(Activity context, Integer maKhuVuc) {
        super(context);
        mAreaId = maKhuVuc;
    }

    @Override
    public List<BanDTO> loadInBackground() {
        return BanDAO.getInstance().getByKhuVuc(mAreaId);
    }
}
