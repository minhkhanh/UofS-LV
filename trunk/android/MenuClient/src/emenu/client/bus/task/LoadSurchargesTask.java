package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import emenu.client.dao.PhuThuKhuVucDAO;
import emenu.client.db.dto.PhuThuDTO;

public class LoadSurchargesTask extends CustomAsyncTask<Integer, Void, List<PhuThuDTO>> {
    @Override
    protected List<PhuThuDTO> doInBackground(Integer... params) {
        // use list to add many kind of surcharge
        List<PhuThuDTO> list = new ArrayList<PhuThuDTO>();

        // surcharge by area
        PhuThuDTO area = PhuThuKhuVucDAO.getInstance().objByTableId(params[0]);
        if (area != null)
            list.add(area);

        return list;
    }
}
