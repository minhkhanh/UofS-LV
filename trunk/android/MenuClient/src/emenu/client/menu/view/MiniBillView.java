package emenu.client.menu.view;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.menu.adapter.MiniBillAdapter;
import emenu.client.menu.view.DishSwappableListView.OnSwappedListener;

public class MiniBillView extends LinearLayout implements OnSwappedListener {
    private MiniBillAdapter mBillAdapter;
    private TextView mBillTotalText;
//    private List<ContentValues> mBillItems = new ArrayList<ContentValues>();
    private DishSwappableListView mBillList;

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

        List<ContentValues> newitems = new ArrayList<ContentValues>();
        List<ContentValues> billItems = mBillAdapter.getData();
        for (ContentValues c : billItems) {
            ContentValues newValues = new ContentValues();
            newValues.putAll(c);
            newitems.add(newValues);
        }
        newBill.bindData(newitems);

        return newBill;
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.frame_mini_bill, this);

        setBackgroundResource(R.color._99acacac);

        mBillTotalText = (TextView) findViewById(R.id.textBillTotal);

        mBillAdapter = new MiniBillAdapter(getContext(), new ArrayList<ContentValues>());
        mBillList = (DishSwappableListView) findViewById(R.id.listBill);
        mBillList.setOnSwappedListener(this);
        mBillList.setAdapter(mBillAdapter);
    }

    private void updateText() {
        mBillTotalText.setText(String.valueOf(mBillAdapter.calcBillTotal()));
    }

    public void bindData(List<ContentValues> items) {
        mBillAdapter.clear();
        if (items != null) {
            mBillAdapter.addAll(items);
        }
        mBillAdapter.notifyDataSetChanged();
        updateText();
    }

    public DishSwappableListView getBillList() {
        return mBillList;
    }

    @Override
    public void onSwapped(DishSwappableListView list) {
        updateText();
    }

}
