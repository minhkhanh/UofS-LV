package emenu.client.menu.ui.view;

import java.util.List;

import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.menu.bus.task.CustomAsyncTask;
import emenu.client.menu.bus.task.LoadChildCategoryListTask;
import emenu.client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.menu.ui.adapter.ExpandableCategoryAdapter2;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

@Deprecated
public class ExpandableCategoryList2 extends ExpandableListView implements
        OnPostExecuteListener<Integer, Void, List<DanhMucDaNgonNguDTO>> {

    ExpandableCategoryAdapter2 mAdapter;

    public int getTotalHeight() {
        int total = 0;
        for (int i = 0; i < getChildCount(); ++i) {
            View v = getChildAt(i);
            if (v instanceof ExpandableCategoryList2) {
                ExpandableCategoryList2 list = (ExpandableCategoryList2) v;
                total += list.getTotalHeight();
            } else {
                total += v.getHeight();
            }
        }

        return total;
    }
    
//    Ongroup

    public void expand(int count) {
        if ((getParent() instanceof ExpandableCategoryList2)) {
            ViewGroup.LayoutParams lp = getLayoutParams();
            lp.height += 39 * count;
            setLayoutParams(lp);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int h = getTotalHeight();
//        if (h > 0)
//            setMeasuredDimension(widthMeasureSpec, h);
//        else
            
    }

    // OnHierarchyChangeListener

    private OnGroupExpandListener mOnGroupExpandListener = new OnGroupExpandListener() {
        @Override
        public void onGroupExpand(int groupPosition) {
            Bundle extras = new Bundle();
            extras.putInt("groupPosition", groupPosition);

            DanhMucDaNgonNguDTO danhMucCha = mAdapter.getGroup(groupPosition);
            LoadChildCategoryListTask task = new LoadChildCategoryListTask();
            task.setOnPostExecuteListener(ExpandableCategoryList2.this);
            task.setExtras(extras);
            task.execute(danhMucCha.getMaDanhMuc());
        }
    };

    public ExpandableCategoryList2(Context context) {
        super(context);
        setOnGroupExpandListener(mOnGroupExpandListener);
        setSmoothScrollbarEnabled(false);
    }

    public ExpandableCategoryList2(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnGroupExpandListener(mOnGroupExpandListener);
    }

    public ExpandableCategoryList2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnGroupExpandListener(mOnGroupExpandListener);
    }

    public void setExpandableCategoryAdapter2(ExpandableCategoryAdapter2 adapter) {
        setAdapter(adapter);
        mAdapter = adapter;
    }

    @Override
    public void onPostExecute(
            CustomAsyncTask<Integer, Void, List<DanhMucDaNgonNguDTO>> task,
            List<DanhMucDaNgonNguDTO> result) {
        Bundle ex = task.getExtras();
        int groupPosition = ex.getInt("groupPosition");
        if (result.size() > 0) {

            mAdapter.addExpandableChild(groupPosition, result);
            mAdapter.notifyDataSetChanged();

             expand(result.size());

        } else {
            collapseGroup(groupPosition);
        }
    }
}
