package client.menu.ui.activity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import client.menu.R;
import client.menu.ui.view.OrderItemView;
import client.menu.util.U;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class OrderActivity extends ListActivity {
//    ListView mListOrder;
    private List<Map<String, Object>> mListData = new ArrayList<Map<String, Object>>();
    private SimpleAdapter mListAdapter;
    
    private class LoadDishAsyncTask extends AsyncTask<Void, Integer, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            // TODO Auto-generated method stub
            return null;
        }
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onChildClick(View v) {
//        if (v.getId() == R.id.btnIncrease) {
//            ViewGroup group = (ViewGroup) v.getParent();
//            EditText editQuantity = (EditText) group.findViewById(R.id.editQuantity);
//            String text = editQuantity.getText().toString();
//            Integer value = Integer.valueOf(text) + 1;
//            if (value > 99) {
//                value = 0;
//            }
//            editQuantity.setText(value.toString());
//            // U.toastText(this, text);
//        } else if (v.getId() == R.id.btnDecrease) {
//            ViewGroup group = (ViewGroup) v.getParent();
//            EditText editQuantity = (EditText) group.findViewById(R.id.editQuantity);
//            String text = editQuantity.getText().toString();
//            Integer value = Integer.valueOf(text) - 1;
//            if (value < 0) {
//                value = 99;
//            }
//            editQuantity.setText(value.toString());
//        } else if (v.getId() == R.id.btnRemove) {
//            ViewGroup group = (ViewGroup) v.getParent();
//            int pos = mListOrder.getPositionForView(group);
//            mListData.remove(pos);
//            mListAdapter.notifyDataSetChanged();
//        }
    }
}
