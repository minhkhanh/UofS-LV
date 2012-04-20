package client.menu.fragment;

import client.menu.R;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TableDialogFragment extends DialogFragment {

	public static TableDialogFragment newInstance() {
		TableDialogFragment f = new TableDialogFragment();

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.layout_two_fragment, container);		
		
		return layout;
	}
}
