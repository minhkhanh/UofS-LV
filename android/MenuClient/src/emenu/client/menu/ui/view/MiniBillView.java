package emenu.client.menu.ui.view;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import client.menu.R;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.menu.ui.adapter.MiniBillAdapter;

public class MiniBillView extends LinearLayout {
    private MiniBillAdapter mBillAdapter;
    private TextView mBillTotalText;
    private List<ContentValues> mBillItems = new ArrayList<ContentValues>();
    private ListView mBillList;

    public MiniBillView(Context context) {
        super(context);
        initView();
    }

    public MiniBillView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            initView();
        }
    }

    public MiniBillView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initView();
        }
    }

    public MiniBillView clone() {
        MiniBillView newBill = new MiniBillView(getContext());
        newBill.bindData(mBillItems);

        return newBill;
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.frame_mini_bill, this);
        
        setBackgroundResource(R.color._99acacac);

        mBillTotalText = (TextView) findViewById(R.id.textBillTotal);

        mBillAdapter = new MiniBillAdapter(getContext(), mBillItems);
        mBillList = (ListView) findViewById(R.id.listBill);
        mBillList.setAdapter(mBillAdapter);
    }

    public void swapItem(MiniBillView receiver, int pos) {
        ContentValues values = mBillItems.get(pos);
        Integer soLuongMain = values.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
        soLuongMain -= 1;

        if (soLuongMain == 0) {
            mBillItems.remove(pos);
        } else if (soLuongMain > 0) {
            values.put(ChiTietOrderDTO.CL_SO_LUONG, soLuongMain);
        }

        int i = 0;
        for (; i < receiver.mBillItems.size(); ++i) {
            ContentValues c = receiver.mBillItems.get(i);
            if (c.getAsInteger(ChiTietOrderDTO.CL_MA_MON_AN) == values
                    .getAsInteger(ChiTietOrderDTO.CL_MA_MON_AN)
                    && c.getAsInteger(ChiTietOrderDTO.CL_MA_DON_VI_TINH) == values
                            .getAsInteger(ChiTietOrderDTO.CL_MA_DON_VI_TINH)) {
                Integer soLuongSub = c.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG) + 1;
                c.put(ChiTietOrderDTO.CL_SO_LUONG, soLuongSub);

                break;
            }
        }

        if (i == receiver.mBillItems.size()) {
            ContentValues c = new ContentValues();
            c.putAll(values);
            c.put(ChiTietOrderDTO.CL_SO_LUONG, 1);
            receiver.mBillItems.add(c);
        }

        refreshList();
        receiver.refreshList();
    }

    public void refreshList() {
        mBillAdapter.notifyDataSetChanged();
        mBillTotalText.setText(String.valueOf(mBillAdapter.getBillTotal()));
    }

    public void bindData(List<ContentValues> items) {
        mBillItems.clear();
        if (items != null) {
            mBillItems.addAll(items);
        }
        refreshList();
    }

    public ListView getBillList() {
        return mBillList;
    }

}
