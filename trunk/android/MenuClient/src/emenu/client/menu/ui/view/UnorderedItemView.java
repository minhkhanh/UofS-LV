package emenu.client.menu.ui.view;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhDTO;
import emenu.client.db.dto.DonViTinhDaNgonNguDTO;
import emenu.client.db.dto.DonViTinhMonAnDTO;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.menu.bus.SessionManager;
import emenu.client.menu.bus.SessionManager.OrderItemId;
import emenu.client.menu.ui.fragment.ItemNoteFragment;
import emenu.client.menu.util.U;

public class UnorderedItemView extends RelativeLayout implements View.OnClickListener,
        ContentValuesLayout {
    private OrderItemId mItemId;
    private ContentValues mValues;

    private TextView mTextQuantity;

    public UnorderedItemView(Context context) {
        super(context);

        initView(context);
    }

    public UnorderedItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    public UnorderedItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_unordered_list, this);

        Button btnPlus = (Button) findViewById(R.id.btnPlus);
        Button btnMinus = (Button) findViewById(R.id.btnMinus);
        Button btnAddNote = (Button) findViewById(R.id.btnAddItemNote);
        Button btnRemove = (Button) findViewById(R.id.btnRemoveItem);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnAddNote.setOnClickListener(this);
        btnRemove.setOnClickListener(this);

        mTextQuantity = (TextView) findViewById(R.id.textQuantity);
    }

    public void bindData(ContentValues values) {
        mValues = values;

        mItemId = new OrderItemId(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN),
                mValues.getAsInteger(DonViTinhDTO.CL_MA_DON_VI_TINH));

        mTextQuantity.setText(mValues.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG)
                .toString());

        TextView dishName = (TextView) findViewById(R.id.textDishName);
        dishName.setText(values.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));

        TextView unitName = (TextView) findViewById(R.id.textUnitName);
        unitName.setText(values.getAsString(DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));

        TextView unitPrice = (TextView) findViewById(R.id.textUnitPrice);
        unitPrice.setText(values.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA).toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddItemNote:
                ItemNoteFragment f = new ItemNoteFragment(mValues);
                U.showDlgFragment((Activity) getContext(), f, "dlg");
                break;
            case R.id.btnPlus:
                int quantity = Integer.valueOf(mTextQuantity.getText().toString()) + 1;
                mTextQuantity.setText(quantity + "");
                SessionManager.getInstance().loadCurrentSession().getOrder()
                        .getItem(mItemId).setSoLuong(quantity);
                break;

            case R.id.btnMinus:
                quantity = Integer.valueOf(mTextQuantity.getText().toString()) - 1;
                if (quantity > 0) {
                    mTextQuantity.setText(quantity + "");
                    SessionManager.getInstance().loadCurrentSession().getOrder()
                            .getItem(mItemId).setSoLuong(quantity);
                }
                break;

            case R.id.btnRemoveItem:
                U.showConfirmDialog(getContext(),
                        R.string.message_confirm_order_item_deletion,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SessionManager.getInstance().loadCurrentSession()
                                        .getOrder().removeItem(mItemId).notifyChanged();
                            }
                        });

                break;
        }

        SessionManager.getInstance().loadCurrentSession().getOrder().logItemsDebug();
    }
}
