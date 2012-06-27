package emenu.client.menu.ui.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.bus.SessionManager;
import emenu.client.bus.SessionManager.ServiceOrder;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.LoadDishUnitsAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.db.dto.DonViTinhDTO;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.menu.ui.adapter.DishUnitsAdapter;
import emenu.client.menu.ui.fragment.DishDetailDlgFragment;
import emenu.client.menu.util.U;

public class DishItemView extends RelativeLayout implements
        OnPostExecuteListener<Integer, Void, List<ContentValues>>, View.OnClickListener {

    private ContentValues mValues;

    private TextView mDishName;
    private TextView mDescript;
    private RatingBar mRate;
    private Spinner mUnitsSpinner;
    private Button mAddButton;
    // private ImageButton mDetailButton;

    private ImageView mAvatar;

    private LoadDishUnitsAsyncTask mTask;

    private DishUnitsAdapter mSpinnerAdapter;

    public DishItemView(Context context) {
        super(context);
        initView();
    }

    public DishItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initView();
        }
    }

    public DishItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            initView();
        }
    }

    public void bindData(ContentValues c) {
        if (mValues == null
                || mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN) != c
                        .getAsInteger(MonAnDTO.CL_MA_MON_AN)) {
            mValues = c;

            byte[] imgData = c.getAsByteArray(MonAnDTO.CL_HINH_ANH);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(imgData, 0,
                    imgData.length);
            mAvatar.setImageBitmap(decodedBitmap);

            mDishName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
            mDescript.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_MO_TA_MON));
            mRate.setRating(mValues.getAsFloat(MonAnDTO.CL_DIEM_DANH_GIA));

            mTask = new LoadDishUnitsAsyncTask();
            mTask.setOnPostExecuteListener(this);
            mTask.execute(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN));
        }
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_dish_list, this);

        mAvatar = (ImageView) findViewById(R.id.imgDishAvatar);
        mAvatar.setOnClickListener(this);

        mDishName = (TextView) findViewById(R.id.textDishName);
        mDescript = (TextView) findViewById(R.id.textDishDescription);
        mRate = (RatingBar) findViewById(R.id.rateDish);

        mSpinnerAdapter = new DishUnitsAdapter(getContext(),
                new ArrayList<ContentValues>());
        mUnitsSpinner = (Spinner) findViewById(R.id.spinDishUnits);
        mUnitsSpinner.setAdapter(mSpinnerAdapter);

        mAddButton = (Button) findViewById(R.id.btnAdd);
        mAddButton.setOnClickListener(this);
    }

    @Override
    public void onPostExecute(CustomAsyncTask<Integer, Void, List<ContentValues>> task,
            List<ContentValues> result) {
        mSpinnerAdapter.clear();
        mSpinnerAdapter.addAll(result);
        mSpinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                ContentValues c = mSpinnerAdapter.getItem(mUnitsSpinner
                        .getSelectedItemPosition());

                ServiceOrder order = SessionManager.getInstance().loadCurrentSession()
                        .getOrder();
                order.addItem(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN),
                        c.getAsInteger(DonViTinhDTO.CL_MA_DON_VI_TINH), 1, "");

                U.toastText(getContext(),
                        getContext().getString(R.string.message_order_item_added) + ": "
                                + mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
                break;

            case R.id.imgDishAvatar:
                DishDetailDlgFragment newFragment = new DishDetailDlgFragment(mValues,
                        (DishUnitsAdapter) mUnitsSpinner.getAdapter(),
                        mUnitsSpinner.getSelectedItemPosition());
                U.showDlgFragment((Activity) getContext(), newFragment, "dialog");
                break;
        }
    }
}
