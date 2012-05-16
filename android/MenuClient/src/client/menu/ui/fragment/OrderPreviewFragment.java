package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListFragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import client.menu.R;
import client.menu.app.MyApplication;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceOrder;
import client.menu.bus.SessionManager.ServiceSession;
import client.menu.db.contract.ChiTietOrderContract;
import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.contract.MonAnDaNgonNguContract;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhDaNgonNguDTO;
import client.menu.db.dto.MonAnDTO;
import client.menu.db.dto.MonAnDaNgonNguDTO;

public class OrderPreviewFragment extends ListFragment {
    private SimpleAdapter mListAdapter;
    private List<Map<String, Object>> adapterData = new ArrayList<Map<String, Object>>();

    private List<MonAnDaNgonNguDTO> mMonAnList = new ArrayList<MonAnDaNgonNguDTO>();
    private List<DonViTinhDaNgonNguDTO> mDonViTinhtList = new ArrayList<DonViTinhDaNgonNguDTO>();
    private List<Integer> mQuantityList = new ArrayList<Integer>();
    private int mItemCount = 0;

    public void addItemData(MonAnDaNgonNguDTO monAn, DonViTinhDaNgonNguDTO donViTinh) {
        for (int i = 0; i < mItemCount; ++i) {
            if (mMonAnList.get(i).getMaMonAn() == monAn.getMaMonAn()
                    && mDonViTinhtList.get(i).getMaDonViTinh() == donViTinh
                            .getMaDonViTinh()) {

                mQuantityList.set(i, mQuantityList.get(i) + 1);

                Map<String, Object> map = adapterData.get(i);
                map.put(ChiTietOrderContract.COL_SO_LUONG, mQuantityList.get(i));

                adapterData.set(i, map);
                return;
            }
        }

        mMonAnList.add(monAn);
        mDonViTinhtList.add(donViTinh);
        mQuantityList.add(1);
        ++mItemCount;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MonAnDaNgonNguContract.COL_TEN_MON, monAn.getTenMonAn());
        map.put(DonViTinhDaNgonNguContract.COL_TEN_DON_VI, donViTinh.getTenDonViTinh());
        map.put(ChiTietOrderContract.COL_SO_LUONG, 1);

        adapterData.add(map);
    }

    public void updateList(MonAnDaNgonNguDTO monAn, DonViTinhDaNgonNguDTO donViTinh) {
        addItemData(monAn, donViTinh);

        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListAdapter = new SimpleAdapter(getActivity(), adapterData,
                R.layout.item_order_preview, new String[] {
                        MonAnDaNgonNguContract.COL_TEN_MON,
                        ChiTietOrderContract.COL_SO_LUONG,
                        DonViTinhDaNgonNguContract.COL_TEN_DON_VI }, new int[] {
                        R.id.textDishName, R.id.textQuantity, R.id.textUnitName });

        setListAdapter(mListAdapter);
    }
}
