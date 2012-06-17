package client.menu.ui.view;

import client.menu.R;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.dao.MonAnDAO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.KhuyenMaiDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;
import client.menu.util.U;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BillItemView extends RelativeLayout {
    Integer mMaMonAn;

    UpdateDishRateTask mUpdateDishRateTask;

    class UpdateDishRateTask extends CustomAsyncTask<Void, Void, Void> {

        Integer mId;
        Float mPoint;

        public UpdateDishRateTask(Integer maMonAn, Float rate) {
            mId = maMonAn;
            mPoint = rate;
        }

        @Override
        protected Void doInBackground(Void... params) {
            MonAnDAO.getInstance().updateDiemDanhGia(mId, mPoint);
            return null;
        }

    };

    public BillItemView(Context context) {
        super(context);

        initView(context);
    }

    public BillItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    public BillItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

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
                mUpdateDishRateTask = new UpdateDishRateTask(mMaMonAn, rating);
                mUpdateDishRateTask.execute();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        U.cancelAsyncTask(mUpdateDishRateTask);
    }

    public void bindData(ContentValues values) {
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
        
        Float promValue = values.getAsFloat(KhuyenMaiDTO.CL_GIA_GIAM);
        Float promRate = values.getAsFloat(KhuyenMaiDTO.CL_TI_LE_GIAM);
        Float promTotal = 0f;
        if (promValue == null || promRate == null) {
            textPromAmt.setText(promTotal.toString());
        } else {
            promTotal = promValue + (promRate / 100) * unitPrice * quantity;
            textPromAmt.setText(promTotal.toString());
        }
        
        TextView textTotal = (TextView) findViewById(R.id.textItemTotal);
        textTotal.setText(String.valueOf(quantity * unitPrice - promTotal));
    }
}
