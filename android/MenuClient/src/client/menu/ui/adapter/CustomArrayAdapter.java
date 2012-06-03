package client.menu.ui.adapter;

import java.util.Collection;
import java.util.List;

import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.NgonNguDTO;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CustomArrayAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mData;

    public CustomArrayAdapter(Context context, List<T> data) {
        mData = data;
        mContext = context;
    }

    public void add(T object) {
        mData.add(object);
    }

    public void addAll(Collection<? extends T> addition) {
        for (T t : addition) {
            mData.add(t);
        }
    }

    public void addAll(int location, Collection<? extends T> addition) {
        for (T t : addition) {
            mData.add(location++, t);
        }
    }

    public void clear() {
        mData.clear();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Context getContext() {
        return mContext;
    }

    public List<T> getData() {
        return mData;
    }
}
