package client.menu.activity;

import java.util.Locale;

import client.menu.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MenuClientActivity extends Activity {

	String selLang = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// if (savedInstanceState != null) {
		// selLang = savedInstanceState.get("selLang").toString();
		// }

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			selLang = bundle.getString("selLang");
		}

		Configuration appConfig = getBaseContext().getResources()
				.getConfiguration();
		if (selLang != null && !selLang.equals(appConfig.locale.getLanguage())) {
			Locale appLoc = new Locale(selLang);
			Locale.setDefault(appLoc);
			appConfig.locale = appLoc;
			getBaseContext().getResources().updateConfiguration(appConfig,
					getBaseContext().getResources().getDisplayMetrics());
		}

		setContentView(R.layout.layout_main);

		Spinner spinner = (Spinner) findViewById(R.id.spinner1);

		String[] data = new String[] { "vi", "en", "ja" };

		spinner.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_activated_1, data));
		
		if (selLang != null) {
			for (int i = 0; i < data.length; ++i) {
				if (data[i].equals(selLang) && i != 0) {
					spinner.setSelection(i);
					break;
				}
			}
		}

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String lang = (String) arg0.getSelectedItem();

				if (!lang.equals(selLang)) {
					finish();
					Intent intent = new Intent(MenuClientActivity.this,
							MenuClientActivity.class);
					intent.putExtra("selLang", lang);
					startActivity(intent);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString("selLang", selLang);
	}

	public void onClick(View v) {
		if (v == findViewById(R.id.TableListBtn)) {
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