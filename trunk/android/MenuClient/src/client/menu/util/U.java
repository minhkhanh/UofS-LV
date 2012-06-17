package client.menu.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import client.menu.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import android.util.JsonReader;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

public final class U {
    
//    public static final ProgressDialog showWaitingDialog(Context context, String msg) {
//       ProgressDialog dlg = new ProgressDialog(context);
//       dlg.setCanceledOnTouchOutside(false);
//       dlg.setMessage(msg);
//       
//       dlg.show
//       
//       return dlg;
//    }

    public static final void showErrorDialog(Context context, int msgResId) {
        new AlertDialog.Builder(context).setMessage(msgResId)
                .setNeutralButton(R.string.caption_ok, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    public static final void showConfirmDialog(Context context, int msgResId,
            DialogInterface.OnClickListener onPositiveClick) {
        new AlertDialog.Builder(context).setMessage(msgResId).setCancelable(false)
                .setPositiveButton(R.string.caption_yes, onPositiveClick)
                .setNegativeButton(R.string.caption_no, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    public static final List<ContentValues> toContentValuesList(Cursor cursor) {
        List<ContentValues> list = new ArrayList<ContentValues>();

        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            ContentValues c = new ContentValues();
            DatabaseUtils.cursorRowToContentValues(cursor, c);

            list.add(c);
        }

        return list;
    }

    public static final void uncheckAllItems(AbsListView view) {
        SparseBooleanArray chkArray = view.getCheckedItemPositions();
        for (int i = 0; i < chkArray.size(); ++i) {
            if (chkArray.valueAt(i)) {
                int pos = chkArray.keyAt(i);
                view.setItemChecked(pos, false);
            }
        }
    }

    public static final String formatDateTime(String format, long currentMilis) {
        return DateFormat.format(format, currentMilis).toString();
    }

    public static final Boolean deserializeXml(String xmlData) {
        Boolean obj = null;

        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(new StringReader(xmlData));

            int type = parser.getEventType();
            String tag = "";
            String text;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareToIgnoreCase("boolean") != 0) {
                            return null;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        obj = Boolean.valueOf(text);
                        return obj;

                    default:
                        break;
                }

                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final Boolean getCursorBool(Cursor c, int i) {
        return Boolean.valueOf(String.valueOf(c.getInt(i)));
    }

    public static final String loadPostResponseJson(String url, String jsonData) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            StringEntity postObj = new StringEntity(jsonData, HTTP.UTF_8);
            // postObj.setContentType("application/json");
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpPost.setEntity(postObj);

            HttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final String loadPostResponse(String url, String xmlData) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            StringEntity postObj = new StringEntity(xmlData, HTTP.UTF_8);
            postObj.setContentType("text/xml");
            httpPost.setHeader("Content-Type", "application/xml; charset=UTF-8");
            httpPost.setEntity(postObj);

            HttpResponse response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final String loadPutResponseJson(String url, String jsonData) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(url);

        try {
            StringEntity postObj = new StringEntity(jsonData, HTTP.UTF_8);
            httpPut.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpPut.setEntity(postObj);

            HttpResponse response = httpclient.execute(httpPut);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final String loadPutResponse(String url, String xmlData) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut(url);
        String result = null;

        try {
            StringEntity putObj = new StringEntity(xmlData, HTTP.UTF_8);
            putObj.setContentType("text/xml");

            httpPut.setHeader("Content-Type", "application/xml; charset=UTF-8");
            httpPut.setEntity(putObj);

            HttpResponse response = httpclient.execute(httpPut);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static final String loadGetResponse(String url)
            throws ClientProtocolException, IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);

        HttpResponse response = httpclient.execute(httpget);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity());
        }

        return null;
    }

    public static final List<Map<String, Object>> toMapList(Cursor cursor) {
        if (cursor == null) {
            return null;
        }

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

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

    public static final int showDlgFragment(Activity host, DialogFragment dlg, String tag) {
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
            return new java.util.Scanner(is, "utf-8").useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }

    public static final void logOwnTag(String msg) {
        Log.d(C.TAG, msg);
    }

    public static final void toastText(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
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
