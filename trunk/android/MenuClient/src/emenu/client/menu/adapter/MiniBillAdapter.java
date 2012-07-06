package emenu.client.menu.adapter;

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

public class MiniBillAdapter extends BillAdapter implements IDishSwappableAdapter {

    static class ViewHolder {
        public TextView dishName;
        public TextView unitName;
        public TextView unitPrice;
        public TextView dishQuantity;
        public TextView itemTotal;
        public TextView itemProm;
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
            holder.itemProm = (TextView) convertView.findViewById(R.id.textItemProm);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContentValues item = getItem(position);

        int quantity = getItem(position).getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
        int price = getData().get(position).getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);
        int totalWithoutProm = quantity * price;
        int itemProm = calcItemProm(totalWithoutProm, item);

        holder.dishName.setText(getItem(position).getAsString(
                MonAnDaNgonNguDTO.CL_TEN_MON));
        holder.unitName.setText(getItem(position).getAsString(
                DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));
        holder.unitPrice.setText(price + "");
        holder.dishQuantity.setText(quantity + "");
        holder.itemProm.setText("-" + itemProm);
        holder.itemTotal.setText((totalWithoutProm - itemProm) + "");

        return convertView;
    }
}
