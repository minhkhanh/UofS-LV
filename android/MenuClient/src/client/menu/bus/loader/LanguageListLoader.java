package client.menu.bus.loader;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import client.menu.db.dao.NgonNguDAO;

public class LanguageListLoader extends CustomAsyncTaskLoader<Cursor> {

    public LanguageListLoader(Activity context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return NgonNguDAO.getInstance().cursorAll();
    }
}
