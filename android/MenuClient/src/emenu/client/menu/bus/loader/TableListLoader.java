package emenu.client.menu.bus.loader;

import java.util.List;

import android.app.Activity;
import emenu.client.db.dto.BanDTO;
import emenu.client.menu.dao.BanDAO;

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
