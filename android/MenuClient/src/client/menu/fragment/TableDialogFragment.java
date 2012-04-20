package client.menu.fragment;

import client.menu.R;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TableDialogFragment extends DialogFragment {

	public static TableDialogFragment newInstance() {
		TableDialogFragment f = new TableDialogFragment();

		return f;
	}

	private static String[] createListItem() {
		return new String[] { "Order", "Tách bàn" };
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle("Table Dialog");

		View layout = inflater.inflate(R.layout.dialog_table, container, false);
		ListView list = (ListView) layout
				.findViewById(R.id.DialogSelectionList);
		list.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1, createListItem()));
		
		return layout;
	}
}
