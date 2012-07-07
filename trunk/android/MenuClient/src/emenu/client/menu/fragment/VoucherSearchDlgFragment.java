package emenu.client.menu.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.dao.VoucherDAO;
import emenu.client.db.dto.VoucherDTO;
import emenu.client.util.U;

public class VoucherSearchDlgFragment extends DialogFragment implements OnClickListener {

    private TextView mNameText;
    private TextView mInfoText;
    private TextView mValueText;
    private TextView mStateText;

    private ContentValues mVoucherValues;

    private EditText mCodeEdit;
    private int mBillTotal;

    private OnVoucherUsedListener mOnUseVoucher;

    public interface OnVoucherUsedListener {
        void onVoucherUsed(ContentValues values);
    }

    class GetVoucherUsedTask extends CustomAsyncTask<String, Void, Boolean> {
        ProgressDialog mDlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDlg = ProgressDialog.show(getActivity(), "",
                    getString(R.string.message_waiting), true, false);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                return VoucherDAO.getInstance().getVoucherUsed(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mDlg.dismiss();

            if (result) {
                String voucherCode = mCodeEdit.getText().toString();
                mVoucherValues.put(VoucherDTO.CL_EX_VOUCHER_CODE, voucherCode);
                mOnUseVoucher.onVoucherUsed(mVoucherValues);
                
                updateFormText("", "", "", "", "");
                U.toastText(getActivity(), R.string.message_voucher_comsumed);
                
                mVoucherValues = null;
            } else {
                U.showErrorDialog(getActivity(), R.string.text_voucher_invalidated);
            }
        }
    }

    class GetVoucherInfoTask extends CustomAsyncTask<String, Void, ContentValues> {

        @Override
        protected ContentValues doInBackground(String... params) {
            ContentValues result = null;
            try {
                VoucherDTO voucher = VoucherDAO.getInstance().getVoucher(params[0],
                        mBillTotal);
                if (voucher != null) {
                    result = voucher.toContentValues();
                    result.put(VoucherDTO.CL_EX_VOUCHER_CODE, params[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(ContentValues result) {
            super.onPostExecute(result);

            if (result != null) {
                updateFormText(null, result.getAsString(VoucherDTO.CL_TEN_VOUCHER),
                        result.getAsString(VoucherDTO.CL_MO_TA),
                        result.getAsFloat(VoucherDTO.CL_GIA_GIAM).toString(),
                        getString(R.string.text_voucher_validated));

                mVoucherValues = result;
            } else {
                updateFormText(null, "",
                        getString(R.string.message_please_check_voucher_again), "",
                        getString(R.string.text_voucher_invalidated));

                mVoucherValues = null;
            }
        }
    }

    public VoucherSearchDlgFragment(int billTotal) {
        mBillTotal = billTotal;
    }

    private void updateFormText(String key, String name, String info, String value,
            String state) {
        if (key != null)
            mCodeEdit.setText(key);
        if (name != null)
            mNameText.setText(name);
        if (info != null)
            mInfoText.setText(info);
        if (value != null)
            mValueText.setText(value);
        if (state != null)
            mStateText.setText(state);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mOnUseVoucher = (OnVoucherUsedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnUseVoucherListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(R.string.text_voucher_usage);
        return inflater.inflate(R.layout.layout_voucher_search, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btnSearch = (Button) getView().findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        Button btnUseVoucher = (Button) getView().findViewById(R.id.btnUseVoucher);
        btnUseVoucher.setOnClickListener(this);
        Button btnExit = (Button) getView().findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);

        mCodeEdit = (EditText) getView().findViewById(R.id.editCode);
        mNameText = (TextView) getView().findViewById(R.id.textName);
        mInfoText = (TextView) getView().findViewById(R.id.textInfo);
        mValueText = (TextView) getView().findViewById(R.id.textValue);
        mStateText = (TextView) getView().findViewById(R.id.textState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnExit:
                dismiss();
                break;

            case R.id.btnSearch:
                String code = mCodeEdit.getText().toString();
                new GetVoucherInfoTask().execute(code);
                break;

            case R.id.btnUseVoucher:
                if (mVoucherValues != null) {
//                    code = mVoucherValues.getAsString(VoucherDTO.CL_EX_VOUCHER_CODE);
//                    String voucherCode = mCodeEdit.getText().toString();
//                    mVoucherValues.put(VoucherDTO.CL_EX_VOUCHER_CODE, voucherCode);
                    mOnUseVoucher.onVoucherUsed(mVoucherValues);
//                    new GetVoucherUsedTask().execute(code);
                } else {
                    U.toastText(getActivity(), R.string.message_please_set_one_voucher);
                }
                break;

            default:
                break;
        }
    }
}
