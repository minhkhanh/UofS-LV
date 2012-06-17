package client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import client.menu.ui.view.OrderItemView;

public class OrderItemsAdapter extends CustomArrayAdapter<ContentValues> {

    public OrderItemsAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderItemView v = (OrderItemView) convertView;

        if (v == null) {
            v = new OrderItemView(getContext());
        }

        v.bindData(getItem(position));

        return v;
    }
}
