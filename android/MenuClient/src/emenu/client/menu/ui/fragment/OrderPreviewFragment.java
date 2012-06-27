package emenu.client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import emenu.client.menu.R;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.DonViTinhDaNgonNguDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;

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
                map.put(ChiTietOrderDTO.CL_SO_LUONG, mQuantityList.get(i));

                adapterData.set(i, map);
                return;
            }
        }

        mMonAnList.add(monAn);
        mDonViTinhtList.add(donViTinh);
        mQuantityList.add(1);
        ++mItemCount;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MonAnDaNgonNguDTO.CL_TEN_MON, monAn.getTenMonAn());
        map.put(DonViTinhDaNgonNguDTO.CL_TEN_DON_VI, donViTinh.getTenDonViTinh());
        map.put(ChiTietOrderDTO.CL_SO_LUONG, 1);

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
                R.layout.item_order_preview, new String[] { MonAnDaNgonNguDTO.CL_TEN_MON,
                        ChiTietOrderDTO.CL_SO_LUONG,
                        DonViTinhDaNgonNguDTO.CL_TEN_DON_VI }, new int[] {
                        R.id.textDishName, R.id.textQuantity, R.id.textUnitName });

        setListAdapter(mListAdapter);
    }
}
