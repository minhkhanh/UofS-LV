package emenu.client.menu.ui.view;

import client.menu.R;
import emenu.client.db.dto.OrderDTO;
import emenu.client.menu.ui.activity.BillActivity;
import emenu.client.menu.ui.activity.TableMapActivity;
import emenu.client.menu.ui.fragment.OrderSplittingDlgFragment;
import emenu.client.menu.util.U;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class BriefOrderGroupView extends CheckableLayout implements OnClickListener {

    private OrderDTO mOrder;

    private TextView mOrderTitle;
    private ImageButton mBtnMoveOrder;
    private ImageButton mBtnRemoveOrder;
    private ImageButton mBtnSplitOrder;
    private ImageButton mBtnPayment;
    private ViewGroup mButtonPane;

    public BriefOrderGroupView(Context context) {
        super(context, R.layout.item_brief_order_list, R.id.cmarkOrderSelection);
    }

    public BriefOrderGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BriefOrderGroupView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void initView(int layoutResId, int checkMarkResId) {
        super.initView(layoutResId, checkMarkResId);

        mButtonPane = (ViewGroup) findViewById(R.id.paneButtons);

        mBtnMoveOrder = (ImageButton) findViewById(R.id.ibtnMoveOrder);
        mBtnRemoveOrder = (ImageButton) findViewById(R.id.ibtnRemoveOrder);
        mBtnSplitOrder = (ImageButton) findViewById(R.id.ibtnSplitOrder);
        mBtnPayment = (ImageButton) findViewById(R.id.ibtnPayment);

        // !important: setFocusable(false) all of the ImageButton inside for the
        // holder item to be clickable
        mBtnMoveOrder.setFocusable(false);
        mBtnRemoveOrder.setFocusable(false);
        mBtnPayment.setFocusable(false);
        mBtnSplitOrder.setFocusable(false);

        mBtnMoveOrder.setOnClickListener(this);
        mBtnRemoveOrder.setOnClickListener(this);
        mBtnPayment.setOnClickListener(this);
        mBtnSplitOrder.setOnClickListener(this);

        mOrderTitle = (TextView) findViewById(R.id.textOrderTitle);
    }

    public void bindData(int index, OrderDTO order) {
        mOrder = order;

        TextView orderTitle = (TextView) findViewById(R.id.textOrderTitle);
        orderTitle.setText(getContext().getString(R.string.sub_order_no) + " "
                + (index + 1));
    }

    public void setOrderTitle(int resId) {
        mOrderTitle.setText(resId);
    }

    @Override
    public void onMarkChecked(boolean isChecked) {
        super.onMarkChecked(isChecked);

        if (isChecked() && mOrder != null) {
            mButtonPane.setVisibility(View.VISIBLE);
        } else {
            mButtonPane.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnPayment:
                Intent intent = new Intent(getContext(), BillActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.ibtnMoveOrder:
                intent = new Intent(getContext(), TableMapActivity.class);
                intent.putExtra(TableMapActivity.KEY_MOVING_ORDER_ID, mOrder.getMaOrder());
                getContext().startActivity(intent);
                break;
                
            case R.id.ibtnSplitOrder:
                OrderSplittingDlgFragment dlg = new OrderSplittingDlgFragment(mOrder.getMaOrder());
                U.showDlgFragment((Activity) getContext(), dlg, "dlg");
                break;

            default:
                break;
        }
    }

    public ViewGroup getButtonPane() {
        return mButtonPane;
    }

    public OrderDTO getOrder() {
        return mOrder;
    }

    public void setOrder(OrderDTO order) {
        mOrder = order;
    }
}
