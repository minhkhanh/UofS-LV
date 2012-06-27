package emenu.client.menu.ui.view;

import client.menu.R;
import emenu.client.db.dto.DonViTinhDaNgonNguDTO;
import emenu.client.db.dto.DonViTinhMonAnDTO;
import android.content.ContentValues;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DishUnitItemView extends LinearLayout {

    private TextView mName;
    private TextView mPrice;

    private ContentValues mValues;

    public DishUnitItemView(Context context) {
        super(context);
        prepareViews();
    }

    public DishUnitItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    public DishUnitItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    private void prepareViews() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_dish_units, this);

        mName = (TextView) findViewById(R.id.textUnitName);
        mPrice = (TextView) findViewById(R.id.textUnitPrice);
    }

    public void bindData(ContentValues v) {
        mValues = v;

        mName.setText(mValues.getAsString(DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));
        mPrice.setText(mValues.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA).toString());
    }
}
