package client.menu.ui.view;

import client.menu.R;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import android.content.ContentValues;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BriefOrderItemView extends LinearLayout {
    private TextView mDishName;
    private TextView mQuantity;
    private ContentValues mValues;

    public BriefOrderItemView(Context context) {
        super(context);
        prepareViews();
    }

    public BriefOrderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    public BriefOrderItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    private void prepareViews() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_brief_order_item, this);

        mDishName = (TextView) findViewById(R.id.textDishName);
        mQuantity = (TextView) findViewById(R.id.textQuantity);
    }

    public void bindData(ContentValues c) {
        if (mValues == null
                || mValues.getAsInteger(ChiTietOrderDTO.CL_MA_CHI_TIET) != c
                        .getAsInteger(ChiTietOrderDTO.CL_MA_CHI_TIET)) {
            mValues = c;

            mDishName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
            mQuantity.setText(mValues.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG));
        }
    }

}
