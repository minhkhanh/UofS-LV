package emenu.client.menu.ui.fragment;

import emenu.client.menu.R;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EqualBillSplitDialog extends DialogFragment {

    private Integer mBillTotal;

    public EqualBillSplitDialog(Integer total) {
        mBillTotal = total;
    }

    private TextView mCustomerNumText;
    private TextView mEachPayText;
    private TextView mBillTotalText;

    private OnClickListener mOnChangeCustomerNumber = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Integer number = 1;
            switch (v.getId()) {
                case R.id.btnPlus:
                    number = Integer.valueOf(mCustomerNumText.getText().toString()) + 1;
                    break;

                case R.id.btnMinus:
                    number = Integer.valueOf(mCustomerNumText.getText().toString()) - 1;
                    if (number < 1) {
                        number = 1;
                    }

                    mCustomerNumText.setText(number.toString());
                    break;
            }

            Integer total = Integer.valueOf(mBillTotalText.getText().toString());

            mCustomerNumText.setText(number.toString());

            Float eachPay = total * 1f / number;
            mEachPayText.setText(eachPay.toString());
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.title_dialog_bill_split));
        View v = inflater.inflate(R.layout.dialog_equal_split_bill, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCustomerNumText = (TextView) getView().findViewById(R.id.textCustomerNumber);

        mBillTotalText = (TextView) getView().findViewById(R.id.textBillTotal);
        mBillTotalText.setText(mBillTotal.toString());

        mEachPayText = (TextView) getView().findViewById(R.id.textEachPay);
        mEachPayText.setText(mBillTotalText.getText()); // with
                                                        // number
                                                        // customer
                                                        // = 1

        Button btnMinus = (Button) getView().findViewById(R.id.btnMinus);
        Button btnPlus = (Button) getView().findViewById(R.id.btnPlus);
        btnMinus.setOnClickListener(mOnChangeCustomerNumber);
        btnPlus.setOnClickListener(mOnChangeCustomerNumber);
    }
}
