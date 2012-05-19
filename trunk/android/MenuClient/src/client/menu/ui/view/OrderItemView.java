package client.menu.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;

public class OrderItemView extends RelativeLayout {
    private ChiTietOrderDTO mChiTietOrder;
//    private MonAnDaNgonNguDTO mMonAnDaNgonNgu;
//    private DonViTinhDaNgonNguDTO mDonViTinhDaNgonNgu;
//    private DonViTinhMonAnDTO mDonViTinhMonAn;

    private Button mButtonPlus;
    private Button mButtonMinus;
    private TextView mTextQuantity;
    // private Spinner mUnitSpinner;
    private TextView mDishNameTextView;
    private EditText mDishNoteEditText;
    private TextView mUnitNameTextView;
    private TextView mUnitPriceTextView;

    // private SimpleCursorAdapter mUnitSpinnerAdapter;

//    private class LoadOrderItemAsyncTask extends AsyncTask<Void, Integer, Void> {
//        private ChiTietOrderDTO mChiTietOrder;
//        private Cursor mDonViTinhCursor;
//        private MonAnDaNgonNguDTO mMonAn;
//        // private int mSelectedIndex = -1;
//        private String mTenDonViTinh;
//        private Integer mGiaDonViTinh;
//
//        public LoadOrderItemAsyncTask(ChiTietOrderDTO chiTietOrder) {
//            mChiTietOrder = chiTietOrder;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//
//            mMonAnDaNgonNgu = mMonAn;
//            mDishNameTextView.setText(mMonAn.getTenMonAn());
//
//            mUnitNameTextView.setText(mTenDonViTinh);
//            mUnitPriceTextView.setText(mGiaDonViTinh.toString());
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            String[] projection = null;
//            String selection = MonAnContract.CL_MA_MON_AN_QN + "=? and "
//                    + MonAnDaNgonNguContract.CL_MA_NGON_NGU + "=?";
//            String[] selectionArgs = {
//                    mChiTietOrder.getMaMonAn().toString(),
//                    MyAppLocale.getCurrentLanguage((Activity) getContext())
//                            .getMaNgonNgu().toString() };
//
//            Cursor cursor = getContext().getContentResolver().query(
//                    MonAnContract.URI_MONAN_INNER_DANGONNGU, projection, selection,
//                    selectionArgs, null);
//
//            cursor.moveToFirst();
//            mMonAn = MonAnDaNgonNguDTO.extractFrom(cursor);
//
//            selection = DonViTinhMonAnContract.CL_MA_MON_AN + "=? and "
//                    + DonViTinhDaNgonNguContract.CL_MA_NGON_NGU + "=? and "
//                    + DonViTinhMonAnContract.TABLE_NAME + "."
//                    + DonViTinhMonAnContract.CL_MA_DON_VI + "=?";
//
//            selectionArgs = new String[] {
//                    mMonAn.getMaMonAn().toString(),
//                    MyAppLocale.getCurrentLanguage((Activity) getContext())
//                            .getMaNgonNgu().toString(),
//                    mChiTietOrder.getMaDonViTinh().toString() };
//
//            mDonViTinhCursor = getContext().getContentResolver().query(
//                    DonViTinhMonAnContract.URI_DONVITINHMONAN_INNER_DANGONNGU,
//                    projection, selection, selectionArgs, null);
//
//            mDonViTinhCursor.moveToFirst();
//            mTenDonViTinh = mDonViTinhCursor.getString(mDonViTinhCursor
//                    .getColumnIndex(DonViTinhDaNgonNguContract.CL_TEN_DON_VI));
//            mGiaDonViTinh = mDonViTinhCursor.getInt(mDonViTinhCursor
//                    .getColumnIndex(DonViTinhMonAnContract.CL_DON_GIA));
//
//            return null;
//        }
//    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == mButtonPlus.getId()) {
                Integer quantity = Integer.valueOf(mTextQuantity.getText().toString()) + 1;
                mTextQuantity.setText(quantity.toString());
                mChiTietOrder.setSoLuong(quantity);
            } else if (v.getId() == mButtonMinus.getId()) {
                Integer quantity = Integer.valueOf(mTextQuantity.getText().toString()) - 1;
                if (quantity > 0) {
                    mTextQuantity.setText(quantity.toString());
                    mChiTietOrder.setSoLuong(quantity);
                }
            }

            SessionManager.loadCurrentSession((Activity) getContext()).getOrder()
                    .debugLogItems();
        }
    };

    public OrderItemView(Context context) {
        super(context);

        

        inflatView(context);
//        bindData(chiTietOrder, monAnDaNgonNgu, donViTinhDaNgonNgu, donViTinhMonAn);

        // new LoadOrderItemAsyncTask(chiTietOrder).execute();
    }

    public OrderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflatView(context);
    }

    public OrderItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        inflatView(context);
    }

    private void inflatView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_order, this);
    }

    public void bindData(ChiTietOrderDTO chiTietOrder, MonAnDaNgonNguDTO monAnDaNgonNgu,
            DonViTinhDaNgonNguDTO donViTinhDaNgonNgu, DonViTinhMonAnDTO donViTinhMonAn) {
        mChiTietOrder = chiTietOrder;
        
        mButtonPlus = (Button) findViewById(R.id.btnPlus);
        mButtonMinus = (Button) findViewById(R.id.btnMinus);
        mButtonPlus.setOnClickListener(mOnClickListener);
        mButtonMinus.setOnClickListener(mOnClickListener);
        
        mTextQuantity = (TextView) findViewById(R.id.textQuantity);
        mTextQuantity.setText(mChiTietOrder.getSoLuong().toString());

        mDishNoteEditText = (EditText) findViewById(R.id.editDishNote);
        mDishNoteEditText.setText(mChiTietOrder.getGhiChu());

        mDishNameTextView = (TextView) findViewById(R.id.textDishName);
        mDishNameTextView.setText(monAnDaNgonNgu.getTenMonAn());

        mUnitNameTextView = (TextView) findViewById(R.id.textUnitName);
        mUnitNameTextView.setText(donViTinhDaNgonNgu.getTenDonViTinh());
        
        mUnitPriceTextView = (TextView) findViewById(R.id.textUnitPrice);
        mUnitPriceTextView.setText(donViTinhMonAn.getDonGia().toString());
    }
}
