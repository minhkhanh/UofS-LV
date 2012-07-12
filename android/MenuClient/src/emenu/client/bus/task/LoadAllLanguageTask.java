package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import emenu.client.dao.NgonNguDAO;
import emenu.client.db.dto.NgonNguDTO;

public class LoadAllLanguageTask extends CustomAsyncTask<Void, Void, List<NgonNguDTO>> {
    @Override
    protected List<NgonNguDTO> doInBackground(Void... params) {
        try {
            return NgonNguDAO.getInstance().listAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<NgonNguDTO>();
        }
    }
}
