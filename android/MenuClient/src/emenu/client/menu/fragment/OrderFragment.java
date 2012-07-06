package emenu.client.menu.fragment;

import emenu.client.menu.R;
import emenu.client.menu.adapter.OrderedAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class OrderFragment extends Fragment {
    
    protected ListView mItemsList;
    protected OrderedAdapter mItemsAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_order, null);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
//        mItemsList = getView().findViewById(R.)
    }
}
