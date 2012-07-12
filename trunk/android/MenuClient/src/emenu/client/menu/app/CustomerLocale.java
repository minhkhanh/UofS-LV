package emenu.client.menu.app;

import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import emenu.client.dao.NgonNguDAO;
import emenu.client.db.dto.NgonNguDTO;

public class CustomerLocale {

    public static final String KEY_PREF_LANGUAGE = "KEY_PREF_LANGUAGE";

    private NgonNguDTO mLanguage = new NgonNguDTO();

    public static final CustomerLocale getDefaultLocale() {
        CustomerLocale locale = new CustomerLocale();

        NgonNguDTO obj = NgonNguDAO.getInstance().objFirstLang();
        locale.setLanguage(obj);

        return locale;
    }

    public void apply(Context context) {
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();

        if (!conf.locale.getLanguage().equals(mLanguage.getKiHieu())) {
            Locale loc = new Locale(mLanguage.getKiHieu());
            conf.locale = loc;
            res.updateConfiguration(conf, res.getDisplayMetrics());
        }
    }

    public void apply(NgonNguDTO newLang, Context context) {
        mLanguage = newLang;
        apply(context);
    }

    public Integer getLangId() {
        return mLanguage.getMaNgonNgu();
    }

    public String getLangCode() {
        return mLanguage.getKiHieu();
    }

    public NgonNguDTO getLanguage() {
        return mLanguage;
    }

    public void setLanguage(NgonNguDTO language) {
        mLanguage = language;
    }
}
