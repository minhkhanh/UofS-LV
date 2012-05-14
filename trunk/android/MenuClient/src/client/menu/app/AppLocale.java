package client.menu.app;

import java.util.Locale;

import client.menu.db.contract.NgonNguContract;
import client.menu.db.contract.ThamSoContract;
import client.menu.db.dto.NgonNguDTO;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;

public class AppLocale {

    ApplicationSettings mAppSettings;
    private NgonNguDTO mLanguage = new NgonNguDTO();

    public static final AppLocale createWithDefaultLanguage(ContentResolver resolver) {
        AppLocale locale = new AppLocale();

        Cursor cursor = resolver.query(NgonNguContract.URI_NGONNGU_MACDINH,
                NgonNguContract.getFullProjection(), null, null, null);
        if (cursor.moveToFirst()) {
            // String value = cursor.getString(cursor
            // .getColumnIndex(ThamSoContract.COL_VALUE));
            //
            // Cursor cursor2 = resolver.query(
            // Uri.withAppendedPath(NgonNguContract.CONTENT_URI, value), null,
            // null,
            // null, null);
            //
            // if (cursor2.moveToFirst()) {
            locale.mLanguage = NgonNguDTO.extractFrom(cursor);
            // }

            cursor.close();
        }

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
