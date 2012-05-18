package client.menu.ui.adapter;

import java.util.List;

import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.ui.view.OrderItemView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class OrderItemsAdapter extends BaseAdapter {
    List<ChiTietOrderDTO> mData;
    Context mContext;
    
    public OrderItemsAdapter(Context context, List<ChiTietOrderDTO> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = new OrderItemView(mContext, mData.get(position));
        }
        
        return v;
    }

}
