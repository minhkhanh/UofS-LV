package client.menu.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import client.menu.R;

public class TestActivity extends Activity {
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        

    }
}
