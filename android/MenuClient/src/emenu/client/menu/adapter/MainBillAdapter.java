package emenu.client.menu.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhMonAnDTO;
import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.menu.view.BillItemView;

public class MainBillAdapter extends BillAdapter {

    public MainBillAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BillItemView v = (BillItemView) convertView;
        if (v == null) {
            v = new BillItemView(getContext());
        }
        v.bindData(getData().get(position));

        return v;
    }
}
