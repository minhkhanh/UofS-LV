package emenu.client.menu.fragment;

import android.app.Fragment;

public abstract class TableInAreaFragment extends Fragment {
    protected String mTenKhuVuc;
    protected Integer mAreaId;
    
    public TableInAreaFragment() {
        
    }

    public TableInAreaFragment(Integer areaId, String areaName) {
        mAreaId = areaId;
        mTenKhuVuc = areaName;
    }

    public int getMaKhuVuc() {
        return mAreaId;
    }
}
