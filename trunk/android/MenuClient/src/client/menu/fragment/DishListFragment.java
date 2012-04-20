package client.menu.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import client.menu.adapter.MonAnDtoAdapter;
import client.menu.dto.DanhMucDTO;
import client.menu.dto.MonAnDTO;

public class DishListFragment extends ListFragment {
	DanhMucDTO danhMuc = null;

	public DanhMucDTO getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMucDTO danhMuc) {
		this.danhMuc = danhMuc;
	}

	public static DishListFragment newInstance(DanhMucDTO danhMuc) {
		DishListFragment f = new DishListFragment();
		f.danhMuc = danhMuc;

		return f;
	}

	private MonAnDTO[] fakeData(DanhMucDTO danhMuc) {
		MonAnDTO[] dataArray = new MonAnDTO[danhMuc.getMaDanhMuc()];
		for (int i = 0; i < dataArray.length; ++i) {
			dataArray[i] = new MonAnDTO();
			dataArray[i].setMaMonAn(i);
		}

		return dataArray;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("danhMuc", danhMuc);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			danhMuc = (DanhMucDTO) savedInstanceState
					.getSerializable("danhMuc");
		}

		setListAdapter(new MonAnDtoAdapter(getActivity(), android.R.id.text1,
				fakeData(danhMuc)));
	}
}
