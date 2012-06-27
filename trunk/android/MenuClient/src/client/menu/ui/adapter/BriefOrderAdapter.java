package client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import client.menu.R;
import client.menu.db.dto.OrderDTO;
import client.menu.ui.view.BriefOrderChildView;
import client.menu.ui.view.BriefOrderGroupView;
import client.menu.ui.view.CheckMarkView;
import client.menu.ui.view.CheckableLayout;
import client.menu.ui.view.SingleChoiceGroup;

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
