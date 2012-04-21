package demo.tablet;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class LocaleListActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_locale_list);
        
        ListView list = (ListView) findViewById(R.id.lv);
        list.setAdapter(new LocaleAdapter(this, Locale.getAvailableLocales()));
    }
}