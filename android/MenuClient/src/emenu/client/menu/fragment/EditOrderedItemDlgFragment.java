package emenu.client.menu.fragment;

import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetOrderItemTask;
import emenu.client.bus.task.GetOrderedItemUpdateTask;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.menu.R;
import emenu.client.menu.activity.OrderActivity;
import emenu.client.menu.fragment.VoucherSearchDlgFragment.OnVoucherUsedListener;
import emenu.client.util.U;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class EditOrderedItemDlgFragment extends DialogFragment implements
        OnClickListener, TextWatcher {
    private EditText mVarQuantityEdit;
    private EditText mNoteEdit;
    private TextView mDishName;
    private TextView mUnprocCountText;

    private ContentValues mValues;

    private int mVarQuantity = 0;

    private ProgressDialog mWaitingDlg;

    private OnItemUpdatedListener mUpdateListener;

    public interface OnItemUpdatedListener {
        void onItemUpdated();
    }

    private OnPostExecuteListener<Integer, Void, ChiTietOrderDTO> mOnPostGetOrderItem = new OnPostExecuteListener<Integer, Void, ChiTietOrderDTO>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Integer, Void, ChiTietOrderDTO> task,
                ChiTietOrderDTO result) {
            if (result != null) {
                ContentValues c = result.toContentValues();
                mValues.putAll(c);
                bindData();
            }
        }
    };

    private OnPostExecuteListener<Void, Void, Boolean> mOnPostUpdateItem = new OnPostExecuteListener<Void, Void, Boolean>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Void, Void, Boolean> task,
                Boolean result) {
            mWaitingDlg.dismiss();

            if (result) {
                U.toastText(getActivity(), R.string.message_request_completed);
                dismiss();

                if (mUpdateListener != null)
                    mUpdateListener.onItemUpdated();
            } else {
                U.showErrorDialog(getActivity(),
                        R.string.message_ordered_item_not_updated);
                new GetOrderItemTask().setOnPostExecuteListener(mOnPostGetOrderItem)
                        .execute(getItemId());
            }
        }
    };

    public EditOrderedItemDlgFragment(ContentValues values) {
        mValues = values;
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
            mUpdateListener = (OnItemUpdatedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnItemUpdatedListener");
        }
    }

    private Integer getItemId() {
        return mValues.getAsInteger(ChiTietOrderDTO.CL_MA_CHI_TIET);
    }

    private int getQuantity() {
        return mValues.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_ordered_item_editing, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
        bindData();
    }

    private int calcUnprocessedCount() {
        return getQuantity() - calcProcessedCount();
    }

    private int calcProcessedCount() {
        return mValues.getAsInteger(ChiTietOrderDTO.CL_EX_PROCESSED)
                + mValues.getAsInteger(ChiTietOrderDTO.CL_EX_PROCESSING);
    }

    private void bindData() {
        mDishName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
        mUnprocCountText.setText(calcUnprocessedCount() + "");
        mVarQuantityEdit.setText(mVarQuantity + "");
        mNoteEdit.setText(mValues.getAsString(ChiTietOrderDTO.CL_GHI_CHU));
    }

    private void initViews() {
        getView().findViewById(R.id.btnMinus).setOnClickListener(this);
        getView().findViewById(R.id.btnPlus).setOnClickListener(this);
        getView().findViewById(R.id.btnOK).setOnClickListener(this);
        getView().findViewById(R.id.btnCancel).setOnClickListener(this);

        mVarQuantityEdit = (EditText) getView().findViewById(R.id.editVarQuantity);
        mVarQuantityEdit.addTextChangedListener(this);

        mNoteEdit = (EditText) getView().findViewById(R.id.editItemNote);
        mDishName = (TextView) getView().findViewById(R.id.textDishName);
        mUnprocCountText = (TextView) getView().findViewById(R.id.textUnprocCount);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.btnPlus:
                if (mVarQuantity + 1 <= 99)
                    ++mVarQuantity;
                mVarQuantityEdit.setText(mVarQuantity + "");
                break;

            case R.id.btnMinus:
                if (mVarQuantity - 1 >= -99)
                    --mVarQuantity;
                mVarQuantityEdit.setText(mVarQuantity + "");
                break;

            case R.id.btnCancel:
                dismiss();
                break;

            case R.id.btnOK:
                int newQuantity = getQuantity() + mVarQuantity;
                if (newQuantity < calcProcessedCount()) {
                    U.showErrorDialog(getActivity(),
                            R.string.message_new_quantity_lt_processed_item);
                } else {
                    mWaitingDlg = ProgressDialog.show(getActivity(), null,
                            getString(R.string.message_waiting));

                    Integer itemId = getItemId();
                    String itemNote = mNoteEdit.getText().toString();
                    new GetOrderedItemUpdateTask(itemId, newQuantity, itemNote)
                            .setOnPostExecuteListener(mOnPostUpdateItem).execute();
                }
                break;

            default:
                break;
        }
    }

    // TEXT CHANGED LISTENERS FOR VAR QUANTITY
    @Override
    public void afterTextChanged(Editable arg0) {
        mVarQuantity = Integer.valueOf(mVarQuantityEdit.getText().toString());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
