package client.menu.ui.adapter;

import java.util.Collection;
import java.util.List;

import client.menu.db.dto.NgonNguDTO;

import android.content.Context;
import android.widget.BaseAdapter;

public abstract class CustomArrayAdapter<T> extends BaseAdapter {
    
    private Context mContext;
    private List<T> mData;
    
    public CustomArrayAdapter(Context context, List<T> data) {
        mData = data;
        mContext = context;
    }
    
    public void addAll(Collection<T> addition) {
        for (T t : addition) {
            mData.add(t);
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
        return mData.get(position).hashCode();
    }   
    
    public Context getContext() {
        return mContext;
    }

    public List<T> getData() {
        return mData;
    }
}
