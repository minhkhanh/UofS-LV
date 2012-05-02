package client.menu.util;

import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public class AppLocale {

	private String mLanguage = "vi";

	public String getLanguage() {
		return mLanguage;
	}

	public void setLanguage(String language) {
		this.mLanguage = language;
	}

	public boolean applyLanguage(Context context) {
		return applyLanguage(mLanguage, context);
	}

	public boolean applyLanguage(String lang, Context context) {
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
