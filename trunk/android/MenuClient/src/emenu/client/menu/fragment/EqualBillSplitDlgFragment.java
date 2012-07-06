package emenu.client.menu.fragment;

import java.text.NumberFormat;

import emenu.client.menu.R;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EqualBillSplitDlgFragment extends DialogFragment implements TextWatcher,
        OnClickListener {

    private int mBillTotal = 0;
    private int mCustomerCount = 1;

    private EditText mCustomerCountEdit;
    private TextView mEachPayText;

    public EqualBillSplitDlgFragment(Integer total) {
        mBillTotal = total;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.title_dialog_bill_split));
        return inflater.inflate(R.layout.layout_equal_bill_split, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCustomerCountEdit = (EditText) getView().findViewById(R.id.editCustomerCount);
        mCustomerCountEdit.addTextChangedListener(this);

        TextView billTotalText = (TextView) getView().findViewById(R.id.textBillTotal);
        billTotalText.setText(mBillTotal + "");

        mEachPayText = (TextView) getView().findViewById(R.id.textEachPay);
        mEachPayText.setText(billTotalText.getText()); // with
                                                       // number
                                                       // customer
                                                       // = 1

        getView().findViewById(R.id.btnMinus).setOnClickListener(this);
        getView().findViewById(R.id.btnPlus).setOnClickListener(this);
    }

    @Override
    public void afterTextChanged(Editable s) {
        int tmpCount = mCustomerCount;
        try {
            mCustomerCount = Integer.valueOf(mCustomerCountEdit.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            mCustomerCount = tmpCount;
        }

        Float eachPay = mBillTotal * 1f / mCustomerCount;
        mEachPayText.setText(eachPay.toString());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlus:
                mCustomerCountEdit.setText((mCustomerCount + 1) + "");
                break;

            case R.id.btnMinus:
                if (mCustomerCount - 1 >= 1)
                    mCustomerCountEdit.setText((mCustomerCount - 1) + "");
                break;
        }
    }
}
