package emenu.client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import emenu.client.menu.ui.view.OrderedItemView;

public class OrderedAdapter extends OrderAdapter {

    public OrderedAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderedItemView v = null;
        if (convertView == null) {
            v = new OrderedItemView(getContext());
        } else {
            v = (OrderedItemView) convertView;
        }

        v.bindData(getItem(position));

        return v;
    }
}
