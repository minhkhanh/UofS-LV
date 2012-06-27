package emenu.client.menu.ui.fragment;

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
import client.menu.R;
import emenu.client.menu.bus.SessionManager;
import emenu.client.menu.bus.SessionManager.ServiceOrder;
import emenu.client.menu.bus.task.LoadBillItemsTask;
import emenu.client.menu.ui.view.MiniBillView;
import emenu.client.menu.util.U;

public class CustomBillSplitDialog extends DialogFragment {
    protected MiniBillView mSubBillView;
    private MiniBillView mMainBillView;
    private CustomLoadBillItemsTask mLoadBillItemsTask;

    private class CustomLoadBillItemsTask extends LoadBillItemsTask {
        @Override
        protected void onPostExecute(List<ContentValues> result) {
            super.onPostExecute(result);

            mMainBillView.bindData(result);
        }
    };

    @Override
    public void onPause() {
        super.onPause();

        U.cancelAsyncTask(mLoadBillItemsTask);
    }

    @Override
    public void onResume() {
        super.onResume();

        ServiceOrder order = SessionManager.getInstance().loadCurrentSession().getOrder();
        mLoadBillItemsTask = new CustomLoadBillItemsTask();
//        mLoadBillItemsTask.execute(order.getContent());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setStyle(DialogFragment.STYLE_NORMAL,
        // android.R.style.Theme_Holo_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.title_dialog_bill_split));

        View v = inflater.inflate(R.layout.dialog_custom_bill_split, container, false);
        return v;
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
                    // newBill.set
                    scrollBill.addView(newBill);

                    mSubBillView.bindData(null);
                }
            }
        });

        mMainBillView = (MiniBillView) getView().findViewById(R.id.billMain);
        ListView listMain = mMainBillView.getBillList();
        listMain.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mMainBillView.swapItem(mSubBillView, arg2);
            }
        });

        mSubBillView = (MiniBillView) getView().findViewById(R.id.billSub);
        ListView listSub = mSubBillView.getBillList();
        listSub.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                mSubBillView.swapItem(mMainBillView, arg2);
            }
        });
    }
}
