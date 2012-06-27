package emenu.client.menu.ui.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import emenu.client.menu.db.dto.ChiTietOrderDTO;
import emenu.client.menu.db.dto.DonViTinhMonAnDTO;
import emenu.client.menu.db.dto.KhuyenMaiDTO;
import emenu.client.menu.ui.view.BillItemView;

public class BillAdapter extends CustomArrayAdapter<ContentValues> {

    public BillAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BillItemView v = (BillItemView) convertView;
        if (v == null) {
            v = new BillItemView(getContext());
        }
        v.bindData(getData().get(position));

        return v;
    }

    public float getBillTotal() {
        float total = 0f;

        for (ContentValues c : getData()) {
            Integer quantity = c.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);

            Integer unitPrice = c.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);
            Float promValue = c.getAsFloat(KhuyenMaiDTO.CL_GIA_GIAM);
            Float promRate = c.getAsFloat(KhuyenMaiDTO.CL_TI_LE_GIAM);
            if (promValue == null || promRate == null) {
                total += unitPrice * quantity;
            } else {
                total += unitPrice * quantity * (100 - promRate) - promValue;
            }
        }

        return total;
    }
}
