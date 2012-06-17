package client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import client.menu.R;
import client.menu.db.dto.OrderDTO;
import client.menu.ui.view.BriefOrderItemView;
import client.menu.ui.view.CheckableLayout;
import client.menu.ui.view.SingleChoiceGroup;

public class BriefOrderListAdapter extends
        CustomExpandableListAdapter<OrderDTO, ContentValues> {

    SingleChoiceGroup mGroup;

    class GroupHolder {
        TextView mOrderTitle;
    }

    public BriefOrderListAdapter(Context context, List<OrderDTO> groupData) {
        super(context, groupData);
        
        mGroup = new SingleChoiceGroup(0);
    }
    
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        BriefOrderItemView view;
        if (convertView == null) {
            view = new BriefOrderItemView(getContext());
        } else {
            view = (BriefOrderItemView) convertView;
        }

        view.bindData(getChild(groupPosition, childPosition));

        return view;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            CheckableLayout groupView = new CheckableLayout(getContext(),
                    R.layout.item_brief_order_list, R.id.cmarkOrderSelection);
            groupView.joinGroup(mGroup);

            holder = new GroupHolder();
            holder.mOrderTitle = (TextView) groupView.findViewById(R.id.textOrderTitle);

            groupView.setTag(holder);

            convertView = groupView;
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        if (groupPosition == 0) {
            holder.mOrderTitle.setText(R.string.text_create_new_order);
        } else {
            holder.mOrderTitle.setText(getContext().getString(R.string.sub_order_no)
                    + " " + groupPosition);
        }

        return convertView;
    }

}
