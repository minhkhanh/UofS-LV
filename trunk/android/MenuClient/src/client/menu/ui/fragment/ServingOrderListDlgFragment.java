package client.menu.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import client.menu.R;
import client.menu.bus.loader.GetServingOrderListLoader;
import client.menu.bus.task.CustomAsyncTask;
import client.menu.bus.task.CustomAsyncTask.OnPostExecuteAsyncTaskListener;
import client.menu.bus.task.GetServingOrderItemsTask;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.OrderDTO;
import client.menu.ui.adapter.BriefOrderListAdapter;
import android.app.DialogFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentValues;
import android.content.Loader;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

public class ServingOrderListDlgFragment extends DialogFragment implements
        LoaderCallbacks<List<OrderDTO>>,
        OnPostExecuteAsyncTaskListener<Void, Integer, List<ContentValues>> {

    BanDTO mBan;

    BriefOrderListAdapter mListAdapter;
    ExpandableListView mOrderList;

    TextView mSelectedOrderText;

    int mSelectedOrder;

    OnGroupExpandListener mOnGroupExpandListener = new OnGroupExpandListener() {
        @Override
        public void onGroupExpand(int groupPosition) {
            if (mListAdapter.getChildrenCount(groupPosition) == 0) {
                Bundle extras = new Bundle();
                extras.putInt("groupPosition", groupPosition);

                GetServingOrderItemsTask task = new GetServingOrderItemsTask(
                        getActivity(), mListAdapter.getGroup(groupPosition));
                task.setOnPostExecuteListener(ServingOrderListDlgFragment.this);
                task.setExtras(extras);
                task.execute();
            }

            // for (int i = 0; i < mListAdapter.getGroupCount(); ++i) {
            // if (i != groupPosition) {
            // mOrderList.collapseGroup(i);
            // }
            // }
        }
    };

    private OnChildClickListener mOnChildClickListener = new OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                int childPosition, long id) {
            selectOrder(groupPosition);
            return false;
        }
    };

    private OnGroupClickListener mOnGroupClickListener = new OnGroupClickListener() {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition,
                long id) {
            selectOrder(groupPosition);
            return false;
        }
    };

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnOK:
                    
                    break;

                case R.id.btnCancel:
                    
                    break;
                default:
                    break;
            }
        }
    };

    public ServingOrderListDlgFragment(BanDTO ban) {
        mBan = ban;
    }

    private void selectOrder(int groupPosition) {
        mSelectedOrderText.setText(getString(R.string.sub_selected_order) + ": "
                + getString(R.string.sub_order_no) + " " + (groupPosition + 1));

        mSelectedOrder = groupPosition;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(
                getString(R.string.sub_order_list_at) + " " + mBan.getTenBan());
        return inflater.inflate(R.layout.layout_brief_order_list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mOrderList = (ExpandableListView) getView().findViewById(R.id.elistOrder);

        mListAdapter = new BriefOrderListAdapter(getActivity(), new ArrayList<OrderDTO>());
        mOrderList.setAdapter(mListAdapter);

        mOrderList.setOnGroupExpandListener(mOnGroupExpandListener);
        mOrderList.setOnChildClickListener(mOnChildClickListener);
        mOrderList.setOnGroupClickListener(mOnGroupClickListener);
        // mOrderList.setItemsCanFocus(true);

        Button btnOK = (Button) getView().findViewById(R.id.btnOK);
        btnOK.setOnClickListener(mOnClickListener);
        Button btnCancel = (Button) getView().findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(mOnClickListener);

        mSelectedOrderText = (TextView) getView().findViewById(R.id.textSelectedOrder);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<List<OrderDTO>> onCreateLoader(int arg0, Bundle arg1) {
        return new GetServingOrderListLoader(getActivity(), mBan.getMaBan());
    }

    @Override
    public void onLoadFinished(Loader<List<OrderDTO>> arg0, List<OrderDTO> arg1) {
        mListAdapter.clearGroup();
        mListAdapter.addGroupAll(arg1);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<OrderDTO>> arg0) {
        mListAdapter.clearGroup();
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPostExecuteAsyncTask(
            CustomAsyncTask<Void, Integer, List<ContentValues>> task,
            List<ContentValues> result) {
        Bundle extras = task.getExtras();
        int groupPosition = extras.getInt("groupPosition", -1);
        if (groupPosition != -1) {
            mListAdapter.clearChildren(groupPosition);
            mListAdapter.addChildAll(groupPosition, result);
            mListAdapter.notifyDataSetChanged();
        }
    }
}
