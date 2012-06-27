package client.menu.ui.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.view.ExpandableCategoryList2;

@Deprecated
public class ExpandableCategoryAdapter2 extends
        CustomExpandableListAdapter<DanhMucDaNgonNguDTO, Object> {

    private List<Integer> mGroupItemHeights = new ArrayList<Integer>();

    private Map<Integer, List<DanhMucDaNgonNguDTO>> mChildRealData = new HashMap<Integer, List<DanhMucDaNgonNguDTO>>();

    public ExpandableCategoryAdapter2(Context context, List<DanhMucDaNgonNguDTO> groupData) {
        super(context, groupData);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        ExpandableCategoryList2 list = new ExpandableCategoryList2(getContext());
        List<DanhMucDaNgonNguDTO> data = new ArrayList<DanhMucDaNgonNguDTO>();
        data.add(mChildRealData.get(groupPosition).get(childPosition));
        ExpandableCategoryAdapter2 adapter = new ExpandableCategoryAdapter2(getContext(),
                mChildRealData.get(groupPosition));
        list.setExpandableCategoryAdapter2(adapter);
        // list.setScrollContainer(false);
        list.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                39 * mChildRealData.get(groupPosition).size()));
        list.setPadding(50, 0, 0, 0);

        return list;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        TextView groupName = new TextView(getContext());
        groupName.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        groupName.setPadding(40, 10, 10, 10);
        // groupName.setGravity(Gravity.CENTER);
        groupName.setText(getGroup(groupPosition).getTenDanhMuc());

        return groupName;
    }

    public void addExpandableChild(int groupPosition, List<DanhMucDaNgonNguDTO> data) {
        addChild(groupPosition, null);
        mChildRealData.put(groupPosition, new ArrayList<DanhMucDaNgonNguDTO>());
        mChildRealData.get(groupPosition).addAll(data);
    }
}
