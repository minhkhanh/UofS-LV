package client.menu.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public final class U {
    public static final List<Map<String, Object>> toMapList(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        
        cursor.move(-1);
        while (cursor.moveToNext()) {
            ContentValues values = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(cursor, values);
            
            Set<Entry<String, Object>> setValues = values.valueSet();
            Object[] entryArray = setValues.toArray();
            Map<String, Object> map = new Hashtable<String, Object>();
            for (int i = 0; i < entryArray.length; ++i) {
                Entry<String, Object> entry = (Entry<String, Object>) entryArray[i];
                map.put(entry.getKey(), entry.getValue());
            }
            
            list.add(map);
        }
        
        return list;
    }
    
    public static final boolean cancelAsyncTask(AsyncTask task) {
        if (task != null && task.getStatus() != AsyncTask.Status.FINISHED) {
            task.cancel(true);
            return true;
        }
        
        return false;
    }
    
    public static final int showDlgFragment(Fragment host, DialogFragment dlg, String tag) {
        FragmentTransaction ft = host.getFragmentManager().beginTransaction();
        Fragment prev = host.getFragmentManager().findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        
        return dlg.show(ft, tag);
    }

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
