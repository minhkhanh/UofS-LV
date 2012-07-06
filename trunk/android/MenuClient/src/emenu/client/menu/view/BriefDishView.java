package emenu.client.menu.view;

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
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.GetDishPromotionTask;
import emenu.client.bus.task.LoadDishUnitsTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.db.dto.DonViTinhDTO;
import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.menu.adapter.DishUnitsAdapter;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceOrder;
import emenu.client.menu.fragment.DishDetailDlgFragment;
import emenu.client.util.C;
import emenu.client.util.U;

public class BriefDishView extends RelativeLayout implements
        OnPostExecuteListener<Integer, Void, List<ContentValues>>, View.OnClickListener {

    private ContentValues mValues;

    private TextView mDishName;
    private TextView mDescript;
    private RatingBar mRate;
    private Spinner mUnitsSpinner;
    private Button mAddButton;
    private ImageView mSaleImage;

    private ImageView mAvatar;

    private LoadDishUnitsTask mTask;

    private DishUnitsAdapter mSpinnerAdapter;

    private OnPostExecuteListener<Integer, Void, KhuyenMaiDTO> mOnPostGetDishPromotion = new OnPostExecuteListener<Integer, Void, KhuyenMaiDTO>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Integer, Void, KhuyenMaiDTO> task,
                KhuyenMaiDTO result) {
            if (result != null) {
                mSaleImage.setVisibility(View.VISIBLE);
                mValues.putAll(result.toContentValues());
            } else
                mSaleImage.setVisibility(View.GONE);
        }
    };

    public BriefDishView(Context context) {
        super(context);
        initView();
    }

    public BriefDishView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initView();
        }
    }

    public BriefDishView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            initView();
        }
    }

    public void bindData(ContentValues c) {
        mValues = c;

        U.setDishAvatar(c, mAvatar);

        mDishName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
        mDescript.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_MO_TA_MON));
        mRate.setRating(mValues.getAsFloat(MonAnDTO.CL_DIEM_DANH_GIA));

        mTask = new LoadDishUnitsTask();
        mTask.setOnPostExecuteListener(this);
        mTask.execute(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN));

        if (mValues.getAsInteger(KhuyenMaiDTO.CL_MA_KHUYEN_MAI) != null) {
            mSaleImage.setVisibility(View.VISIBLE);
        } else {
            mSaleImage.setVisibility(View.INVISIBLE);
        }

        // new
        // GetDishPromotionTask().setOnPostExecuteListener(mOnPostGetDishPromotion)
        // .execute(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN));
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_brief_dish, this);

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

        mSaleImage = (ImageView) findViewById(R.id.imgSale);
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
                U.showDlgFragment((Activity) getContext(), newFragment, true);
                break;
        }
    }
}
