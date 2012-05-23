package client.menu.bus;

import java.util.List;
import java.util.Map;

import android.content.Context;
import client.menu.db.dao.NgonNguDAO;

public class LanguageListLoader extends CustomAsyncTaskLoader<List<Map<String, Object>>> {

    public LanguageListLoader(Context context) {
        super(context);
    }

    @Override
    protected void releaseResources(List<Map<String, Object>> data) {
    }

    @Override
    public List<Map<String, Object>> loadInBackground() {
        return NgonNguDAO.getInstance().mapAll();
    }
}
