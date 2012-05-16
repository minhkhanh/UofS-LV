package client.menu.app;

import client.menu.bus.SessionManager;
import client.menu.util.C;
import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
    private static final String EX_MSG_01 = "Can not get MyApplication object from the activity parameter.";

    private MyAppSettings gSettings;

    private SessionManager mSessionManager;

    public static MyAppSettings getSettings(Activity activity) {
        Application app = activity.getApplication();
        if (app instanceof MyApplication) {
            return ((MyApplication) app).gSettings;
        }

        throw new IllegalArgumentException(C.TAG + EX_MSG_01);
    }

    public static SessionManager getSessionManager(Activity activity) {
        Application app = activity.getApplication();
        if (app instanceof MyApplication) {
            return ((MyApplication) app).mSessionManager;
        }

        throw new IllegalArgumentException(C.TAG + EX_MSG_01);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        gSettings = new MyAppSettings(this);
        mSessionManager = new SessionManager();

        // create temporary session
        mSessionManager.loadSession(1);
    }
}
