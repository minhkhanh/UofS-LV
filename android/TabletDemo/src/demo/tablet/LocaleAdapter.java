package demo.tablet;

import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LocaleAdapter extends ArrayAdapter<Locale> {
	public LocaleAdapter(Context context, Locale[] data) {
		super(context, R.layout.row_locale, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			row = inflater.inflate(R.layout.row_locale, null);
		}

		Locale locale = getItem(position);

		TextView tv01 = (TextView) row.findViewById(R.id.tv01);
		tv01.setText(locale.getCountry());
		TextView tv02 = (TextView) row.findViewById(R.id.tv02);
		tv02.setText(locale.getDisplayCountry());
		TextView tv03 = (TextView) row.findViewById(R.id.tv03);
		tv03.setText(locale.getDisplayLanguage());
		TextView tv04 = (TextView) row.findViewById(R.id.tv04);
		tv04.setText(locale.getDisplayName());
		TextView tv05 = (TextView) row.findViewById(R.id.tv05);
		tv05.setText(locale.getDisplayVariant());
		TextView tv06 = (TextView) row.findViewById(R.id.tv06);
		tv06.setText(locale.getLanguage());
		TextView tv07 = (TextView) row.findViewById(R.id.tv07);
		tv07.setText(locale.getVariant());

		return row;
	}
}
