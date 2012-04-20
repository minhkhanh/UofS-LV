package client.menu.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import client.menu.R;
import client.menu.dto.BanDTO;

public class TableGridAdapter extends ArrayAdapter<BanDTO> {

	Activity context;

	public TableGridAdapter(Activity context, BanDTO[] data) {
		super(context, R.layout.item_table_grid, data);
		this.context = context;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		View gridItem = convertView;

		if (gridItem == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			gridItem = inflater.inflate(R.layout.item_table_grid, null);
		}

		TextView tableCaption = (TextView) gridItem.findViewById(R.id.TableCaption);
		tableCaption.setText(getItem(pos).getTenBan());

		return gridItem;
	}

}
