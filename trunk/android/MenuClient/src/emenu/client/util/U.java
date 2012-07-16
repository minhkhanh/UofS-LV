package emenu.client.util;

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
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.menu.R;
import emenu.client.menu.fragment.AuthDlgFragment;
import emenu.client.menu.fragment.AuthDlgFragment.OnAuthDlgDismissedListener;

public final class U {

    public static final ProgressDialog createWaitingDialog(Context context) {
        ProgressDialog dlg = new ProgressDialog(context);
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);
        dlg.setMessage(context.getString(R.string.message_waiting));

        return dlg;
    }

    public static final long getTimeFromJsonDate(String jsonDate) {
        // /Date(1234567890...+xxxx)/
        // /Date(1234567890...-xxxx)/
        long offset = 0;
        int pOpen = jsonDate.indexOf("(");
        int pClose = jsonDate.indexOf(")");

        // try to detect time zone
        int pSign = jsonDate.indexOf("+");
        if (pSign == -1)
            pSign = jsonDate.indexOf("-");
        if (pSign == -1)
            pSign = pClose;
        else {
            // time zone detected
            String hrOffset = jsonDate.substring(pSign + 1, pSign + 3);
            String minOffset = jsonDate.substring(pSign + 3, pSign + 5);
            offset = Integer.valueOf(hrOffset) * 3600000 + Integer.valueOf(minOffset)
                    * 60000;

            if (jsonDate.charAt(pSign) == '-')
                offset *= -1;
        }

        String miliSecs = jsonDate.substring(pOpen + 1, pSign);
        return Long.valueOf(miliSecs) + offset;
    }

    public static final void setDishAvatar(ContentValues c, ImageView v) {
        byte[] imgData = c.getAsByteArray(MonAnDTO.CL_HINH_ANH);
        if (imgData != null) {
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(imgData, 0,
                    imgData.length);
            v.setImageBitmap(decodedBitmap);
        }
    }

    public static final void showErrorDialog(Context context, String msg) {
        new AlertDialog.Builder(context).setMessage(msg)
                .setNeutralButton(R.string.caption_ok, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

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

    public static final String loadPostResponseForm(String url, List<NameValuePair> data)
            throws ParseException, IOException {
        HttpClient httpclient = new MyHttpClient();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setEntity(new UrlEncodedFormEntity(data));
        HttpResponse response = httpclient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity());
        }

        return null;
    }

    public static final String loadPostResponseJson(HttpClient httpClient, String url,
            String jsonData) {
        if (httpClient == null)
            httpClient = new MyHttpClient();

        HttpPost httpPost = new HttpPost(url);

        try {
            StringEntity postObj = new StringEntity(jsonData, HTTP.UTF_8);
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            httpPost.setEntity(postObj);

            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final String loadPostResponseJson(String url, String jsonData) {
        HttpClient httpclient = new MyHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            StringEntity postObj = new StringEntity(jsonData, HTTP.UTF_8);
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

    @Deprecated
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
        HttpClient httpclient = new MyHttpClient();
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

    @Deprecated
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

    public static final String loadGetResponse(HttpClient httpClient, String url)
            throws ClientProtocolException, IOException {
        if (httpClient == null)
            httpClient = new MyHttpClient();

        HttpGet httpget = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpget);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity());
        }

        return null;
    }

    public static final String loadGetResponse(String url)
            throws ClientProtocolException, IOException {
        HttpClient httpclient = new MyHttpClient();
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

    public static final int showAuthDlg(OnAuthDlgDismissedListener listener,
            FragmentManager fm, Bundle extras) {
        AuthDlgFragment dlg = new AuthDlgFragment();
        dlg.setArguments(extras);

        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(C.AUTH_DIALOG_TAG);
        if (prev != null) {
            ft.remove(prev);
        }

        return dlg.show(ft, C.AUTH_DIALOG_TAG);
    }

    public static final int showDlgFragment(FragmentManager fm, DialogFragment dlg,
            boolean addStack) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(C.DIALOG_FRAGMENT_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        if (addStack)
            ft.addToBackStack(null);

        return dlg.show(ft, C.DIALOG_FRAGMENT_TAG);
    }

    public static final int showDlgFragment(Activity host, DialogFragment dlg,
            boolean addStack) {
        FragmentTransaction ft = host.getFragmentManager().beginTransaction();
        Fragment prev = host.getFragmentManager()
                .findFragmentByTag(C.DIALOG_FRAGMENT_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        if (addStack)
            ft.addToBackStack(null);

        return dlg.show(ft, C.DIALOG_FRAGMENT_TAG);
    }

    public static final String toString(InputStream is) {
        try {
            return new java.util.Scanner(is, "utf-8").useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static final void logOwnTag(String msg) {
        Log.d(C.TAG, msg);
    }

    public static final void toastText(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
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
