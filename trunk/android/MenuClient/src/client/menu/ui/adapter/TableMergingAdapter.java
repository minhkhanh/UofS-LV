package client.menu.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import client.menu.R;
import client.menu.db.dto.BanDTO;

public class TableMergingAdapter extends CustomArrayAdapter<BanDTO> {

    static class ViewHolder {
        public CheckedTextView mTable;
    }

    public TableMergingAdapter(Context context, List<BanDTO> data) {
        super(context, data);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup arg2) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_table_merging_adapter, null);

            holder = new ViewHolder();
            holder.mTable = (CheckedTextView) convertView.findViewById(R.id.cktxtTable);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BanDTO ban = getData().get(pos);
        holder.mTable.setText(ban.getTenBan());
        if (ban.getActive()) {
            holder.mTable.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.alarm_tick_icon, 0, 0);
        } else if (!ban.getActive()) {
            holder.mTable.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.alarm_private_icon, 0, 0);
        }

        convertView.setFocusable(false);
        holder.mTable.setFocusable(false);
        return convertView;
    }

}
