package client.menu.app;

import android.app.Activity;
import android.app.Application;

public final class MyAppSettings {

    private Application mApplication;
    private MyAppLocale mLocale;
    
    public static final MyAppLocale getCurrentAppLocale(Activity activity) {
        MyAppSettings settings = MyApplication.getSettings(activity);
        return settings.mLocale;
    }

    public Application getApplication() {
        return mApplication;
    }

    public void setApplication(Application application) {
        mApplication = application;
    }

    public MyAppLocale getLocale() {
        return mLocale;
    }

    public void setLocale(MyAppLocale locale) {
        mLocale = locale;
    }

    public MyAppSettings(Application application) {
        mApplication = application;
        mLocale = MyAppLocale.createWithDefaultLanguage(mApplication
                .getContentResolver());
    }
}
