package client.menu.ui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.bus.task.LoadChildCategoryListTask;
import client.menu.db.dto.DanhMucDaNgonNguDTO;
import client.menu.ui.adapter.ExpandableCategoryAdapter3;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ExpandableCategoryList3 extends ListView implements
        OnPostExecuteAsyncTaskListener<Void, Integer, List<DanhMucDaNgonNguDTO>> {

    ExpandableCategoryAdapter3 mAdapter;

    OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            DanhMucDaNgonNguDTO danhMucCha = mAdapter.getItem(arg2).danhMuc;
            int treePosition = mAdapter.getTreePosition(danhMucCha.getMaDanhMuc());

            if (mAdapter.isExpanded(arg2)) {
                mAdapter.collapse(arg2);
                mAdapter.notifyDataSetChanged();
            } else {
                if (mAdapter.isChildrenLoaded(treePosition)) {
                    mAdapter.expand(arg2, treePosition);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Bundle extras = new Bundle();
                    extras.putInt("position", arg2);
                    extras.putInt("treePosition", treePosition);

                    LoadChildCategoryListTask task = new LoadChildCategoryListTask(
                            getContext(), danhMucCha.getMaDanhMuc());
                    task.setOnPostExecuteListener(ExpandableCategoryList3.this);
                    task.setExtras(extras);
                    task.execute();
                }
            }
        }
    };

    public ExpandableCategoryList3(Context context) {
        super(context);
        setOnItemClickListener(mOnItemClickListener);
    }

    public ExpandableCategoryList3(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnItemClickListener(mOnItemClickListener);
    }

    public ExpandableCategoryList3(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (!(adapter instanceof ExpandableCategoryAdapter3)) {
            throw new UnsupportedOperationException(
                    "This method only supports ExpandableCategoryAdapter3.");
        }

        super.setAdapter(adapter);
        mAdapter = (ExpandableCategoryAdapter3) adapter;
    }

    @Override
    public void onPostExecuteAsyncTask(
            CustomAsyncTask<Void, Integer, List<DanhMucDaNgonNguDTO>> task,
            List<DanhMucDaNgonNguDTO> result) {
        if (result.size() > 0) {
            Bundle ex = task.getExtras();
            int position = ex.getInt("position");
            int treePosition = ex.getInt("treePosition");
            mAdapter.expand(position, treePosition, result);
            mAdapter.notifyDataSetChanged();
        }
    }

}
