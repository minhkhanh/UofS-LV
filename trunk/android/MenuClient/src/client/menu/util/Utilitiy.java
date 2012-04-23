package client.menu.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;

public final class Utilitiy {
	public static final void restartActivity(Activity obj) {
		obj.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		obj.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		obj.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
	}
}
