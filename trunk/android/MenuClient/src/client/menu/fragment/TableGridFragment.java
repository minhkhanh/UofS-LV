package client.menu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import client.menu.adapter.TableItemAdapter;
import client.menu.dto.BanDTO;
import client.menu.dto.KhuVucDTO;

public class TableGridFragment extends Fragment {
	KhuVucDTO khuVuc = null;

	public KhuVucDTO getKhuVuc() {
		return khuVuc;
	}

	public void setKhuVuc(KhuVucDTO khuVuc) {
		this.khuVuc = khuVuc;
	}

	public static TableGridFragment newInstance(KhuVucDTO khuVuc) {
		TableGridFragment f = new TableGridFragment();
		f.khuVuc = khuVuc;

		return f;
	}

	private BanDTO[] fakeData(KhuVucDTO khuVuc) {
		BanDTO[] banArray = new BanDTO[khuVuc.getMaKhuVuc()];
		for (int i = 0; i < banArray.length; ++i) {
			banArray[i] = new BanDTO();
			banArray[i].setMaBan(i);
			banArray[i].setTenBan("Bàn " + i);
		}

		return banArray;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("khuVuc", khuVuc);

		Log.d("mylog", "TableGridFragment.onSaveInstanceState");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			khuVuc = (KhuVucDTO) savedInstanceState.getSerializable("khuVuc");
		}

		GridView grid = new GridView(getActivity());
		grid.setGravity(Gravity.CENTER);
		grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		grid.setNumColumns(GridView.AUTO_FIT);
		grid.setAdapter(new TableItemAdapter(getActivity(), fakeData(khuVuc)));
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,
					long id) {
				Toast.makeText(getActivity(), "" + pos, Toast.LENGTH_SHORT)
						.show();
			}
		});
		
		return grid;
	}
}
