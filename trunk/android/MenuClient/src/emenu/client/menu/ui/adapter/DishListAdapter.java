package emenu.client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import emenu.client.menu.ui.view.DishItemView;

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
}
