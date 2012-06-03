package client.menu.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.view.ExpandableCategoryList;
import client.menu.ui.view.ExpandableCategoryView;

public class ExpandableCategoryListAdapter extends
        CustomArrayAdapter<DanhMucDaNgonNguDTO> {
    
    ExpandableCategoryList mListView;

    public ExpandableCategoryListAdapter(Context context, List<DanhMucDaNgonNguDTO> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExpandableCategoryView view;
        if (convertView == null) {
            view = new ExpandableCategoryView(getContext(), mListView);
        } else {
            view = (ExpandableCategoryView) convertView;
        }

        view.bindData(getData().get(position));
        
        return view;
    }

    public ExpandableCategoryList getListView() {
        return mListView;
    }

    public void setListView(ExpandableCategoryList listView) {
        mListView = listView;
    }

    
}
