package client.menu.adapter;

import java.util.Locale;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LocaleAdapter extends ArrayAdapter<Locale> {
	public LocaleAdapter(Context context, Locale[] data) {
		super(context, android.R.layout.simple_list_item_1, data);
	}
	
	private TextView getTextView(int position, View convertView, ViewGroup parent) {
		TextView textView = null;
		if (convertView == null) {
			textView = new TextView(getContext());
		} else {
			textView = (TextView) convertView;
		}

		textView.setText(getItem(position).getDisplayLanguage());
		textView.setPadding(10, 10, 10, 10);

		return textView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getTextView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getTextView(position, convertView, parent);
	}
}
