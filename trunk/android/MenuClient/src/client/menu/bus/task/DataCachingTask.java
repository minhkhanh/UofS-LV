package client.menu.bus.task;

import client.menu.dao.AbstractDAO;
import android.app.ProgressDialog;
import android.content.Context;

public class DataCachingTask extends DataPreparingTask {

    public DataCachingTask(ProgressDialog dlg) {
        super(dlg);
    }

    @Override
    protected boolean workCore(AbstractDAO dao) {
        return dao.loadCachedData();
    }
}