package client.menu.ui.view;

import java.util.List;
import java.util.concurrent.ExecutionException;

import client.menu.R;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.bus.task.LoadChildCategoryListTask;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.view.ExpandableCategoryList.OnCategoryClickListener;
import client.menu.util.U;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableCategoryView extends LinearLayout implements
        OnPostExecuteAsyncTaskListener<Void, Integer, List<DanhMucDaNgonNguDTO>> {

    private ExpandableCategoryList mRoot;

    private TextView mCategoryText;
    private LinearLayout mExpandableLinear;

    private LoadChildCategoryListTask mTask;
    private DanhMucDaNgonNguDTO mDanhMucCha;
    private boolean mHavingChilden;
    private boolean mExpanded;
    private boolean mFocused;

    public ExpandableCategoryView(Context context, ExpandableCategoryList root) {
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
                        mTask.execute();
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
    public void onPostExecuteAsyncTask(
            CustomAsyncTask<Void, Integer, List<DanhMucDaNgonNguDTO>> task,
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
        if (mDanhMucCha == null || danhMuc.getMaDanhMuc() != mDanhMucCha.getMaDanhMuc()) {
            mDanhMucCha = danhMuc;
            mTask = new LoadChildCategoryListTask(getContext(), 0,
                    mDanhMucCha.getMaDanhMuc());
            mTask.setOnPostExecuteListener(ExpandableCategoryView.this);

            mCategoryText.setText(mDanhMucCha.getTenDanhMuc());
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

    public ExpandableCategoryList getRoot() {
        return mRoot;
    }

    public void setRoot(ExpandableCategoryList root) {
        mRoot = root;
    }
}
