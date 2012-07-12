package emenu.client.menu.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.menu.fragment.CategoryListFragment.CategoryNode;

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

        // convertView.setBackgroundResource(R.drawable.activated_background);

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
        CategoryNode parent = mTreeData.get(treePosition);

        int markedIndent = -1;
        for (int i = treePosition + 1; i < mTreeData.size(); ++i) {
            CategoryNode item = mTreeData.get(i);

            if (item.indent <= parent.indent) {
                break; // is not a child
            } else {
                if (markedIndent == -1) {
                    if (item.state == CategoryNode.COLLAPSED)
                        markedIndent = item.indent; // parent node of collapsed
                                                    // sub tree
                } else {
                    if (item.indent > markedIndent)
                        continue; // child node of collapsed sub tree
                    else
                        markedIndent = -1; // out of collapsed sub tree
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

    public List<CategoryNode> createCompleteTree() {
        List<CategoryNode> tree = new ArrayList<CategoryNode>();
        int markedIndent = -1;
        for (CategoryNode node : mTreeData) {
            if (markedIndent == -1) {
                if (node.state == CategoryNode.COLLAPSED)
                    markedIndent = node.indent; // parent node of collapsed sub
                                                // tree
            } else {
                if (node.indent > markedIndent)
                    continue; // child in collapsed sub tree
                else
                    markedIndent = -1; // out of collapsed sub tree
            }

            tree.add(node);
        }

        return tree;
    }

    public void setTreeData(List<CategoryNode> treeData) {
        clear();
        mTreeData = treeData;
        addAll(treeData);
    }
}
