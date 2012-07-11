package emenu.client.menu.fragment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.LoadDishUnitsTask;
import emenu.client.dao.MonLienQuanDAO;
import emenu.client.db.dto.DonViTinhDTO;
import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.menu.adapter.DishUnitsAdapter;
import emenu.client.menu.adapter.RelatedDishesAdapter;
import emenu.client.menu.app.CustomerLocale;
import emenu.client.menu.app.MenuApplication;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceOrder;
import emenu.client.util.U;

public class DishDetailDlgFragment extends DialogFragment implements OnClickListener,
        TextWatcher {

    private EditText mNoteEdit;
    private TextView mDishName;
    private TextView mDishDescript;
    private RatingBar mDishRate;
    private ImageView mAvatar;

    private Spinner mUnitsSpinner;
    private Button mDecrQuantityBtn;
    private Button mIncrQuantityBtn;
    private EditText mQuantityText;

    private ContentValues mValues;
    private DishUnitsAdapter mUnitsAdapter;

    private Gallery mRelatedDishes;
    private RelatedDishesAdapter mGalleryAdapter;

    private int mSelectedUnit;
    private int mCurrQuantity = 1;

    class ListRelatedDishesTask extends
            CustomAsyncTask<Integer, Void, List<ContentValues>> {
        Integer mLanguageId = MenuApplication.getInstance().customerLocale.getLanguage()
                .getMaNgonNgu();

        @Override
        protected List<ContentValues> doInBackground(Integer... params) {
            try {
                return MonLienQuanDAO.getInstance().listContentByDishId(mLanguageId,
                        params[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<ContentValues>();
            }
        }

        @Override
        protected void onPostExecute(List<ContentValues> result) {
            super.onPostExecute(result);

            if (result.size() > 0) {
                mGalleryAdapter.clear();
                mGalleryAdapter.addAll(result);
                mGalleryAdapter.notifyDataSetChanged();
            }
        }
    }

    private OnPostExecuteListener<Integer, Void, List<ContentValues>> mOnPostLoadDishUnits = new OnPostExecuteListener<Integer, Void, List<ContentValues>>() {
        @Override
        public void onPostExecute(
                CustomAsyncTask<Integer, Void, List<ContentValues>> task,
                List<ContentValues> result) {
            mUnitsAdapter.clear();
            mUnitsAdapter.addAll(result);
            mUnitsAdapter.notifyDataSetChanged();
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

        View layout = inflater.inflate(R.layout.layout_dish_detail, container, false);

        return layout;
    }

    private void bindData() {
        mDishName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
        mDishDescript.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_MO_TA_MON));
        mDishRate.setRating(mValues.getAsFloat(MonAnDTO.CL_DIEM_DANH_GIA));

        if (mUnitsAdapter == null) {
            mUnitsAdapter = new DishUnitsAdapter(getActivity(),
                    new ArrayList<ContentValues>());
            new LoadDishUnitsTask().setOnPostExecuteListener(mOnPostLoadDishUnits)
                    .execute(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN));
        }
        mUnitsSpinner.setAdapter(mUnitsAdapter);
        mUnitsSpinner.setSelection(mSelectedUnit);

        U.setDishAvatar(mValues, mAvatar);

        mQuantityText.setText(mCurrQuantity + "");
        new ListRelatedDishesTask().execute(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN));

        ViewGroup panePromotion = (ViewGroup) getView().findViewById(R.id.panePromotion);
        if (mValues.getAsInteger(KhuyenMaiDTO.CL_MA_KHUYEN_MAI) != null) {
            panePromotion.setVisibility(View.VISIBLE);

            Integer promPrice = mValues.getAsInteger(KhuyenMaiDTO.CL_GIA_GIAM);
            TextView textPromVal = (TextView) getView().findViewById(R.id.textPromValue);
            if (promPrice != 0) {
                textPromVal.setText(promPrice.toString());
            } else {
                int promPercent = (int) (mValues.getAsFloat(KhuyenMaiDTO.CL_TI_LE_GIAM) * 100);
                textPromVal.setText(promPercent + "%");
            }

            long from = mValues.getAsLong(KhuyenMaiDTO.CL_BAT_DAU);
            long to = mValues.getAsLong(KhuyenMaiDTO.CL_KET_THUC);

            TextView textFrom = (TextView) getView().findViewById(R.id.textFrom);
            TextView textTo = (TextView) getView().findViewById(R.id.textTo);

            textFrom.setText(U.formatDateTime("MM/dd/yyyy", from));
            textTo.setText(U.formatDateTime("MM/dd/yyyy", to));
        } else {
            panePromotion.setVisibility(View.INVISIBLE);
        }
    }

    private void initViews() {
        mAvatar = (ImageView) getView().findViewById(R.id.imgDishAvatar);

        mUnitsSpinner = (Spinner) getView().findViewById(R.id.spinUnits);

        mDishName = (TextView) getView().findViewById(R.id.textDishName);
        mDishDescript = (TextView) getView().findViewById(R.id.textDishDescription);

        Button btnOK = (Button) getView().findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
        Button btnCancel = (Button) getView().findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        mNoteEdit = (EditText) getView().findViewById(R.id.editDishNote);
        mDishRate = (RatingBar) getView().findViewById(R.id.rateDish);

        mUnitsSpinner = (Spinner) getView().findViewById(R.id.spinUnits);

        mIncrQuantityBtn = (Button) getView().findViewById(R.id.btnPlus);
        mIncrQuantityBtn.setOnClickListener(this);

        mDecrQuantityBtn = (Button) getView().findViewById(R.id.btnMinus);
        mDecrQuantityBtn.setOnClickListener(this);

        mQuantityText = (EditText) getView().findViewById(R.id.editQuantity);
        mQuantityText.addTextChangedListener(this);

        mRelatedDishes = (Gallery) getView().findViewById(R.id.gallery1);
        mGalleryAdapter = new RelatedDishesAdapter(getActivity(),
                new ArrayList<ContentValues>());
        mRelatedDishes.setAdapter(mGalleryAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
        bindData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                ContentValues c = mUnitsAdapter.getItem(mUnitsSpinner
                        .getSelectedItemPosition());

                ServiceOrder order = SessionManager.getInstance().loadCurrentSession()
                        .getOrder();

                order.addItem(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN),
                        c.getAsInteger(DonViTinhDTO.CL_MA_DON_VI_TINH), mCurrQuantity,
                        mNoteEdit.getText().toString());

                U.toastText(getActivity(), getString(R.string.message_order_item_added)
                        + ": " + mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));

                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;

            case R.id.btnMinus:
                if (mCurrQuantity > 1) {
                    --mCurrQuantity;
                    mQuantityText.setText(mCurrQuantity + "");
                }
                break;

            case R.id.btnPlus:
                if (mCurrQuantity < 99) {
                    ++mCurrQuantity;
                    mQuantityText.setText(mCurrQuantity + "");
                }
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String strNum = mQuantityText.getText().toString();
        if (strNum.compareTo("") != 0)
            mCurrQuantity = Integer.valueOf(strNum);
    }
}
