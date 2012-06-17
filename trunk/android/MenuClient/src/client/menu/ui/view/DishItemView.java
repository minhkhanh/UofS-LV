package client.menu.ui.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteListener;
import client.menu.bus.task.LoadDishUnitsAsyncTask;
import client.menu.db.dto.DonViTinhDTO;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.ui.adapter.DishUnitsAdapter;
import client.menu.ui.fragment.DishDetailDlgFragment;
import client.menu.util.U;

public class DishItemView extends RelativeLayout implements
        OnPostExecuteListener<Integer, Void, List<ContentValues>> {

    private ContentValues mValues;

    private TextView mDishName;
    private TextView mDescript;
    private RatingBar mRate;
    private Spinner mUnitsSpinner;
    private ImageButton mAddButton;
    private ImageButton mDetailButton;

    private LoadDishUnitsAsyncTask mTask;

    private DishUnitsAdapter mSpinnerAdapter;

    public DishItemView(Context context) {
        super(context);
        prepareViews();
    }

    public DishItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    public DishItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    public void bindData(ContentValues c) {
        if (mValues == null
                || mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN) != c
                        .getAsInteger(MonAnDTO.CL_MA_MON_AN)) {
            mValues = c;

            mDishName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
            mDescript.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_MO_TA_MON));
            mRate.setRating(mValues.getAsFloat(MonAnDTO.CL_DIEM_DANH_GIA));

            mTask = new LoadDishUnitsAsyncTask();
            mTask.setOnPostExecuteListener(this);
            mTask.execute(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN));
        }
    }

    private void prepareViews() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_dish_list, this);

        mDishName = (TextView) findViewById(R.id.textDishName);
        mDescript = (TextView) findViewById(R.id.textDishDescription);
        mRate = (RatingBar) findViewById(R.id.rateDish);

        mSpinnerAdapter = new DishUnitsAdapter(getContext(),
                new ArrayList<ContentValues>());
        mUnitsSpinner = (Spinner) findViewById(R.id.spinDishUnits);
        mUnitsSpinner.setAdapter(mSpinnerAdapter);

        mAddButton = (ImageButton) findViewById(R.id.ibtnAdd);
        mAddButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues c = (ContentValues) mUnitsSpinner.getSelectedItem();

                ServiceOrder order = SessionManager.getInstance()
                        .loadCurrentSession().getOrder();
                order.addItem(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN),
                        c.getAsInteger(DonViTinhDTO.CL_MA_DON_VI_TINH), 1, null);

                U.toastText(getContext(),
                        getContext().getString(R.string.message_order_item_added) + ": "
                                + mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
            }
        });

        mDetailButton = (ImageButton) findViewById(R.id.ibtnMore);
        mDetailButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DishDetailDlgFragment newFragment = new DishDetailDlgFragment(mValues,
                        (DishUnitsAdapter) mUnitsSpinner.getAdapter(), mUnitsSpinner
                                .getSelectedItemPosition());
                U.showDlgFragment((Activity) getContext(), newFragment, "dialog");
            }
        });
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Integer, Void, List<ContentValues>> task,
            List<ContentValues> result) {
        mSpinnerAdapter.clear();
        mSpinnerAdapter.addAll(result);
        mSpinnerAdapter.notifyDataSetChanged();
    }

}
