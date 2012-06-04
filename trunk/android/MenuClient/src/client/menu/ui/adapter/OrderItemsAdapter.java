package client.menu.ui.adapter;

import java.util.Collection;
import java.util.List;

import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.ui.view.OrderItemView;
import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class OrderItemsAdapter extends CustomArrayAdapter<ContentValues>{
    List<ChiTietOrderDTO> mChiTietOrderList;

    public OrderItemsAdapter(Context context, List<ContentValues> data,
            List<ChiTietOrderDTO> chiTietOrderList) {
        super(context, data);
        mChiTietOrderList = chiTietOrderList;
    }
    
    @Override
    public void clear() {
        super.clear();
        
        mChiTietOrderList.clear();
    }
    
    public void addAllExtra(Collection<ChiTietOrderDTO> addition) {
        mChiTietOrderList.addAll(addition);
    }
    
    public void addExtra(ChiTietOrderDTO object) {
        mChiTietOrderList.add(object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderItemView v = (OrderItemView) convertView;

        if (v == null) {
            v = new OrderItemView(getContext());
        }

        v.bindData(mChiTietOrderList.get(position), getItem(position));

        return v;
    }
}
