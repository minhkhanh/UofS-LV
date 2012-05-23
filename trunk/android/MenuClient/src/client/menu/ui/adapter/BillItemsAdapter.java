package client.menu.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.ui.view.BillItemView;

public class BillItemsAdapter extends BaseAdapter {

    private List<ContentValues> mData;
    private Context mContext;

    public BillItemsAdapter(Context context, List<ContentValues> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mData.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BillItemView v = (BillItemView) convertView;
        if (v == null) {
            v = new BillItemView(mContext);
        }
        v.bindData(mData.get(position));

        return v;
    }

    public int getBillTotal() {
        int total = 0;

        for (ContentValues c : mData) {
            int quantity = c.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
            int price = c.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);

            total += quantity * price;
        }

        return total;
    }

    public List<ContentValues> getData() {
        return mData;
    }

    public Context getContext() {
        return mContext;
    }
}
