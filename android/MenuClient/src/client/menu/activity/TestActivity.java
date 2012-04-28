package client.menu.activity;

import client.menu.R;
import client.menu.db.contract.MonAnContract;
import client.menu.db.provider.MenuClientContentProvider;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TestActivity extends Activity {
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        String[] from = new String[] { BaseColumns._ID, MonAnContract.COL_SID,
                MonAnContract.COL_AVATAR };
        int[] to = new int[] { R.id.textView1, R.id.textView2, R.id.textView3 };
        adapter = new SimpleCursorAdapter(this,
                R.layout.item_test_list, null, from, to,
                0);

        ListView list = (ListView) findViewById(R.id.testList);
        list.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, new LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                String[] projection = new String[] { BaseColumns._ID,
                        MonAnContract.COL_SID, MonAnContract.COL_AVATAR };
                CursorLoader cursor = new CursorLoader(TestActivity.this,
                        MonAnContract.URI_TABLE, projection, null, null, null);
                return cursor;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
                adapter.swapCursor(arg1);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> arg0) {
                adapter.swapCursor(null);
            }
        });

    }

    private void onClick(View v) {
        if (v.getId() == R.id.testAddBtn) {
            
        }
    }
}
