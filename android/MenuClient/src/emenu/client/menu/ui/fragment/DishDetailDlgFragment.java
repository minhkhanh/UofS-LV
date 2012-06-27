package emenu.client.menu.ui.fragment;

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
import client.menu.R;
import emenu.client.db.dto.DonViTinhDTO;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.menu.app.MyAppLocale;
import emenu.client.menu.app.MyApplication;
import emenu.client.menu.bus.SessionManager;
import emenu.client.menu.bus.SessionManager.ServiceOrder;
import emenu.client.menu.bus.task.CustomAsyncTask;
import emenu.client.menu.dao.MonLienQuanDAO;
import emenu.client.menu.ui.adapter.DishUnitsAdapter;
import emenu.client.menu.ui.adapter.RelatedDishesAdapter;
import emenu.client.menu.util.U;

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

    class ListRelatedDishesTask extends
            CustomAsyncTask<Integer, Void, List<ContentValues>> {
        Integer mLanguageId = MyAppLocale.getCurrentLanguage(MyApplication.getInstance())
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

    private int mSelectedUnit;
    private int mCurrQuantity = 1;

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

        mUnitsSpinner.setAdapter(mUnitsAdapter);
        mUnitsSpinner.setSelection(mSelectedUnit);

        byte[] imgData = mValues.getAsByteArray(MonAnDTO.CL_HINH_ANH);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
        mAvatar.setImageBitmap(decodedBitmap);

        mQuantityText.setText(mCurrQuantity + "");
        new ListRelatedDishesTask().execute(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN));
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
