package client.menu.activity;

import client.menu.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuClientActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		if (v == findViewById(R.id.TableListBtn)) {
			Intent intent = new Intent(this, TableListActivity.class);
			startActivity(intent);
		} else if (v == findViewById(R.id.MainMenuBtn)) {
			Intent intent = new Intent(this, MainMenuActivity.class);
			startActivity(intent);
		}
	}
}