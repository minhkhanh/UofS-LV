package emenu.client.menu.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;
import emenu.client.dao.MonAnDAO;
import emenu.client.menu.view.BriefDishView;

public class DishListAdapter extends CustomArrayAdapter<ContentValues> implements
        Filterable {

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

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<ContentValues> list = (List<ContentValues>) results.values;
                if (list != null) {
                    clear();
                    addAll(list);
                    notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<ContentValues> list = MonAnDAO.getInstance().contentByNameFilter(1,
                        constraint.toString());
                results.values = list;

                return results;
            }
        };
    }
}
