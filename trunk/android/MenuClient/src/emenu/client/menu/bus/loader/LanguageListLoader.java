 package emenu.client.menu.bus.loader;

import java.util.List;

import android.app.Activity;
import emenu.client.menu.dao.NgonNguDAO;
import emenu.client.menu.db.dto.NgonNguDTO;

public class LanguageListLoader extends CustomAsyncTaskLoader<List<NgonNguDTO>> {

    public LanguageListLoader(Activity context) {
        super(context);
    }

    @Override
    public List<NgonNguDTO> loadInBackground() {
        return NgonNguDAO.getInstance().getAll();
    }
}
