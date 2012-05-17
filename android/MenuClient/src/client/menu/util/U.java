package client.menu.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import client.menu.db.contract.ChiTietOrderContract;
import client.menu.db.contract.MonAnContract;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public final class U {

    public static final String convertStreamToString(InputStream is) {
        try {
            return new java.util.Scanner(is).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }

    public static final void logOwnTag(String msg) {
        Log.d(C.TAG, msg);
    }

    public static final void toastText(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static final void restartActivity(Activity activity) {
        int original = activity.getResources().getConfiguration().orientation;

        if (original == Configuration.ORIENTATION_LANDSCAPE) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else if (original == Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }

        U.logOwnTag("restartActivity : " + activity.getClass().toString());
    }

    public static final View extractViewFromParent(ViewGroup parent, int id) {
        View v = parent.findViewById(id);
        parent.removeView(v);

        return v;
    }
}
