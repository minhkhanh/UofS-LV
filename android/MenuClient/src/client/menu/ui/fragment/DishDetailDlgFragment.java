package client.menu.ui.fragment;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
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
import client.menu.ui.adapter.DishUnitsAdapter;

public class DishDetailDlgFragment extends DialogFragment {

    private TextView mSelectedQuantityTextView;
    private TextView mUnitName;
    private TextView mUnitPrice;
    private ListView mUnitsList;
    private EditText mNoteEdit;
    private NumberPicker mNpickerQuantity;
    private TextView mDishName;
    private TextView mDishDescript;
    private RatingBar mDishRate;

    private ContentValues mValues;
    private DishUnitsAdapter mUnitsAdapter;

    private int mSelectedUnit;

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnOK:
                    ContentValues c = (ContentValues) mUnitsList.getSelectedItem();

                    ServiceOrder order = SessionManager.getInstance()
                            .loadCurrentSession().getOrder();

                    order.addItem(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN),
                            c.getAsInteger(DonViTinhDTO.CL_MA_DON_VI_TINH),
                            mNpickerQuantity.getValue(), mNoteEdit.getText().toString());

                    dismiss();
                    break;

                case R.id.btnCancel:
                    dismiss();
                    break;
            }
        }
    };

    public DishDetailDlgFragment(ContentValues v, DishUnitsAdapter unitsAdapter,
            int selectedUnit) {
        mValues = v;
        mUnitsAdapter = unitsAdapter;
        mSelectedUnit = selectedUnit;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
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
        ContentValues c = mUnitsAdapter.getItem(pos);

        String unitName = c.getAsString(DonViTinhDaNgonNguDTO.CL_TEN_DON_VI);
        Integer unitPrice = c.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);

        mUnitName.setText(unitName);
        mUnitPrice.setText(unitPrice.toString());
    }

    private void bindData() {
        mDishName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
        mDishDescript.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_MO_TA_MON));
        mDishRate.setRating(mValues.getAsFloat(MonAnDTO.CL_DIEM_DANH_GIA));

        mUnitsList.setAdapter(mUnitsAdapter);
        showDishPriceInfo(mSelectedUnit);
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

        mUnitsList = (ListView) getView().findViewById(R.id.listDishUnits);
        mUnitsList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                showDishPriceInfo(arg2);
            }
        });

        mUnitName = (TextView) getView().findViewById(R.id.textUnitName);
        mUnitPrice = (TextView) getView().findViewById(R.id.textUnitPrice);

        mDishName = (TextView) getView().findViewById(R.id.textDishName);
        mDishDescript = (TextView) getView().findViewById(R.id.textDishDescription);

        Button btnOK = (Button) getView().findViewById(R.id.btnOK);
        btnOK.setOnClickListener(mOnClickListener);
        Button btnCancel = (Button) getView().findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(mOnClickListener);

        mNoteEdit = (EditText) getView().findViewById(R.id.editDishNote);
        mDishRate = (RatingBar) getView().findViewById(R.id.rateDish);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        prepareViews();
        bindData();
    }
}
