package emenu.client.menu.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.db.dto.NgonNguDTO;

public class LanguageListAdapter extends CustomArrayAdapter<NgonNguDTO> {

    static class ViewHolder {
        public TextView mLangName;
        public ImageView mFlag;
    }
    
    public LanguageListAdapter(Context context, List<NgonNguDTO> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            
            convertView = inflater.inflate(R.layout.item_language_list, null);
            holder = new ViewHolder();
            holder.mLangName = (TextView) convertView.findViewById(R.id.textLanguageName);
            holder.mFlag = (ImageView) convertView.findViewById(R.id.imgFlag);
            
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();    
        }
        
        holder.mLangName.setText(getItem(position).getTenNgonNgu());
        
        if (getItem(position).getKiHieu().compareTo("vi") == 0) {
            holder.mFlag.setImageResource(R.drawable.vietnam_flag_icon);
        } else if (getItem(position).getKiHieu().compareTo("en") == 0) {
            holder.mFlag.setImageResource(R.drawable.united_kingdom_flag_icon);
        } else if (getItem(position).getKiHieu().compareTo("ja") == 0) {
            holder.mFlag.setImageResource(R.drawable.japan_flag_icon);
        } 

        return convertView;
    } 
}
