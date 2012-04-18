package client.menu.fragment;

import client.menu.R;
import client.menu.dto.KhuVucDTO;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AreaListFragment extends ListFragment {
	boolean isDualPane;
	int selIndex;

	private static KhuVucDTO[] fakeData() {
		KhuVucDTO[] khuVucArray = new KhuVucDTO[5];
		for (int i = 0; i < khuVucArray.length; ++i) {
			khuVucArray[i] = new KhuVucDTO();
			khuVucArray[i].setMaKhuVuc(i);
			khuVucArray[i].setTenKhuVuc("Khu vực " + i);
		}

		return khuVucArray;
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selIndex", selIndex);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new ArrayAdapter<KhuVucDTO>(getActivity(),
				android.R.layout.simple_list_item_activated_1, fakeData()));

		View detailsFrame = getActivity().findViewById(
				R.id.TableGridPlaceHolder);
		isDualPane = detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// Restore last state for checked position.
			selIndex = savedInstanceState.getInt("selIndex", 0);
		}

		if (isDualPane) {
			// In dual-pane mode, the list view highlights the selected item.
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			// Make sure our UI is in the correct state.
			showDetails(selIndex);
		}
	}

	void showDetails(int index) {
		selIndex = index;
		
		KhuVucDTO khuVuc = (KhuVucDTO) getListAdapter().getItem(index);
		
		if (isDualPane) {
			// We can display everything in-place with fragments, so update
			// the list to highlight the selected item and show the data.
			getListView().setItemChecked(index, true);		
			
			// Check what fragment is currently shown, replace if needed.
			TableGridFragment tableGrid = (TableGridFragment) getFragmentManager()
					.findFragmentById(R.id.TableGridPlaceHolder);

			if (tableGrid == null || tableGrid.getKhuVuc() != khuVuc) {
				// Make new fragment to show this selection.
				tableGrid = TableGridFragment.newInstance(khuVuc);

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.TableGridPlaceHolder, tableGrid);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}

		} else {
			TableGridFragment tableGrid = TableGridFragment.newInstance(khuVuc);
			
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.AreaListPlaceHolder, tableGrid);
			ft.addToBackStack(null);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}
	}
}
