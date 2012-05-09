package client.menu.ui.activity;

import client.menu.R;
import client.menu.ui.fragment.AuthDialogFragment;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplitTableActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_split_table);

		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				Fragment prev = getFragmentManager()
						.findFragmentByTag("dialog");
				if (prev != null) {
					ft.remove(prev);
				}

				DialogFragment newFragment = AuthDialogFragment.newInstance();
				newFragment.show(ft, "dialog");
			}
		});
	}
}
