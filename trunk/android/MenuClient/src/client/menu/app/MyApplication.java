package client.menu.app;

import client.menu.bus.SessionManager;
import client.menu.util.C;
import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
    private static final String EX_MSG_01 = "Can not get MyApplication object from the activity parameter.";

    private MyAppSettings mSettings;

    public static final MyAppSettings getSettings(Activity activity) {
        MyApplication app = (MyApplication) activity.getApplication();
        if (app != null) {
            return app.mSettings;
        }

        throw new IllegalArgumentException(C.TAG + EX_MSG_01);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        
        MyAppRepository.createInstance(this);
        
        mSettings = new MyAppSettings(this);
        SessionManager.createInstance();
        
    }
}
