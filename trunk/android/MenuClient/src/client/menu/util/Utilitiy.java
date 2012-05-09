package client.menu.util;

import java.util.ArrayList;
import java.util.Hashtable;

import client.menu.db.contract.ChiTietOrderContract;
import client.menu.db.contract.MonAnContract;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public final class Utilitiy {
    
    public static final void toastText(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    
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
	
	public static final void orderDish(ArrayList<ContentValues> order, ContentValues dish) {
	    for (int i = 0; i < order.size(); ++i) {
	        ContentValues dishDetail = order.get(i);
	        int maMon = dish.getAsInteger(MonAnContract.COL_SID);
	        if (dishDetail.getAsInteger(ChiTietOrderContract.COL_MA_MON) == maMon) {
	            int quatity = dishDetail.getAsInteger(ChiTietOrderContract.COL_SO_LUONG);
	            dishDetail.put(ChiTietOrderContract.COL_SO_LUONG, quatity + 1);
	        } else {
	            ContentValues newDetail = new ContentValues();
	            newDetail.put(ChiTietOrderContract.COL_MA_MON, maMon);
	            newDetail.put(ChiTietOrderContract.COL_SO_LUONG, 1);
	            
	            order.add(newDetail);
	        }	            
	    }
	}
}
