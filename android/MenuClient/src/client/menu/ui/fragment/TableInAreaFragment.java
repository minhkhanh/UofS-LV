package client.menu.ui.fragment;

import android.app.Fragment;

public abstract class TableInAreaFragment extends Fragment {
    protected String mTenKhuVuc;
    protected Integer mMaKhuVuc;

    public TableInAreaFragment(Integer areaId, String areaName) {
        mMaKhuVuc = areaId;
        mTenKhuVuc = areaName;
    }

    public int getMaKhuVuc() {
        return mMaKhuVuc;
    }
}
