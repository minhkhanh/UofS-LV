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

public class DishListAdapter extends ArrayAdapter<MonAnDTO> {

	public DishListAdapter(Context context, MonAnDTO[] objects) {
		super(context, R.layout.row_dish_list, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View row = inflater.inflate(R.layout.row_dish_list, null);
		ImageView dishImage = (ImageView) row.findViewById(R.id.AnhMonAn);
		TextView dishRate = (TextView) row.findViewById(R.id.DanhGiaMonAn);

		dishImage.setBackgroundResource(R.drawable.green_square);
		dishRate.setText("" + getItem(position).getDiemDanhGia());

		return row;
	}

}
