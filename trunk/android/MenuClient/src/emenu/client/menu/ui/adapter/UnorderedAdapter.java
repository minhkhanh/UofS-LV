package emenu.client.menu.ui.adapter;

import java.util.List;

import emenu.client.menu.ui.view.UnorderedItemView;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class UnorderedAdapter extends OrderAdapter {

    public UnorderedAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UnorderedItemView v = null;
        if (convertView == null) {
            v = new UnorderedItemView(getContext());
        } else {
            v = (UnorderedItemView) convertView;
        }

        v.bindData(getItem(position));

        return v;
    }

}
