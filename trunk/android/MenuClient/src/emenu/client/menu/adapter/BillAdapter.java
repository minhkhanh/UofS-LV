package emenu.client.menu.adapter;

import java.util.List;

import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhMonAnDTO;
import emenu.client.db.dto.KhuyenMaiDTO;

import android.content.ContentValues;
import android.content.Context;

public abstract class BillAdapter extends CustomArrayAdapter<ContentValues> {

    public BillAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    public static final int calcItemProm(int totalWithoutProm, ContentValues c) {
        if (c.getAsInteger(KhuyenMaiDTO.CL_MA_KHUYEN_MAI) != null) {
            int promValue = c.getAsInteger(KhuyenMaiDTO.CL_GIA_GIAM);
            float promRate = c.getAsFloat(KhuyenMaiDTO.CL_TI_LE_GIAM);

            return (int) (totalWithoutProm * promRate) + promValue;
        }

        return 0;
    }

    public int calcBillTotal() {
        int total = 0;

        List<ContentValues> data = getData();
        for (ContentValues c : data) {
            int quantity = c.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG);
            int unitPrice = c.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);
            int totalWithoutProm = quantity * unitPrice;
            total += totalWithoutProm - calcItemProm(totalWithoutProm, c);
        }

        return total;
    }
}
