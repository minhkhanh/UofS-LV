package client.menu.adapter;

import client.menu.R;
import client.menu.dto.MonAnDTO;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MonAnDtoAdapter extends ArrayAdapter<MonAnDTO> {
	
	public MonAnDtoAdapter(Context _context, int resId,
			MonAnDTO[] objects) {
		super(_context, resId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View row = inflater.inflate(R.layout.mon_an_row_layout, parent, false);
		ImageView dishImage = (ImageView)row.findViewById(R.id.AnhMonAn);
		TextView dishRate = (TextView) row.findViewById(R.id.DanhGiaMonAn);
		
		dishImage.setBackgroundResource(R.drawable.green_square);
		dishRate.setText("" + getItem(position).getDiemDanhGia());
		
		return row;
	}
	
}
