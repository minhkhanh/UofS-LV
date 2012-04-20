package client.menu.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import client.menu.R;
import client.menu.dto.BanDTO;

public class TableItemAdapter extends ArrayAdapter<BanDTO> {

	Activity context;

	public TableItemAdapter(Activity context, BanDTO[] data) {
		super(context, R.layout.table_grid_item, data);
		this.context = context;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		View gridItem = convertView;

		if (gridItem == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			gridItem = inflater.inflate(R.layout.table_grid_item, null);
		}

		TextView tableCaption = (TextView) gridItem.findViewById(R.id.TableCaption);
		tableCaption.setText(getItem(pos).getTenBan());

		return gridItem;
	}

}
