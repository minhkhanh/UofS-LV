package emenu.client.menu.adapter;

import java.util.List;

import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.menu.view.BriefOrderChildView;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class MiniOrderAdapter extends OrderAdapter implements IDishSwappableAdapter {

    public MiniOrderAdapter(Context context, List<ContentValues> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BriefOrderChildView view;
        if (convertView == null) {
            view = new BriefOrderChildView(getContext());
        } else {
            view = (BriefOrderChildView) convertView;
        }

        view.bindData(getItem(position));

        return view;
    }

    @Override
    public void gather() {
        List<ContentValues> items = getData();
        for (int i = 0; i < items.size() - 1; ++i) {
            ContentValues chiTiet1 = items.get(i);
            for (int j = i + 1; j < items.size();) {
                ContentValues chiTiet2 = items.get(j);
                if (chiTiet1.getAsInteger(ChiTietOrderDTO.CL_MA_MON_AN) == chiTiet2
                        .getAsInteger(ChiTietOrderDTO.CL_MA_MON_AN)
                        && chiTiet1.getAsInteger(ChiTietOrderDTO.CL_MA_DON_VI_TINH) == chiTiet2
                                .getAsInteger(ChiTietOrderDTO.CL_MA_DON_VI_TINH)) {
                    chiTiet1.put(
                            ChiTietOrderDTO.CL_SO_LUONG,
                            chiTiet1.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG)
                                    + chiTiet2.getAsInteger(ChiTietOrderDTO.CL_SO_LUONG));

                    items.remove(j);
                } else {
                    ++j;
                }
            }
        }
        
        notifyDataSetChanged();
    }

}
