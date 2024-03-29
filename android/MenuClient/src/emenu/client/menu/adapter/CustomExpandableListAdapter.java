package emenu.client.menu.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public abstract class CustomExpandableListAdapter<G, C> extends BaseExpandableListAdapter {

    protected List<G> mGroupData;
    protected List<List<C>> mChildData;
    
    protected Context mContext;

    public CustomExpandableListAdapter(Context context, List<G> groupData) {
        mContext = context;
//        generateChildren();
        mGroupData = groupData;
        mChildData = new ArrayList<List<C>>();
        for (int i = 0; i < mGroupData.size(); ++i) {
            mChildData.add(new ArrayList<C>());
        }
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return mChildData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public abstract View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent);

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildData.get(groupPosition).size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return mGroupData.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mGroupData.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public abstract View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent);

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void addChild(int groupPosition, C newChild) {
        mChildData.get(groupPosition).add(newChild);
    }

    public void addChildAll(int groupPosition, Collection<? extends C> childData) {
        mChildData.get(groupPosition).addAll(childData);
    }
    
    public void addGroup(int position, G newGroup) {
        mGroupData.add(position, newGroup);
        mChildData.add(position, new ArrayList<C>());
    }

    public void addGroup(G newGroup) {
        mGroupData.add(newGroup);
        mChildData.add(new ArrayList<C>());
    }

    public void addGroupAll(Collection<? extends G> groupData) {
        mGroupData.addAll(groupData);
        for (int i = 0; i < groupData.size(); ++i) {
            mChildData.add(new ArrayList<C>());
        }
    }

    public void clearChildren(int groupPosition) {
        mChildData.get(groupPosition).clear();
    }

    public void clearGroup() {
        for (int i = 0; i < mGroupData.size(); ++i) {
            mChildData.get(i).clear();
        }
        mChildData.clear();
        mGroupData.clear();
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

}
