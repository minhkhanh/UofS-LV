package client.menu.ui.fragment;

import java.util.List;

import client.menu.R;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.YeuCauGhepBan;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

public class TableGroupingOptionsDlgFragment extends DialogFragment {
    
    private List<BanDTO> mListMaBan;
    
//    private Expa
    
    public TableGroupingOptionsDlgFragment(List<BanDTO> listBan) {
        mListMaBan = listBan;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.layout_table_grouping_options, null);
        return new ListView(getActivity());
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
//        TabHost tabHost = (TabHost) getView().findViewById(android.R.id.tabhost);
        
    }
}
