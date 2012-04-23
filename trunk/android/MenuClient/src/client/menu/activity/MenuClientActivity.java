package client.menu.activity;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import client.menu.R;
import client.menu.adapter.LocaleAdapter;
import client.menu.util.AppSettings;

public class MenuClientActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AppSettings.appLocale.applyLanguage(this);

		setContentView(R.layout.layout_main);

		Spinner spinner = (Spinner) findViewById(R.id.spinner1);

		Locale[] data = AppSettings.appLocale.getLocales();

		spinner.setAdapter(new LocaleAdapter(this, data));

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
//				Log.d("item selected", arg2 + "");
				Locale locale = (Locale) arg0.getSelectedItem();
				String lang = locale.getLanguage(); 

				if (AppSettings.appLocale.getLanguage().equals(lang) == false) {
					AppSettings.appLocale.setLanguage(lang);
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

	}

	public void onClick(View v) {
		if (v.getId() == R.id.TableListBtn) {
			Intent intent = new Intent(this, TableListActivity.class);
			startActivity(intent);
		} else if (v == findViewById(R.id.MainMenuBtn)) {
			Intent intent = new Intent(this, MainMenuActivity.class);
			startActivity(intent);
		} else if (v == findViewById(R.id.SplitTableBtn)) {
			Intent intent = new Intent(this, SplitTableActivity.class);
			startActivity(intent);
		}
	}
}