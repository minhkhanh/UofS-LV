package client.menu.bus.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

public abstract class CustomAsyncTask<Params, Progress, Result> extends
        AsyncTask<Params, Progress, Result> {

    public interface OnPreExecuteAsyncTaskListener<Params, Progress, Result> {
        void onPreExecuteAsyncTask(CustomAsyncTask<Params, Progress, Result> task);
    }

    public interface OnPostExecuteAsyncTaskListener<Params, Progress, Result> {
        void onPostExecuteAsyncTask(CustomAsyncTask<Params, Progress, Result> task,
                Result result);
    }

    public interface OnProgressUpdateAsyncTaskListener<Params, Progress, Result> {
        void onProgressUpdateAsyncTask(CustomAsyncTask<Params, Progress, Result> task,
                Progress... values);
    }

    private Bundle mExtras;
    private Context mContext;
    private OnPostExecuteAsyncTaskListener<Params, Progress, Result> mOnPostExecuteListener;
    private OnPreExecuteAsyncTaskListener<Params, Progress, Result> mOnPreExecuteListener;
    private OnProgressUpdateAsyncTaskListener<Params, Progress, Result> mOnProgressUpdateListener;

    public CustomAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected abstract Result doInBackground(Params... params);

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);

        if (mOnPostExecuteListener != null) {
            mOnPostExecuteListener.onPostExecuteAsyncTask(this, result);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (mOnPreExecuteListener != null) {
            mOnPreExecuteListener.onPreExecuteAsyncTask(this);
        }
    }

    @Override
    protected void onProgressUpdate(Progress... values) {
        super.onProgressUpdate(values);

        if (mOnProgressUpdateListener != null) {
            mOnProgressUpdateListener.onProgressUpdateAsyncTask(this, values);
        }
    }

    public Context getContext() {
        return mContext;
    }

    public void setOnPostExecuteListener(
            OnPostExecuteAsyncTaskListener<Params, Progress, Result> onPostExecuteListener) {
        mOnPostExecuteListener = onPostExecuteListener;
    }

    public void setOnPreExecuteListener(
            OnPreExecuteAsyncTaskListener<Params, Progress, Result> onPreExecuteListener) {
        mOnPreExecuteListener = onPreExecuteListener;
    }

    public void setOnProgressUpdateListener(
            OnProgressUpdateAsyncTaskListener<Params, Progress, Result> onProgressUpdateListener) {
        mOnProgressUpdateListener = onProgressUpdateListener;
    }

    public Bundle getExtras() {
        return mExtras;
    }

    public void setExtras(Bundle extras) {
        mExtras = extras;
    }

}
