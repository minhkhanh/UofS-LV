package client.menu.adapter;

import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import client.menu.R;
import client.menu.dto.MonAnDTO;

public class DishListAdapter extends ArrayAdapter<MonAnDTO> {
	
	public DishListAdapter(Context context, MonAnDTO[] objects) {
		super(context, R.layout.row_dish_list, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		HashMap<String, View> map = null;
		
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			row = inflater.inflate(R.layout.row_dish_list, null);
			map = new HashMap<String, View>();
			map.put("dishImage", row.findViewById(R.id.AnhMonAn));
			map.put("dishRate", row.findViewById(R.id.DanhGiaMonAn));
			row.setTag(map);
		} else {
			map = (HashMap<String, View>) row.getTag();
		}

		ImageView dishImage = (ImageView) map.get("dishImage");
		TextView dishRate = (TextView) map.get("dishRate");
	
		dishImage.setBackgroundResource(R.drawable.green_square);
		dishRate.setText("" + getItem(position).getDiemDanhGia());

		return row;
	}
}
