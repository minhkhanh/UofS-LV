package client.menu.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import client.menu.R;
import client.menu.db.dto.BanDTO;

public class TableListAdapter extends CustomArrayAdapter<BanDTO> {

    static class ViewHolder {
        public TextView mName;
        public ImageView mImage;
    }

    public TableListAdapter(Context context, List<BanDTO> data) {
        super(context, data);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_table_grid, null);

            holder = new ViewHolder();
            holder.mName = (TextView) convertView.findViewById(R.id.textTableName);
            holder.mImage = (ImageView) convertView.findViewById(R.id.imgTableImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BanDTO ban = getData().get(pos);
        if (ban.getMaBanChinh() == null) {
            holder.mName.setText(ban.getTenBan());    
        } else {
            holder.mName.setText(ban.getTenBan() + " - Nh�m " + ban.getMaBanChinh());
        }
        
        if (ban.getActive()) {
            holder.mImage.setImageResource(R.drawable.alarm_tick_icon);
        } else if (!ban.getActive()) {
            holder.mImage.setImageResource(R.drawable.alarm_private_icon);
        }

        return convertView;
    }

}
