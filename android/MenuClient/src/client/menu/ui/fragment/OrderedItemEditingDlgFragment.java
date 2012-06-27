package client.menu.ui.fragment;

import client.menu.R;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class OrderedItemEditingDlgFragment extends DialogFragment {
    EditText mQuantityEdit;
    EditText mNoteEdit;

    ContentValues mValues;

    public OrderedItemEditingDlgFragment(ContentValues values) {
        mValues = values;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_order_item_editing, null);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        initViews();
        bindData();
    }
    
    private void bindData() {
        
    }

    private void initViews() {
        
    }
}
