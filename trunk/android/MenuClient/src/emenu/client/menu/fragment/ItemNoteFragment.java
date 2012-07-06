package emenu.client.menu.fragment;

import emenu.client.menu.R;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.OrderItemId;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhDTO;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemNoteFragment extends DialogFragment implements OnClickListener {
    EditText mNoteEdit;
    private ContentValues mValues;
    private OrderItemId mItemId;

    public ItemNoteFragment(ContentValues values) {
        mValues = values;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_item_note, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
        bindData();
    }

    private void bindData() {
        TextView dishName = (TextView) getView().findViewById(R.id.textDishName);
        dishName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));

        mNoteEdit.setText(mValues.getAsString(ChiTietOrderDTO.CL_GHI_CHU));
    }

    private void initViews() {
        mItemId = new OrderItemId(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN),
                mValues.getAsInteger(DonViTinhDTO.CL_MA_DON_VI_TINH));

        mNoteEdit = (EditText) getView().findViewById(R.id.editItemNote);

        Button btnCancel = (Button) getView().findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        Button btnOK = (Button) getView().findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                dismiss();
                break;

            case R.id.btnOK:
                String note = mNoteEdit.getText().toString();
                mValues.put(ChiTietOrderDTO.CL_GHI_CHU, note);

                SessionManager.getInstance().loadCurrentSession().getOrder()
                        .getItem(mItemId).setGhiChu(note);

                dismiss();
                break;
        }
    }
}
