package client.menu.ui.adapter;

import java.util.List;

import client.menu.ui.view.DishUnitItemView;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class DishUnitsAdapter extends CustomArrayAdapter<ContentValues> {

    public DishUnitsAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DishUnitItemView v;
        if (convertView == null) {
            v = new DishUnitItemView(getContext());
        } else {
            v = (DishUnitItemView) convertView;
        }

        v.bindData(getItem(position));

        return v;
    }

}
