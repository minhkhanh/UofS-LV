package client.menu.app;

import android.app.Application;

public final class ApplicationSettings {

    private Application mApplication;
    private AppLocale mLocale;

    public Application getApplication() {
        return mApplication;
    }

    public void setApplication(Application application) {
        mApplication = application;
    }

    public AppLocale getLocale() {
        return mLocale;
    }

    public void setLocale(AppLocale locale) {
        mLocale = locale;
    }

    public ApplicationSettings(Application application) {
        mApplication = application;
        mLocale = AppLocale.createWithDefaultLanguage(mApplication
                .getContentResolver());
    }
}
