package emenu.client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhDaNgonNguDTO;
import emenu.client.db.dto.DonViTinhMonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;

public class MiniBillAdapter extends BillAdapter {
    
    static class ViewHolder {
        public TextView dishName;
        public TextView unitName;
        public TextView unitPrice;
        public TextView dishQuantity;
        public TextView itemTotal;
    }

    public MiniBillAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
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

        Integer quantity = getData().get(position).getAsInteger(
                ChiTietOrderDTO.CL_SO_LUONG);
        Integer price = getData().get(position)
                .getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);

        holder.dishName.setText(getData().get(position).getAsString(
                MonAnDaNgonNguDTO.CL_TEN_MON));
        holder.unitName.setText(getData().get(position).getAsString(
                DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));
        holder.unitPrice.setText(price.toString());
        holder.dishQuantity.setText(quantity.toString());
        holder.itemTotal.setText(String.valueOf(quantity * price));

        return convertView;
    }
}
