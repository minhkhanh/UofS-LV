package client.menu.ui.fragment;

import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import client.menu.R;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.db.dto.DonViTinhDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;

public class DishDetailDialogFragment extends DialogFragment {

    private MonAnDTO mMonAn; // for further info
    private MonAnDaNgonNguDTO mMonAnDaNgonNgu;
    private SimpleCursorAdapter mUnitsAdapter;
    // private DonViTinhMonAnDTO mDonViTinhMonAn;
    private Integer mMaDonViTinh;

    private TextView mSelectedQuantityTextView;
    private TextView mUnitNameTextView;
    private TextView mUnitPriceTextView;
    private ListView mUnitsList;
    private EditText mNoteEditText;
    NumberPicker mNpickerQuantity;

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnOK:
                    ServiceOrder order = SessionManager.getInstance().loadCurrentSession()
                            .getOrder();

                    order.addItem(mMonAn.getMaMonAn(), mMaDonViTinh, mNpickerQuantity
                            .getValue(), mNoteEditText.getText().toString());

                    dismiss();
                    break;

                case R.id.btnCancel:
                    dismiss();
                    break;
            }
        }
    };

    public DishDetailDialogFragment(MonAnDTO monAn, MonAnDaNgonNguDTO monAnDaNgonNgu,
            Integer maDonViTinh, SimpleCursorAdapter unitsAdapter) {
        mMonAn = monAn;
        mMonAnDaNgonNgu = monAnDaNgonNgu;
        mUnitsAdapter = unitsAdapter;
        mMaDonViTinh = maDonViTinh;
        // mDonViTinhMonAn = maDonViTinh;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // getDialog().setTitle(getString(R.string.title_dialog_auth));
        getDialog().setCanceledOnTouchOutside(false);
        

        View layout = inflater.inflate(R.layout.dialog_dish_detail, container, false);

        return layout;
    }

    private void showDishPriceInfo(int pos) {
        Cursor cursor = mUnitsAdapter.getCursor();
        if (cursor.moveToPosition(pos)) {
            String unitName = cursor.getString(cursor
                    .getColumnIndex(DonViTinhDaNgonNguDTO.CL_TEN_DON_VI));
            Integer unitPrice = cursor.getInt(cursor
                    .getColumnIndex(DonViTinhMonAnDTO.CL_DON_GIA));

            mUnitNameTextView.setText(unitName);
            mUnitPriceTextView.setText(unitPrice.toString());

            mMaDonViTinh = cursor.getInt(cursor
                    .getColumnIndex(DonViTinhDTO.CL_MA_DON_VI_TINH));
        }
        
    }

    private void prepareListPrices() {
        mUnitsList = (ListView) getView().findViewById(R.id.listDishUnits);
        mUnitsList.setAdapter(mUnitsAdapter);
        mUnitsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                showDishPriceInfo(arg2);
            }

        });

        Cursor cursor = mUnitsAdapter.getCursor();
        cursor.moveToPosition(-1);
        int pos = -1;
        while (cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex(DonViTinhMonAnDTO.CL_MA_DON_VI)) == mMaDonViTinh) {
                pos = cursor.getPosition();
                break;
            }
        }

        if (pos != -1) {
            showDishPriceInfo(pos);
        }
    }

    private void prepareViews() {
        mSelectedQuantityTextView = (TextView) getView().findViewById(
                R.id.textSelectedQuantity);

        mNpickerQuantity = (NumberPicker) getView().findViewById(R.id.npickerQuantity);
        mNpickerQuantity.setMinValue(1);
        mNpickerQuantity.setMaxValue(99);
        mNpickerQuantity.setValue(1);
        mSelectedQuantityTextView.setText(String.valueOf(mNpickerQuantity.getValue()));

        mNpickerQuantity.setOnValueChangedListener(new OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mSelectedQuantityTextView.setText(String.valueOf(newVal));
            }
        });

        mUnitNameTextView = (TextView) getView().findViewById(R.id.textUnitName);
        mUnitPriceTextView = (TextView) getView().findViewById(R.id.textUnitPrice);

        prepareListPrices();

        TextView dishNameView = (TextView) getView().findViewById(R.id.textDishName);
        dishNameView.setText(mMonAnDaNgonNgu.getTenMonAn());

        TextView dishDescriptionView = (TextView) getView().findViewById(
                R.id.textDishDescription);
        dishDescriptionView.setText(mMonAnDaNgonNgu.getMoTaMonAn());

        Button btnOK = (Button) getView().findViewById(R.id.btnOK);
        btnOK.setOnClickListener(mOnClickListener);
        Button btnCancel = (Button) getView().findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(mOnClickListener);

        mNoteEditText = (EditText) getView().findViewById(R.id.editDishNote);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        prepareViews();
    }
}
