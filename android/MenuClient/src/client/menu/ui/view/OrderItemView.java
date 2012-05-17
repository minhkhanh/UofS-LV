package client.menu.ui.view;

import client.menu.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OrderItemView extends RelativeLayout {

    Button mButtonPlus;
    Button mButtonMinus;
    TextView mTextQuantity;

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == mButtonPlus.getId()) {
                Integer quantity = Integer.valueOf(mTextQuantity.getText().toString()) + 1;
                mTextQuantity.setText(quantity.toString());
            } else if (v.getId() == mButtonMinus.getId()) {
                Integer quantity = Integer.valueOf(mTextQuantity.getText().toString()) - 1;
                if (quantity > 0) {
                    mTextQuantity.setText(quantity.toString());
                }
            }
        }
    };

    public OrderItemView(Context context) {
        super(context);

        inflatView(context);
    }

    public OrderItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        inflatView(context);
    }

    private void inflatView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_order, this);
    }

    private void prepareWidgets() {
        mButtonPlus = (Button) findViewById(R.id.btnPlus);
        mButtonMinus = (Button) findViewById(R.id.btnMinus);
        mTextQuantity = (TextView) findViewById(R.id.textQuantity);

        mButtonPlus.setOnClickListener(mOnClickListener);
    }
}
