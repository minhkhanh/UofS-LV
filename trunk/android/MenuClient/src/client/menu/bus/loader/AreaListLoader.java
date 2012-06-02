package client.menu.bus.loader;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import client.menu.dao.KhuVucDAO;

public class AreaListLoader extends CustomAsyncTaskLoader<Cursor> {
    
    public AreaListLoader(Activity context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return KhuVucDAO.getInstance().cursorAll();
    }
}
