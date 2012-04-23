package client.menu.util;

import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public class AppLocale {
	private Locale[] locales = new Locale[] { new Locale("vi"),
			new Locale("en"), new Locale("ja") };
	
	private String language = "vi";

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String currLang) {
		this.language = currLang;
	}

	public Locale[] getLocales() {
		return locales;
	}

	public boolean applyLanguage(Context context) {
		return applyLanguage(language, context);
	}

	public boolean applyLanguage(String lang, Context context) {
		// Log.d("apply: currLang", currLang);
		// Log.d("apply: langCode", langCode);
		Resources res = context.getResources();
		Configuration conf = res.getConfiguration();

		if (conf.locale.getLanguage().equals(lang) == false) {
			Locale loc = new Locale(lang);
			conf.locale = loc;
			res.updateConfiguration(conf, null);

			setLanguage(lang);

			return true;
		}

		return false;
	}
}
