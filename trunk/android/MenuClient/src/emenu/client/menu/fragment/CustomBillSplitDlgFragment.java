package emenu.client.menu.fragment;

import java.util.List;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import emenu.client.bus.task.LoadBillItemsTask;
import emenu.client.menu.R;
import emenu.client.menu.app.SessionManager;
import emenu.client.menu.app.SessionManager.ServiceOrder;
import emenu.client.menu.view.DishSwappableListView;
import emenu.client.menu.view.MiniBillView;
import emenu.client.util.U;

public class CustomBillSplitDlgFragment extends DialogFragment {
    protected MiniBillView mSubBillView;
    private MiniBillView mMainBillView;
    private List<ContentValues> mData;

    public CustomBillSplitDlgFragment(List<ContentValues> data) {
        mData = data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.title_dialog_bill_split));
        return inflater.inflate(R.layout.layout_custom_bill_split, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ViewGroup scrollBill = (ViewGroup) getView().findViewById(R.id.scrollBill);

        Button btnSplit = (Button) getView().findViewById(R.id.btnSplit);
        btnSplit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mSubBillView.getBillList().getCount() > 0) {
                    MiniBillView newBill = mSubBillView.clone();
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                    lp.setMargins(10, 10, 10, 10);
                    newBill.setLayoutParams(lp);
                    scrollBill.addView(newBill);

                    mSubBillView.bindData(null);
                }
            }
        });

        mMainBillView = (MiniBillView) getView().findViewById(R.id.billMain);
        DishSwappableListView listMain = mMainBillView.getBillList();
        mMainBillView.bindData(mData);
        
        mSubBillView = (MiniBillView) getView().findViewById(R.id.billSub);
        DishSwappableListView listSub = mSubBillView.getBillList();
        
        listMain.pair(listSub);
    }
}
