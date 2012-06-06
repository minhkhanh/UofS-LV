package client.menu.ui.view;

import client.menu.R;
import client.menu.ui.adapter.ExpandableCategoryAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

public class ExpandableCategoryList extends ListView {

    private ExpandableCategoryView mFocusedItem;
//    private LinearLayout mContainer;

    public interface OnCategoryClickListener {
        void onCategoryItemClick(ExpandableCategoryList list, ExpandableCategoryView item);
    }

    OnCategoryClickListener mOnCategoryClickListener;
    
//    OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//            
//        }
//    };

    public ExpandableCategoryList(Context context) {
        super(context);
//        setOnItemClickListener(mOnItemClickListener);
//        prepareViews();
    }

    public ExpandableCategoryList(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setOnItemClickListener(mOnItemClickListener);
//        if (!isInEditMode()) {
//            prepareViews();
//        }
    }

    public ExpandableCategoryList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        setOnItemClickListener(mOnItemClickListener);
//        if (!isInEditMode()) {
//            prepareViews();
//        }
    }

//    private void prepareViews() {
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
//                Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.frame_category_list, this);
//        mContainer = (LinearLayout) v.findViewById(R.id.linearContainer);
//    }

//    public void addCategory(ExpandableCategoryView view) {
//        view.setRoot(this);
//        mContainer.addView(view);        
//    }
    
    public void setExpandableCategoryAdapter(ExpandableCategoryAdapter adapter) {
        adapter.setListView(this);
        setAdapter(adapter);
    }
    
//    @Override
//    public void setAdapter(ListAdapter adapter) {
//        throw new UnsupportedOperationException("Use setCategoryAdapter() instead.");
//    }

    public void setOnCategoryClickListener(
            OnCategoryClickListener onCategoryItemClickListener) {
        mOnCategoryClickListener = onCategoryItemClickListener;
    }

    public OnCategoryClickListener getOnCategoryClickListener() {
        return mOnCategoryClickListener;
    }

    public ExpandableCategoryView getFocusedItem() {
        return mFocusedItem;
    }

    public void setFocusedItem(ExpandableCategoryView focusedItem) {
        mFocusedItem = focusedItem;
    }
}
