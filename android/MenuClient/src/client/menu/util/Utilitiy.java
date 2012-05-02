package client.menu.util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public final class Utilitiy {
	public static final void restartActivity(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
		
		Log.d(C.TAG, "restartActivity : " + activity);
	}
	
	public static final View extractViewFromParent(ViewGroup parent, int id) {
		View v = parent.findViewById(id);
		parent.removeView(v);
		
		return v;
	}
}
