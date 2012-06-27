package emenu.client.menu.ui.view;

import emenu.client.menu.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

@Deprecated
public class ExpandableCategoryScroll extends ScrollView {
    private ExpandableCategoryView mFocusedItem;
    private LinearLayout mContainer;

    public interface OnCategoryClickListener {
        void onCategoryItemClick(ExpandableCategoryScroll list,
                ExpandableCategoryView item);
    }

    OnCategoryClickListener mOnCategoryClickListener;

    public ExpandableCategoryScroll(Context context) {
        super(context);
        prepareViews();
    }

    public ExpandableCategoryScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    public ExpandableCategoryScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    private void prepareViews() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.frame_category_list, this);
        mContainer = (LinearLayout) v.findViewById(R.id.linearContainer);
    }

    public void addCategory(ExpandableCategoryView view) {
//        view.setRoot(this);       // this is locked temporarily
        mContainer.addView(view);
    }

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
