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
			selIndex = savedInstanceState.getInt("selIndex", 0);
		}

		if (isDualPane) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(selIndex);
		}
	}

	void showDetails(int index) {
		selIndex = index;
		
		KhuVucDTO khuVuc = (KhuVucDTO) getListAdapter().getItem(index);
		
		if (isDualPane) {
			getListView().setItemChecked(index, true);		
			
			TableGridFragment tableGrid = (TableGridFragment) getFragmentManager()
					.findFragmentById(R.id.TableGridPlaceHolder);

			if (tableGrid == null || tableGrid.getKhuVuc() != khuVuc) {
				tableGrid = TableGridFragment.newInstance(khuVuc);

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
