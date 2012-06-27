 package client.menu.bus.loader;

import java.util.List;

import android.app.Activity;
import client.menu.dao.NgonNguDAO;
import client.menu.db.dto.NgonNguDTO;

public class LanguageListLoader extends CustomAsyncTaskLoader<List<NgonNguDTO>> {

    public LanguageListLoader(Activity context) {
        super(context);
    }

    @Override
    public List<NgonNguDTO> loadInBackground() {
        return NgonNguDAO.getInstance().getAll();
    }
}
