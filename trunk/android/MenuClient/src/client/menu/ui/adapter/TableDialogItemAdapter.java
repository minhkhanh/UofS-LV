package client.menu.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class TableDialogItemAdapter extends ArrayAdapter<String> {
	public TableDialogItemAdapter(Context context, String[] data) {
		super(context, android.R.layout.simple_list_item_activated_1, data);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = null;
		
		return v;
	}
}
