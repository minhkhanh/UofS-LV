package client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import client.menu.R;
import client.menu.db.dto.OrderDTO;
import client.menu.ui.view.BriefOrderItemView;

public class BriefOrderListAdapter extends
        CustomExpandableListAdapter<OrderDTO, ContentValues> {

    class GroupHolder {
        TextView mText1;
    }

    public BriefOrderListAdapter(Context context, List<OrderDTO> groupData) {
        super(context, groupData);
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
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(
                    android.R.layout.simple_expandable_list_item_1, null);

            holder = new GroupHolder();
            holder.mText1 = (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

//        holder.mText1.setBackgroundResource(R.drawable.activated_background);
        holder.mText1.setText(getContext().getString(R.string.sub_order_no) + " "
                + (groupPosition + 1));

        return convertView;
        // TextView view = new TextView(getContext());
        // view.setBackgroundResource(R.drawable.activated_background);
        // view.setText(getContext().getString(R.string.sub_order_no) + " "
        // + (groupPosition + 1));
        //
        // return view;
    }

}
