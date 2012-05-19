package client.menu.ui.adapter;

import java.util.List;

import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.ui.view.OrderItemView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class OrderItemsAdapter extends BaseAdapter {
    List<ChiTietOrderDTO> mChiTietOrderList;

    List<MonAnDaNgonNguDTO> mMonAnDaNgonNguList;
    List<DonViTinhDaNgonNguDTO> mDonViTinhDaNgonNguList;
    List<DonViTinhMonAnDTO> mDonViTinhMonAnList;

    Context mContext;

    public OrderItemsAdapter(Context context, List<MonAnDaNgonNguDTO> monAnDaNgonNguList,
            List<DonViTinhDaNgonNguDTO> donViTinhDaNgonNguList,
            List<DonViTinhMonAnDTO> donViTinhMonAnList,
            List<ChiTietOrderDTO> chiTietOrderList) {
        mMonAnDaNgonNguList = monAnDaNgonNguList;
        mDonViTinhDaNgonNguList = donViTinhDaNgonNguList;
        mDonViTinhMonAnList = donViTinhMonAnList;
        mContext = context;
        mChiTietOrderList = chiTietOrderList;
    }

    @Override
    public int getCount() {
        return mChiTietOrderList.size();
    }

    @Override
    public Object getItem(int position) {
        return mChiTietOrderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderItemView v = (OrderItemView) convertView;

        if (v == null) {
            v = new OrderItemView(mContext);
        }

        v.bindData(mChiTietOrderList.get(position), mMonAnDaNgonNguList.get(position),
                mDonViTinhDaNgonNguList.get(position), mDonViTinhMonAnList.get(position));

        return v;
    }
}
