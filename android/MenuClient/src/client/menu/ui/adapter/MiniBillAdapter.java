package client.menu.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import client.menu.R;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;

public class MiniBillAdapter extends BillItemsAdapter {

    static class ViewHolder {
        public TextView dishName;
        public TextView unitName;
        public TextView unitPrice;
        public TextView dishQuantity;
        public TextView itemTotal;
    }

    public MiniBillAdapter(Activity activity, List<ContentValues> data) {
        super(activity, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = mActivity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_mini_bill, null);
            holder = new ViewHolder();
            holder.dishName = (TextView) convertView.findViewById(R.id.textDishName);
            holder.unitName = (TextView) convertView.findViewById(R.id.textUnitName);
            holder.unitPrice = (TextView) convertView.findViewById(R.id.textUnitPrice);
            holder.dishQuantity = (TextView) convertView.findViewById(R.id.textQuantity);
            holder.itemTotal = (TextView) convertView.findViewById(R.id.textItemTotal);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Integer quantity = mData.get(position).getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
        Integer price = mData.get(position).getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);

        holder.dishName.setText(mData.get(position).getAsString(
                MonAnDaNgonNguDTO.CL_TEN_MON));
        holder.unitName.setText(mData.get(position).getAsString(
                DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));
        holder.unitPrice.setText(price.toString());
        holder.dishQuantity.setText(quantity.toString());
        holder.itemTotal.setText(String.valueOf(quantity * price));

        return convertView;
    }
}
