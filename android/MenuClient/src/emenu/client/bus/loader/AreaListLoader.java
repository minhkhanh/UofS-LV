package emenu.client.bus.loader;

import android.app.Activity;
import android.database.Cursor;
import emenu.client.dao.KhuVucDAO;

public class AreaListLoader extends CustomAsyncTaskLoader<Cursor> {
    
    public AreaListLoader(Activity context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return KhuVucDAO.getInstance().cursorAll();
    }
}
