package emenu.client.menu.view;

import android.content.ContentValues;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.menu.adapter.IDishSwappableAdapter;

public class DishSwappableListView extends ListView implements
        android.widget.AdapterView.OnItemClickListener {

    private DishSwappableListView mTheOther;
    private IDishSwappableAdapter mAdapter;
    private OnItemClickListener mSubItemClickListener;
    private OnSwappedListener mOnSwappedListener;
    private RoleName mRole = RoleName.None;

    private enum RoleName {
        None, Main, Sub
    }

    public interface OnSwappedListener {
        void onSwapped(DishSwappableListView list);
    }

    public DishSwappableListView(Context context) {
        super(context);
        initView();
    }

    public DishSwappableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            initView();
    }

    public DishSwappableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            initView();
    }

    public void pair(DishSwappableListView theOther) {
        mTheOther = theOther;
        theOther.mTheOther = this;

        mRole = RoleName.Main;
        mTheOther.mRole = RoleName.Sub;
    }

    private void initView() {
        setOnItemClickListener(this);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (!(adapter instanceof IDishSwappableAdapter))
            throw new IllegalArgumentException(
                    "List adapter must implement IDishSwappableAdapter to be a DishSwappableListView's adapter");

        mAdapter = (IDishSwappableAdapter) adapter;
        super.setAdapter(adapter);
    }

    @Override
    public void setOnItemClickListener(
            android.widget.AdapterView.OnItemClickListener listener) {
        if (listener == this)
            super.setOnItemClickListener(listener);
        else
            mSubItemClickListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        if (mSubItemClickListener != null)
            mSubItemClickListener.onItemClick(arg0, arg1, arg2, arg3);

        if (mTheOther != null) {
            ContentValues values = mAdapter.getItem(arg2);
            Integer soLuongMain = values.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
            soLuongMain -= 1;

            if (soLuongMain == 0) {
                mAdapter.remove(arg2);
            } else if (soLuongMain > 0) {
                values.put(ChiTietOrderDTO.CL_SO_LUONG, soLuongMain);
            }

            int i = 0;
            for (; i < mTheOther.mAdapter.getCount(); ++i) {
                ContentValues c = mTheOther.mAdapter.getItem(i);
                if (c.getAsInteger(ChiTietOrderDTO.CL_MA_MON_AN) == values
                        .getAsInteger(ChiTietOrderDTO.CL_MA_MON_AN)
                        && c.getAsInteger(ChiTietOrderDTO.CL_MA_DON_VI_TINH) == values
                                .getAsInteger(ChiTietOrderDTO.CL_MA_DON_VI_TINH)) {

                    Integer soLuongSub = c.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG) + 1;
                    c.put(ChiTietOrderDTO.CL_SO_LUONG, soLuongSub);
                    break;

                }
            }

            // create new record in "the order" list
            if (i == mTheOther.mAdapter.getCount()) {
                ContentValues c = new ContentValues();
                c.putAll(values);
                c.put(ChiTietOrderDTO.CL_SO_LUONG, 1);

                if (mTheOther.mRole == RoleName.Sub)
                    mTheOther.mAdapter.add(c);
                else if (mTheOther.mRole == RoleName.Main)
                    mTheOther.mAdapter.add(0, c);
            }

            mAdapter.notifyDataSetChanged();
            mTheOther.mAdapter.notifyDataSetChanged();

            if (mOnSwappedListener != null)
                mOnSwappedListener.onSwapped(this);
            if (mTheOther.mOnSwappedListener != null)
                mTheOther.mOnSwappedListener.onSwapped(mTheOther);
        }
    }

    public OnSwappedListener getOnSwappedListener() {
        return mOnSwappedListener;
    }

    public void setOnSwappedListener(OnSwappedListener onSwappedListener) {
        mOnSwappedListener = onSwappedListener;
    }
}
