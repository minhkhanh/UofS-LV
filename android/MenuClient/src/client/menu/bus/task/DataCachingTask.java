package client.menu.bus.task;

import client.menu.dao.AbstractDAO;
import android.content.Context;

public class DataCachingTask extends DataPreparingTask {

    public DataCachingTask(Context context) {
        super(context);
    }

    @Override
    protected boolean workCore(AbstractDAO dao) {
        return dao.loadCachedData();
    }
}
