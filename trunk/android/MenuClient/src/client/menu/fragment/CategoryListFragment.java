package client.menu.fragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import client.menu.R;
import client.menu.dto.DanhMucDTO;

public class CategoryListFragment extends ListFragment {
	boolean isDualPane;
	int selIndex;

	private static DanhMucDTO[] fakeData(int count) {
		DanhMucDTO[] khuVucArray = new DanhMucDTO[count];
		for (int i = 0; i < khuVucArray.length; ++i) {
			khuVucArray[i] = new DanhMucDTO();
			khuVucArray[i].setMaDanhMuc(i);
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

		setListAdapter(new ArrayAdapter<DanhMucDTO>(getActivity(),
				android.R.layout.simple_list_item_activated_1, fakeData(10)));

		View dishList = getActivity()
				.findViewById(R.id.RightPaneHolder);
		isDualPane = dishList != null
				&& dishList.getVisibility() == View.VISIBLE;

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

		DanhMucDTO danhMuc = (DanhMucDTO) getListAdapter().getItem(index);

		if (isDualPane) {
			getListView().setItemChecked(index, true);

			DishListFragment dishList = (DishListFragment) getFragmentManager()
					.findFragmentById(R.id.RightPaneHolder);

			if (dishList == null || dishList.getDanhMuc() != danhMuc) {
				dishList = DishListFragment.newInstance(danhMuc);

				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.RightPaneHolder, dishList);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}

		} else {
			DishListFragment dishList = DishListFragment.newInstance(danhMuc);

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.LeftPaneHolder, dishList);
			ft.addToBackStack(null);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}
	}
}
