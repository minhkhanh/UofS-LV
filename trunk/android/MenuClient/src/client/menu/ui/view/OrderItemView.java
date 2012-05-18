package client.menu.ui.view;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import client.menu.R;
import client.menu.app.MyAppLocale;
import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.MonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;

public class OrderItemView extends RelativeLayout {
    private ChiTietOrderDTO mChiTietOrder;

    private Button mButtonPlus;
    private Button mButtonMinus;
    private TextView mTextQuantity;
    private Spinner mUnitSpinner;
    private TextView mDishNameTextView;
    private EditText mDishNoteEditText;

    private MonAnDaNgonNguDTO mMonAnDaNgonNgu; // reserved

    private SimpleCursorAdapter mUnitSpinnerAdapter;

    private class LoadOrderItemAsyncTask extends AsyncTask<Void, Integer, Void> {
        private ChiTietOrderDTO mChiTietOrder;
        private Cursor mDonViTinhMonAnCursor;
        private MonAnDaNgonNguDTO mMonAn;
        private int mSelectedIndex = -1;

        public LoadOrderItemAsyncTask(ChiTietOrderDTO chiTietOrder) {
            mChiTietOrder = chiTietOrder;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            mUnitSpinnerAdapter.swapCursor(mDonViTinhMonAnCursor);
            mUnitSpinner.setSelection(mSelectedIndex);

            mMonAnDaNgonNgu = mMonAn;

            mDishNameTextView.setText(mMonAn.getTenMonAn());
        }

        @Override
        protected Void doInBackground(Void... params) {
            String[] projection = null;
            String selection = MonAnContract.CL_MA_MON_AN_QN + "=? and "
                    + MonAnDaNgonNguContract.CL_MA_NGON_NGU + "=?";
            String[] selectionArgs = {
                    mChiTietOrder.getMaMonAn().toString(),
                    MyAppLocale.getCurrentLanguage((Activity) getContext())
                            .getMaNgonNgu().toString() };

            Cursor cursor = getContext().getContentResolver().query(
                    MonAnContract.URI_MONAN_INNER_DANGONNGU, projection, selection,
                    selectionArgs, null);

            cursor.moveToFirst();
            mMonAn = MonAnDaNgonNguDTO.extractFrom(cursor);

            selection = DonViTinhMonAnContract.CL_MA_MON_AN + "=? and "
                    + DonViTinhDaNgonNguContract.CL_MA_NGON_NGU + "=?";

            selectionArgs = new String[] {
                    mMonAn.getMaMonAn().toString(),
                    MyAppLocale.getCurrentLanguage((Activity) getContext())
                            .getMaNgonNgu().toString() };

            mDonViTinhMonAnCursor = getContext().getContentResolver().query(
                    DonViTinhMonAnContract.URI_DONVITINHMONAN_INNER_DANGONNGU,
                    projection, selection, selectionArgs, null);

            if (mDonViTinhMonAnCursor == null) {
                mSelectedIndex = -1;
            } else {
                while (mDonViTinhMonAnCursor.moveToNext()) {
                    if (mDonViTinhMonAnCursor.getInt(mDonViTinhMonAnCursor
                            .getColumnIndex(DonViTinhMonAnContract.CL_MA_DON_VI)) == mChiTietOrder
                            .getMaDonViTinh()) {
                        mSelectedIndex = mDonViTinhMonAnCursor.getPosition();
                        break;
                    }
                }

                cursor.moveToPosition(-1);
            }

            return null;
        }
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == mButtonPlus.getId()) {
                Integer quantity = Integer.valueOf(mTextQuantity.getText().toString()) + 1;
                mTextQuantity.setText(quantity.toString());
            } else if (v.getId() == mButtonMinus.getId()) {
                Integer quantity = Integer.valueOf(mTextQuantity.getText().toString()) - 1;
                if (quantity > 0) {
                    mTextQuantity.setText(quantity.toString());
                }
            }
        }
    };

    public OrderItemView(Context context, ChiTietOrderDTO chiTietOrder) {
        super(context);

        mChiTietOrder = chiTietOrder;

        inflatView(context);
        prepareWidgets();

        new LoadOrderItemAsyncTask(chiTietOrder).execute();
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

    private void prepareWidgets() {
        mButtonPlus = (Button) findViewById(R.id.btnPlus);
        mButtonMinus = (Button) findViewById(R.id.btnMinus);

        mTextQuantity = (TextView) findViewById(R.id.textQuantity);
        mTextQuantity.setText(mChiTietOrder.getSoLuong().toString());

        mDishNoteEditText = (EditText) findViewById(R.id.editDishNote);
        mDishNoteEditText.setText(mChiTietOrder.getGhiChu());

        mDishNameTextView = (TextView) findViewById(R.id.textDishName);

        mUnitSpinner = (Spinner) findViewById(R.id.spinDishPrices);
        mUnitSpinnerAdapter = new SimpleCursorAdapter(getContext(),
                R.layout.item_dish_units_spinner, null, new String[] {
                        DonViTinhDaNgonNguContract.CL_TEN_DON_VI,
                        DonViTinhMonAnContract.CL_DON_GIA }, new int[] {
                        R.id.textUnitName, R.id.textUnitPrice }, 0);
        mUnitSpinner.setAdapter(mUnitSpinnerAdapter);

        mButtonPlus.setOnClickListener(mOnClickListener);
        mButtonMinus.setOnClickListener(mOnClickListener);
    }
}
