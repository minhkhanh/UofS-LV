package emenu.client.bus.task;

import emenu.client.dao.KhuyenMaiMonDAO;
import emenu.client.db.dto.KhuyenMaiDTO;

public class LoadDishPromotionTask extends CustomAsyncTask<Integer, Void, KhuyenMaiDTO> {
    @Override
    protected KhuyenMaiDTO doInBackground(Integer... params) {
        try {
            return KhuyenMaiMonDAO.getInstance().objByDishId(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
