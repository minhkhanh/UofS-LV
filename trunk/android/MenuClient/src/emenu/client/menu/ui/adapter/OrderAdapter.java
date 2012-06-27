package emenu.client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;

public abstract class OrderAdapter extends CustomArrayAdapter<ContentValues> {

    public OrderAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

}
