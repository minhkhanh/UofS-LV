package client.menu.app;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import client.menu.db.dao.NgonNguDAO;
import client.menu.db.dto.NgonNguDTO;

public class MyAppLocale {

    MyAppSettings mAppSettings;
    private NgonNguDTO mLanguage = new NgonNguDTO();

    public static final NgonNguDTO getCurrentLanguage(Activity activity) {
        MyAppLocale appLocale = MyAppSettings.getCurrentAppLocale(activity);
        return appLocale.mLanguage;
    }

    public static final MyAppLocale createWithDefaultLanguage() {
        MyAppLocale locale = new MyAppLocale();

//        Cursor cursor = resolver.query(NgonNguContract.URI_NGONNGU_MACDINH, null, null,
//                null, null);
//        if (cursor.moveToFirst()) {
//            locale.mLanguage = NgonNguDTO.extractFrom(cursor);
//
//            cursor.close();
//        }
        
        NgonNguDTO obj = NgonNguDAO.getInstance().objNgonNguMacDinh();
        locale.setLanguage(obj);        

        return locale;
    }

    public NgonNguDTO getLanguage() {
        return mLanguage;
    }

    public void setLanguage(NgonNguDTO language) {
        mLanguage = language;
    }

    // public AppLocale(ApplicationSettings settings, String langAbbr) {
    // storeLangAbbr(langAbbr);
    // mAppSettings = settings;
    // }

    public String loadLangAbbr() {
        return mLanguage.getKiHieu();
    }

    public void storeLangAbbr(String abbr) {
        mLanguage.setKiHieu(abbr);
    }

    public boolean applyLanguage(Context context) {
        return applyLanguage(loadLangAbbr(), context);
    }

    public boolean applyLanguage(String lang, Context context) {
        if (lang == null) {
            return false;
        }

        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();

        if (conf.locale.getLanguage().equals(lang) == false) {
            Locale loc = new Locale(lang);
            conf.locale = loc;
            res.updateConfiguration(conf, null);

            storeLangAbbr(lang);

            return true;
        }

        return false;
    }
}
