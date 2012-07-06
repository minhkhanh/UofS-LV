package emenu.client.menu.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import emenu.client.menu.R;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.dto.ChiTietNhomBan;

@Deprecated
public class TableGroupsAdapter extends CustomArrayAdapter<ChiTietNhomBan> {
    
    public static final int VIEW_TYPE_NEW_GROUP = 0;
    public static final int VIEW_TYPE_EXISTING_GROUP = 1;

    public TableGroupsAdapter(Context context, List<ChiTietNhomBan> data) {
        super(context, data);
    }
    
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_NEW_GROUP;
        }
        
        return VIEW_TYPE_EXISTING_GROUP;
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public ChiTietNhomBan getItem(int position) {
        if (position == 0) {
            return null;
        }

        return super.getItem(position - 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckedTextView v;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = (CheckedTextView) inflater.inflate(
                    R.layout.simple_list_item_single_choice, null);
        } else {
            v = (CheckedTextView) convertView;
        }

        String text = "";
        if (position == 0) {
            text = getContext().getString(R.string.caption_create_new_table_group);
        } else {
            List<BanDTO> listBanPhu = getItem(position).getListBanPhu();
            for (int i = 0; i < listBanPhu.size(); ++i) {
                BanDTO ban = listBanPhu.get(i);
                text += ban.getTenBan();
                text += ",";
            }
        }

        // TextView t = new TextView(getContext());
        // t.setText(text);

        v.setText(text);

        return v;
    }
}
