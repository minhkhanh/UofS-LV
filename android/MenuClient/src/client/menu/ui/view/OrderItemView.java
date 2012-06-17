package client.menu.ui.view;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.OrderItemId;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;

public class OrderItemView extends RelativeLayout {
    private OrderItemId mItemId;
    private ContentValues mValues;

    private Button mButtonRemove;
    private Button mButtonPlus;
    private Button mButtonMinus;
    private TextView mTextQuantity;

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnPlus:
                    int quantity = Integer.valueOf(mTextQuantity.getText().toString()) + 1;
                    mTextQuantity.setText(quantity + "");
                    SessionManager.getInstance().loadCurrentSession()
                            .getOrder().getItem(mItemId).setSoLuong(quantity);
                    break;

                case R.id.btnMinus:
                    quantity = Integer.valueOf(mTextQuantity.getText().toString()) - 1;
                    if (quantity > 0) {
                        mTextQuantity.setText(quantity + "");
                        SessionManager.getInstance().loadCurrentSession()
                                .getOrder().getItem(mItemId).setSoLuong(quantity);
                    }
                    break;

                case R.id.btnRemove:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(
                            getContext().getResources().getString(
                                    R.string.message_confirm_order_item_deletion))
                            .setCancelable(false)
                            .setPositiveButton(
                                    getContext().getResources().getString(
                                            R.string.caption_yes),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            SessionManager.getInstance()
                                                    .loadCurrentSession()
                                                    .getOrder().removeItem(mItemId)
                                                    .notifyChanged();
                                        }
                                    })
                            .setNegativeButton(
                                    getContext().getResources().getString(
                                            R.string.caption_no),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    AlertDialog alert = builder.create();
                    alert.show();

                    break;
            }

            SessionManager.getInstance().loadCurrentSession().getOrder()
                    .logItemsDebug();
        }
    };

    public OrderItemView(Context context) {
        super(context);

        initView(context);
    }

    public OrderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    public OrderItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_order, this);

        mButtonPlus = (Button) findViewById(R.id.btnPlus);
        mButtonMinus = (Button) findViewById(R.id.btnMinus);
        mButtonPlus.setOnClickListener(mOnClickListener);
        mButtonMinus.setOnClickListener(mOnClickListener);

        mButtonRemove = (Button) findViewById(R.id.btnRemove);
        mButtonRemove.setOnClickListener(mOnClickListener);

        mTextQuantity = (TextView) findViewById(R.id.textQuantity);
    }

    public void bindData(ContentValues values) {
        mValues = values;

        mItemId = new OrderItemId(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN),
                mValues.getAsInteger(DonViTinhDTO.CL_MA_DON_VI_TINH));

        mTextQuantity.setText(mValues.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG)
                .toString());

        EditText dishNote = (EditText) findViewById(R.id.editDishNote);
        dishNote.setText(mValues.getAsString(ChiTietOrderDTO.CL_GHI_CHU));

        TextView dishName = (TextView) findViewById(R.id.textDishName);
        dishName.setText(values.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));

        TextView unitName = (TextView) findViewById(R.id.textUnitName);
        unitName.setText(values.getAsString(DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));

        TextView unitPrice = (TextView) findViewById(R.id.textUnitPrice);
        unitPrice.setText(values.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA).toString());

        // if (mChiTietOrder.getMaOrder() != null && mChiTietOrder.getMaOrder()
        // > 0) {
        // setBackgroundResource(R.color._99acacac);
        // }
    }
}
