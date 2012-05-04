package client.menu.application;

import android.app.Application;

public class MyApplication extends Application {
    
    public static ApplicationSettings gSettings; 
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        gSettings = new ApplicationSettings(this);
    }
}
