package client.menu.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.ViewGroup;

public final class Utilitiy {
	public static final void restartActivity(Activity obj) {
		obj.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		obj.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		obj.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
	}
	
	public static final View extractViewFromParent(ViewGroup parent, int id) {
		View v = parent.findViewById(id);
		parent.removeView(v);
		
		return v;
	}
}
