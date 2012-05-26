package client.menu.ui.activity;

import client.menu.R;
import client.menu.app.MyApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        MyApplication.getSettings(this).getLocale().applyLanguage(getApplicationContext());
        
        setContentView(R.layout.layout_splash);
        
        findViewById(android.R.id.content).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreenActivity.this, TableListActivity.class);
                startActivity(intent);
            }
        });
    }
}
