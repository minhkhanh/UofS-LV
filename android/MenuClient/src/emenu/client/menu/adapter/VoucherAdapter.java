package emenu.client.menu.adapter;

import java.util.ArrayList;
import java.util.List;

import emenu.client.menu.R;
import emenu.client.db.dto.VoucherDTO;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VoucherAdapter extends CustomArrayAdapter<ContentValues> {

    static class ViewHolder {
        TextView mCodeText;
        TextView mValueText;
    }

    public VoucherAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_voucher, null);

            holder = new ViewHolder();
            holder.mCodeText = (TextView) convertView.findViewById(R.id.textCode);
            holder.mValueText = (TextView) convertView.findViewById(R.id.textValue);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mCodeText.setText(getItem(position).getAsString(
                VoucherDTO.CL_EX_VOUCHER_CODE));
        holder.mValueText.setText("-"
                + getItem(position).getAsInteger(VoucherDTO.CL_GIA_GIAM).toString());

        return convertView;
    }

    public List<String> getAllVoucherCodes() {
        List<String> list = new ArrayList<String>();
        for (ContentValues c : getData()) {
            String code = c.getAsString(VoucherDTO.CL_EX_VOUCHER_CODE);
            list.add(code);
        }

        return list;
    }

    public int calcVoucherTotal() {
        int total = 0;
        List<ContentValues> data = getData();
        for (ContentValues c : data) {
            total += c.getAsInteger(VoucherDTO.CL_GIA_GIAM);
        }

        return total;
    }
}
