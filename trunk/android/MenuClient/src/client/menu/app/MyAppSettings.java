package client.menu.app;

import android.app.Activity;
import android.app.Application;

public final class MyAppSettings {

    private Application mApplication;
    private MyAppLocale mLocale;
    
    public static final MyAppLocale getCurrentAppLocale(MyApplication myApp) {
        MyAppSettings settings = myApp.getSettings();
        return settings.mLocale;
    }

    public MyAppLocale getLocale() {
        return mLocale;
    }

    public void setLocale(MyAppLocale locale) {
        mLocale = locale;
    }

    public MyAppSettings(Application application) {
        mApplication = application;
        mLocale = MyAppLocale.createWithDefaultLanguage();
    }
}
