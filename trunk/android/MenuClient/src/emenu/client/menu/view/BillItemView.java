package emenu.client.menu.view;

import emenu.client.menu.R;
import emenu.client.menu.adapter.MainBillAdapter;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.dao.MonAnDAO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhDaNgonNguDTO;
import emenu.client.db.dto.DonViTinhMonAnDTO;
import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.util.U;
import android.content.ContentValues;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BillItemView extends RelativeLayout {
    private Integer mMaMonAn;
    private ContentValues mValues;
    private GetDishRatingTask mUpdateDishRateTask;

    class GetDishRatingTask extends CustomAsyncTask<Void, Void, Boolean> {
        Integer mId;
        float mPoint;

        public GetDishRatingTask(Integer maMonAn, float rate) {
            mId = maMonAn;
            mPoint = rate;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                return MonAnDAO.getInstance().getRatingUpdate(mId, mPoint);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    };

    public BillItemView(Context context) {
        super(context);

        initView(context);
    }

    public BillItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            initView(context);
    }

    public BillItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_bill, this);

        RatingBar rateDish = (RatingBar) findViewById(R.id.rateDish);
        rateDish.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                    boolean fromUser) {
                U.cancelAsyncTask(mUpdateDishRateTask);
                mUpdateDishRateTask = new GetDishRatingTask(mMaMonAn, rating);
                mUpdateDishRateTask.execute();
                
                ratingBar.setEnabled(false);
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        U.cancelAsyncTask(mUpdateDishRateTask);
    }

    public void bindData(ContentValues values) {
        mValues = values;

        mMaMonAn = values.getAsInteger(DonViTinhMonAnDTO.CL_MA_MON_AN);

        Integer quantity = values.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
        Integer unitPrice = values.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);

        TextView dishName = (TextView) findViewById(R.id.textDishName);
        dishName.setText(values.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));

        TextView unitName = (TextView) findViewById(R.id.textUnitName);
        unitName.setText(values.getAsString(DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));

        TextView textUnitPrice = (TextView) findViewById(R.id.textUnitPrice);
        textUnitPrice.setText(unitPrice.toString());

        TextView textQuantity = (TextView) findViewById(R.id.textQuantity);
        textQuantity.setText(quantity.toString());

        TextView textPromAmt = (TextView) findViewById(R.id.textPromTotal);

        int totalWithoutProm = quantity * unitPrice;
        int prom = MainBillAdapter.calcItemProm(totalWithoutProm, mValues);
        if (prom > 0) {
            findViewById(R.id.panePromotion).setVisibility(View.VISIBLE);
            textPromAmt.setText("-" + prom);
        } else {
            findViewById(R.id.panePromotion).setVisibility(View.INVISIBLE);
        }

        TextView textTotal = (TextView) findViewById(R.id.textItemTotal);
        textTotal.setText(String.valueOf(quantity * unitPrice - prom));
    }
}
