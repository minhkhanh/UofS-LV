package client.menu.app;

import client.menu.bus.SessionManager;
import android.app.Application;

public class MyApplication extends Application {
    
    public static ApplicationSettings gSettings;
    private SessionManager mSessionManager;
    
    public SessionManager getSessionManager() {
        return mSessionManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        
        gSettings = new ApplicationSettings(this);
        mSessionManager = new SessionManager();
        
        // create temporary session
        mSessionManager.loadSession(1);
    }
}
