package client.menu.ui.adapter;

import java.util.List;

import client.menu.R;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.ui.view.DishItemView;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class DishListAdapter extends CustomArrayAdapter<ContentValues> {

    static class ViewHolder {
        TextView mName;
        TextView mDescript;
        RatingBar mRate;
    }

    public DishListAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DishItemView v;
        if (convertView == null) {
            v = new DishItemView(getContext());
        } else {
            v = (DishItemView) convertView;
        }

        v.bindData(getItem(position));

        return v;
    }

    public int indexOf(Integer maMonAn) {
        for (int i = 0; i < getCount(); ++i) {
            ContentValues c = getItem(i);
            if (c.getAsInteger(MonAnDTO.CL_MA_MON_AN) == maMonAn) {
                return i;
            }
        }

        return -1;
    }
}
