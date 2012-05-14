package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import client.menu.R;
import client.menu.app.MyApplication;
import client.menu.bus.SessionManager;
import client.menu.bus.SessionManager.ServiceSession;
import client.menu.db.contract.ChiTietOrderContract;
import client.menu.db.contract.DonViTinhMonAnContract;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.MonAnDTO;

public class OrderPreviewFragment extends ListFragment {
    SimpleAdapter mListAdapter;
    
    List<Map<String, Object>> adapterData = new ArrayList<Map<String, Object>>();

    public OrderPreviewFragment(int maMonAn) {

//        ChiTietOrderDTO chiTietOrder = new ChiTietOrderDTO(null, null, null, 1, maMonAn,
//                null, null);
//        mOrder.add(chiTietOrder);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        chiTietOrder.extract(map);
//        map.put(DonViTinhMonAnContract.COL_DON_GIA, 555f);
//        adapterData.add(map);
    }

    public void updateList(Integer maMonAn, Integer maDonViTinh) {
        SessionManager sessionManager = ((MyApplication) getActivity().getApplication()).getSessionManager();
        ServiceSession session = sessionManager.loadCurrentSession();
        
        session.addOrderItem(maMonAn, maDonViTinh);
        
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // for (int i = 0; i < mOrder.size(); ++i) {
        // Map<String, Object> map = new HashMap<String, Object>();
        // mOrder.get(i).extract(map);
        // map.put(DonViTinhMonAnContract.COL_DON_GIA, 555f);
        //
        // data.add(map);
        // }

        mListAdapter = new SimpleAdapter(getActivity(), adapterData, R.layout.item_order_preview,
                new String[] { ChiTietOrderContract.COL_MA_MON,
                        DonViTinhMonAnContract.COL_DON_GIA }, new int[] {
                        R.id.textDishName, R.id.textPrice });

        setListAdapter(mListAdapter);
    }
}
