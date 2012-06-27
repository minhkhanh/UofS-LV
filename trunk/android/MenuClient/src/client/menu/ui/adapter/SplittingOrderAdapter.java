package client.menu.ui.adapter;

import java.util.List;

import client.menu.ui.view.BriefOrderChildView;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class SplittingOrderAdapter extends CustomArrayAdapter<ContentValues> {

    public SplittingOrderAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BriefOrderChildView view;
        if (convertView == null) {
            view = new BriefOrderChildView(getContext());
        } else {
            view = (BriefOrderChildView) convertView;
        }

        view.bindData(getItem(position));

        return view;
    }

}
