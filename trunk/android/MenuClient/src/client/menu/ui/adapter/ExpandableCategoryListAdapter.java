package client.menu.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.view.ExpandableCategoryView;

public class ExpandableCategoryListAdapter extends CustomArrayAdapter<DanhMucDaNgonNguDTO> {

    public ExpandableCategoryListAdapter(Context context, List<DanhMucDaNgonNguDTO> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExpandableCategoryView view;
        if (convertView == null) {
            view = new ExpandableCategoryView(getContext());
        } else {
            view = (ExpandableCategoryView) convertView;
        }

        view.bindData(getData().get(position));

        return view;
    }

}
