package emenu.client.bus.loader;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;

public class GetOrderItemsLoader extends CustomAsyncTaskLoader<List<ContentValues>> {

    public GetOrderItemsLoader(Context context) {
        super(context);
    }

    @Override
    public List<ContentValues> loadInBackground() {
        
        
        return null;
    }

}
