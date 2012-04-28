package client.menu.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import client.menu.R;
import client.menu.adapter.TableGridAdapter;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.KhuVucDTO;
import client.menu.util.Utilitiy;

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

        // Log.d("mylog", "TableGridFragment.onSaveInstanceState");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            khuVuc = (KhuVucDTO) savedInstanceState.getSerializable("khuVuc");
        }

        ViewGroup frame = (ViewGroup) inflater.inflate(R.layout.frame_table_grid, null);
        GridView grid = (GridView) Utilitiy.extractViewFromParent(frame, R.id.TableGrid);
        grid.setAdapter(new TableGridAdapter(getActivity(), fakeData(khuVuc)));
        grid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dlg");
                if (prev != null) {
                    ft.remove(prev);
                }

                DialogFragment newFragment = TableDialogFragment.newInstance();
                newFragment.show(ft, "dlg");
            }
        });

        return grid;
    }
}
