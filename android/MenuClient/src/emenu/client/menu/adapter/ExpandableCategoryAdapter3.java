package emenu.client.menu.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.menu.view.ExpandableCategoryList3;
import emenu.client.menu.view.ExpandableCategoryView;

@Deprecated
public class ExpandableCategoryAdapter3 extends CustomArrayAdapter<DanhMucDaNgonNguDTO> {

    ExpandableCategoryList3 mListView;

    public ExpandableCategoryAdapter3(Context context, List<DanhMucDaNgonNguDTO> data) {
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

        view.bindData(getItem(position));

        return view;
    }

    public ExpandableCategoryList3 getListView() {
        return mListView;
    }

    public void setListView(ExpandableCategoryList3 listView) {
        mListView = listView;
    }

}
