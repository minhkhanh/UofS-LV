package client.menu.ui.view;

import java.util.List;

import client.menu.R;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.bus.task.LoadChildCategoryListTask;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.util.U;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableCategoryView extends LinearLayout implements
        OnPostExecuteAsyncTaskListener<Void, Integer, List<DanhMucDaNgonNguDTO>> {

    private TextView mCategoryText;
    private LinearLayout mExpandableLinear;

    private LoadChildCategoryListTask mTask;
    protected DanhMucDaNgonNguDTO mDanhMuc;

    public ExpandableCategoryView(Context context) {
        super(context);
        prepareViews();
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

        mCategoryText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTask.getStatus() == AsyncTask.Status.PENDING) {
                    mTask.execute();
                }
                toggle();
            }
        });
    }

    @Override
    public void onPostExecuteAsyncTask(
            CustomAsyncTask<Void, Integer, List<DanhMucDaNgonNguDTO>> task,
            List<DanhMucDaNgonNguDTO> result) {
        for (DanhMucDaNgonNguDTO d : result) {
            ExpandableCategoryView item = new ExpandableCategoryView(getContext());
            item.bindData(d);

            mExpandableLinear.addView(item);
        }
    }

    public void bindData(DanhMucDaNgonNguDTO danhMuc) {
        mDanhMuc = danhMuc;
        mTask = new LoadChildCategoryListTask(getContext(), 0, mDanhMuc.getMaDanhMuc());
        mTask.setOnPostExecuteListener(ExpandableCategoryView.this);

        mCategoryText.setText(mDanhMuc.getTenDanhMuc());
    }

    public void toggle() {
        int visibility = mExpandableLinear.getVisibility();
        if (visibility == VISIBLE) {
            mExpandableLinear.setVisibility(GONE);
        } else if (visibility == GONE) {
            mExpandableLinear.setVisibility(VISIBLE);
        }
    }
}
