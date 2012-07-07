 package emenu.client.bus.loader;

import java.util.List;

import android.app.Activity;
import emenu.client.dao.NgonNguDAO;
import emenu.client.db.dto.NgonNguDTO;

public class LanguageListLoader extends CustomAsyncTaskLoader<List<NgonNguDTO>> {

    public LanguageListLoader(Activity context) {
        super(context);
    }

    @Override
    public List<NgonNguDTO> loadInBackground() {
        return NgonNguDAO.getInstance().getAll();
    }
}