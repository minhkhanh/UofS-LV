package emenu.client.menu.view;

import emenu.client.menu.R;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhDaNgonNguDTO;
import emenu.client.db.dto.DonViTinhMonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.menu.fragment.OrderedItemEditingDlgFragment;
import emenu.client.util.C;
import emenu.client.util.U;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class OrderedItemView extends RelativeLayout implements OnClickListener,
        ContentValuesLayout {
    ContentValues mValues;

    public OrderedItemView(Context context) {
        super(context);
        initView();
    }

    public OrderedItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            initView();
    }

    public OrderedItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.item_ordered_list, this);

        // Button btnEdit = (Button) findViewById(R.id.btnEditItem);
        // Button btnRemove = (Button) findViewById(R.id.btnRemoveItem);
        //
        // btnEdit.setOnClickListener(this);
        // btnRemove.setOnClickListener(this);
    }

    public void bindData(ContentValues values) {
        mValues = values;

        TextView dishName = (TextView) findViewById(R.id.textDishName);
        dishName.setText(values.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));

        TextView unitName = (TextView) findViewById(R.id.textUnitName);
        unitName.setText(values.getAsString(DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));

        TextView unitPrice = (TextView) findViewById(R.id.textUnitPrice);
        unitPrice.setText(values.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA).toString());

        Integer quantity = values.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
        TextView itemQuantity = (TextView) findViewById(R.id.textQuantity);
        itemQuantity.setText(quantity.toString());

        Integer unprocessed = quantity
                - values.getAsInteger(ChiTietOrderDTO.CL_EX_PROCESSED)
                - values.getAsInteger(ChiTietOrderDTO.CL_EX_PROCESSING);

        TextView textProcessed = (TextView) findViewById(R.id.textUnprocCount);
        textProcessed.setText(unprocessed.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;
        }
    }
}
