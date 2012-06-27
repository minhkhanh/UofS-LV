package emenu.client.menu.ui.view;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.menu.bus.task.CustomAsyncTask;
import emenu.client.menu.bus.task.LoadChildCategoryListTask;
import emenu.client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.menu.ui.view.ExpandableCategoryList3.OnCategoryClickListener;
import emenu.client.menu.util.U;

public class ExpandableCategoryView extends LinearLayout implements
        OnPostExecuteListener<Integer, Void, List<DanhMucDaNgonNguDTO>> {

    private ExpandableCategoryList3 mRoot;

    private TextView mCategoryText;
    private LinearLayout mExpandableLinear;

    private LoadChildCategoryListTask mTask;
    private DanhMucDaNgonNguDTO mDanhMuc;

    private boolean mHavingChilden;
    private boolean mExpanded;
    private boolean mFocused;

    public ExpandableCategoryView(Context context, ExpandableCategoryList3 root) {
        super(context);
        prepareViews();
        mRoot = root;
    }

    public ExpandableCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    public ExpandableCategoryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        U.cancelAsyncTask(mTask);
    }

    private void prepareViews() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.frame_expandable_category, this);

        mCategoryText = (TextView) findViewById(R.id.textCategory);
        mExpandableLinear = (LinearLayout) findViewById(R.id.linearExpandable);
        mExpanded = false;
        mFocused = false;
        mHavingChilden = false;

        mCategoryText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTask != null) {
                    if (mTask.getStatus() == AsyncTask.Status.PENDING) {
                        mTask.execute(mDanhMuc.getMaDanhMuc());
                    }

                    if (mExpanded && !mFocused) {
                        setFocused(true);
                    } else if (mHavingChilden) {
                        toggleExpanded();
                    }

                    OnCategoryClickListener listener = mRoot.getOnCategoryClickListener();
                    if (listener != null) {
                        listener.onCategoryItemClick(mRoot, ExpandableCategoryView.this);
                    }
                }
            }
        });
    }

    @Override
    public void onPostExecute(
            CustomAsyncTask<Integer, Void, List<DanhMucDaNgonNguDTO>> task,
            List<DanhMucDaNgonNguDTO> result) {
        toggleExpanded();
        if (result.size() > 0) {
            mHavingChilden = true;
            for (DanhMucDaNgonNguDTO d : result) {
                ExpandableCategoryView item = new ExpandableCategoryView(getContext(),
                        mRoot);
                item.bindData(d);

                mExpandableLinear.addView(item);
            }
        }
    }

    public void bindData(DanhMucDaNgonNguDTO danhMuc) {
        if (mDanhMuc == null || danhMuc.getMaDanhMuc() != mDanhMuc.getMaDanhMuc()) {
            mDanhMuc = danhMuc;
            mTask = new LoadChildCategoryListTask();
            mTask.setOnPostExecuteListener(ExpandableCategoryView.this);

            mCategoryText.setText(mDanhMuc.getTenDanhMuc());
        }
    }

    public void setFocused(boolean flag) {
        mFocused = flag;
        mCategoryText.setActivated(mFocused);
        if (mFocused) {
            if (mRoot.getFocusedItem() != null && mRoot.getFocusedItem() != this) {
                mRoot.getFocusedItem().setFocused(false);
            }

            mRoot.setFocusedItem(this);
        }
    }

    public void toggleExpanded() {
        mExpanded = !mExpanded;
        setFocused(mExpanded);
        if (mExpanded) {
            mExpandableLinear.setVisibility(VISIBLE);
        } else {
            mExpandableLinear.setVisibility(GONE);
        }
    }

    public ExpandableCategoryList3 getRoot() {
        return mRoot;
    }

    public DanhMucDaNgonNguDTO getDanhMuc() {
        return mDanhMuc;
    }
}
