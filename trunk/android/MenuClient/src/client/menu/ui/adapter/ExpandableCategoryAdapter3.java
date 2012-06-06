package client.menu.ui.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import android.widget.TextView;
import android.widget.AbsListView.LayoutParams;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.fragment.CategoryListFragment.CategoryNode;

public class ExpandableCategoryAdapter3 extends CustomArrayAdapter<CategoryNode> {

    List<CategoryNode> mTreeData = new ArrayList<CategoryNode>();

    public ExpandableCategoryAdapter3(Context context, List<CategoryNode> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView groupName = new TextView(getContext());
        // groupName.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        // LayoutParams.WRAP_CONTENT));
        // groupName.setPadding(40, 10, 10, 10);
        groupName.setText(getItem(position).danhMuc.getTenDanhMuc());

        groupName.setPadding(getItem(position).indent * 10, 0, 0, 0);

        return groupName;
    }

    public int getTreePosition(Integer maDanhmuc) {
        for (int i = 0; i < mTreeData.size(); ++i) {
            CategoryNode node = mTreeData.get(i);
            if (node.danhMuc.getMaDanhMuc() == maDanhmuc) {
                return i;
            }
        }

        return -1;
    }

    public boolean isChildrenLoaded(int treePosition) {
        return (treePosition < mTreeData.size() - 1 && mTreeData.get(treePosition).indent < mTreeData
                .get(treePosition + 1).indent);
    }

    public boolean isExpanded(int position) {
        return (position < getCount() - 1 && getItem(position).indent < getItem(position + 1).indent);
    }

    public void expand(int position, int treePosition) {
        for (int i = treePosition + 1; i < mTreeData.size(); ++i) {
            if (mTreeData.get(i).indent <= mTreeData.get(treePosition).indent) {
                break;
            } else {
                CategoryNode node = mTreeData.get(i);
                position += 1;
                add(position, node);
            }
        }
    }

    public void expand(int position, int treePosition, List<DanhMucDaNgonNguDTO> addition) {
        int indent = getItem(position).indent + 1;
        for (int i = 0; i < addition.size(); ++i) {
            CategoryNode node = new CategoryNode();
            node.danhMuc = addition.get(i);
            node.indent = indent;
            add(i + position + 1, node);

            mTreeData.add(i + treePosition + 1, node);
        }

    }

    // public boolean getState(int position) {
    // return (position < getCount() - 1 && getIndent(position) <
    // getIndent(position + 1));
    // }

    public void collapse(int position) {
        for (int i = position + 1; i < getCount(); ) {
            if (getItem(i).indent <= getItem(position).indent) {
                break;
            } else {
                remove(i);
            }
        }
    }

    public void setTreeData(List<CategoryNode> treeData) {
        mTreeData = treeData;
    }
}
