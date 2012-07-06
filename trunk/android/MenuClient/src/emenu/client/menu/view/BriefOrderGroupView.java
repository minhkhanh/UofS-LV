package emenu.client.menu.view;

import emenu.client.menu.R;
import emenu.client.db.dto.OrderDTO;
import emenu.client.menu.activity.BillActivity;
import emenu.client.menu.activity.MainMenuActivity;
import emenu.client.menu.activity.TableMapActivity;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.fragment.OrderSplittingDlgFragment;
import emenu.client.util.C;
import emenu.client.util.U;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class BriefOrderGroupView extends CheckableLayout implements OnClickListener {

    private OrderDTO mOrder;

    // private TextView mOrderTitle;
    private ImageView mBtnMoveOrder;
    private ImageView mBtnRemoveOrder;
    private ImageView mBtnSplitOrder;
    private ImageView mBtnPayment;
    private ImageView mBtnSelect;
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

        mBtnMoveOrder = (ImageView) findViewById(R.id.imgMoveOrder);
        mBtnRemoveOrder = (ImageView) findViewById(R.id.imgRemoveOrder);
        mBtnSplitOrder = (ImageView) findViewById(R.id.imgSplitOrder);
        mBtnPayment = (ImageView) findViewById(R.id.imgPayment);
        mBtnSelect = (ImageView) findViewById(R.id.imgSelectOrder);

        // !important: setFocusable(false) all of the ImageView inside for the
        // holder item to be clickable
        // mBtnMoveOrder.setFocusable(false);
        // mBtnRemoveOrder.setFocusable(false);
        // mBtnPayment.setFocusable(false);
        // mBtnSplitOrder.setFocusable(false);

        mBtnMoveOrder.setOnClickListener(this);
        mBtnRemoveOrder.setOnClickListener(this);
        mBtnPayment.setOnClickListener(this);
        mBtnSplitOrder.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);

        // mOrderTitle = (TextView) findViewById(R.id.textOrderTitle);
    }

    public void bindData(int index, OrderDTO order) {
        mOrder = order;

        TextView orderTitle = (TextView) findViewById(R.id.textOrderTitle);
        orderTitle.setText(getContext().getString(R.string.sub_order_no) + " "
                + (index + 1));
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
            case R.id.imgSelectOrder:
                SessionManager.getInstance().loadSession(mOrder.getMaOrder());
                Intent intent = new Intent(getContext(), MainMenuActivity.class);
                getContext().startActivity(intent);
                break;

            case R.id.imgPayment:
                intent = new Intent(getContext(), BillActivity.class);
                getContext().startActivity(intent);
                break;

            case R.id.imgMoveOrder:
                intent = new Intent(getContext(), TableMapActivity.class);
                intent.putExtra(TableMapActivity.KEY_MOVING_ORDER_ID, mOrder.getMaOrder());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(intent);
                break;

            case R.id.imgSplitOrder:
                OrderSplittingDlgFragment dlg = new OrderSplittingDlgFragment(
                        mOrder.getMaOrder());
                U.showDlgFragment((Activity) getContext(), dlg, true);
                break;

            default:
                break;
        }
    }
}
