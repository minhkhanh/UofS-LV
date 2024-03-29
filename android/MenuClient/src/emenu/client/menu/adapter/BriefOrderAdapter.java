package emenu.client.menu.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.db.dto.OrderDTO;
import emenu.client.menu.view.BriefOrderChildView;
import emenu.client.menu.view.BriefOrderGroupView;
import emenu.client.menu.view.CheckMarkView;
import emenu.client.menu.view.CheckableLayout;
import emenu.client.menu.view.SingleChoiceGroup;

public class BriefOrderAdapter extends
        CustomExpandableListAdapter<OrderDTO, ContentValues> {

    SingleChoiceGroup mGroup;

    public BriefOrderAdapter(Context context, List<OrderDTO> groupData) {
        super(context, groupData);

        mGroup = new SingleChoiceGroup(-1);
    }
    
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        BriefOrderChildView view;
        if (convertView == null) {
            view = new BriefOrderChildView(getContext());
        } else {
            view = (BriefOrderChildView) convertView;
        }

        view.bindData(getChild(groupPosition, childPosition));

        return view;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        BriefOrderGroupView view = null;
        if (convertView == null) {
            view = new BriefOrderGroupView(getContext());
            view.joinGroup(mGroup);
        } else {
            view = (BriefOrderGroupView) convertView;
        }

        view.bindData(groupPosition, getGroup(groupPosition));

        return view;
    }

}
