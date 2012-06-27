package client.menu.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import client.menu.R;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.fragment.CategoryListFragment.CategoryNode;

public class ExpandableCategoryAdapter extends CustomArrayAdapter<CategoryNode> {

    class ViewHolder {
        TextView mCategoryName;
    }

    List<CategoryNode> mTreeData = new ArrayList<CategoryNode>();

    public ExpandableCategoryAdapter(Context context, List<CategoryNode> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.frame_category_item, null);

            TextView categoryName = (TextView) convertView
                    .findViewById(R.id.textCategoryName);
            holder = new ViewHolder();
            holder.mCategoryName = categoryName;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
//        convertView.setBackgroundResource(R.drawable.activated_background);
        
        holder.mCategoryName.setPadding(getItem(position).indent * 20 + 10, 0, 0, 0);
        holder.mCategoryName.setText(getItem(position).danhMuc.getTenDanhMuc());

        return convertView;
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
        getItem(position).state = mTreeData.get(treePosition).state = CategoryNode.EXPANDED;
        
        int markedIndent = -1;
        for (int i = treePosition + 1; i < mTreeData.size(); ++i) {
            int treeIndent = mTreeData.get(i).indent;

            if (treeIndent <= mTreeData.get(treePosition).indent) {
                break;
            } else if (treeIndent > mTreeData.get(treePosition).indent) {
                if (mTreeData.get(i).state == CategoryNode.COLLAPSED) {
                    markedIndent = treeIndent;
                } else if (markedIndent != -1) {
                    if (treeIndent > markedIndent) {
                        continue;
                    } else {
                        markedIndent = -1;
                    }
                }

                CategoryNode node = mTreeData.get(i);
                position += 1;
                add(position, node);
            }
        }
    }

    public void expand(int position, int treePosition, List<DanhMucDaNgonNguDTO> addition) {
        getItem(position).state = mTreeData.get(treePosition).state = CategoryNode.EXPANDED;
        
        int indent = getItem(position).indent + 1;
        for (int i = 0; i < addition.size(); ++i) {
            CategoryNode node = new CategoryNode();
            node.danhMuc = addition.get(i);
            node.indent = indent;
            add(i + position + 1, node);

            mTreeData.add(i + treePosition + 1, node);
        }
    }

    public void collapse(int position, int treePosition) {
        for (int i = position + 1; i < getCount();) {
            if (getItem(i).indent <= getItem(position).indent) {
                break;
            } else {
                remove(i);
            }
        }

        getItem(position).state = mTreeData.get(treePosition).state = CategoryNode.COLLAPSED;
    }

    public void setTreeData(List<CategoryNode> treeData) {
        mTreeData = treeData;
    }
}
