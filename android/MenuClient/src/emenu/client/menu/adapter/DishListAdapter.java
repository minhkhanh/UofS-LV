package emenu.client.menu.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import emenu.client.menu.view.BriefDishView;

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
        BriefDishView v;
        if (convertView == null) {
            v = new BriefDishView(getContext());
        } else {
            v = (BriefDishView) convertView;
        }

        v.bindData(getItem(position));

        return v;
    }
}
