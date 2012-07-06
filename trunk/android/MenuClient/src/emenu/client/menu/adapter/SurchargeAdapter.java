package emenu.client.menu.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import emenu.client.db.dto.PhuThuDTO;
import emenu.client.menu.R;

public class SurchargeAdapter extends CustomArrayAdapter<PhuThuDTO> {

    private int mBillTotal;

    static class ViewHolder {
        TextView mName;
        TextView mValue;
    }

    public SurchargeAdapter(Context context, List<PhuThuDTO> data, int billTotal) {
        super(context, data);

        mBillTotal = billTotal;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_surcharge, null);

            holder = new ViewHolder();
            holder.mName = (TextView) convertView.findViewById(R.id.textSurchargeName);
            holder.mValue = (TextView) convertView.findViewById(R.id.textSurchargeVal);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PhuThuDTO s = getItem(position);
        holder.mName.setText(getItem(position).getTenPhuThu());
        holder.mValue.setText(calcItemSurcharge(mBillTotal, s) + "");

        return convertView;
    }

    public int calcSurchargeTotal() {
        int total = 0;
        List<PhuThuDTO> data = getData();
        for (PhuThuDTO s : data) {
            total += calcItemSurcharge(mBillTotal, s);
        }

        return total;
    }

    public static final int calcItemSurcharge(int billTotal, PhuThuDTO surcharge) {
        return surcharge.getGiaTang() + (int) (billTotal * surcharge.getTiLeTang());
    }

    public int getBillTotal() {
        return mBillTotal;
    }

    public void setBillTotal(int billTotal) {
        mBillTotal = billTotal;
    }

}
