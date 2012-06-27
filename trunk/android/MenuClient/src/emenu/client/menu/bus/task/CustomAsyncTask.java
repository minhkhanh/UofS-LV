package emenu.client.menu.bus.task;

import android.os.AsyncTask;
import android.os.Bundle;

public abstract class CustomAsyncTask<Params, Progress, Result> extends
        AsyncTask<Params, Progress, Result> {

    public interface OnPreExecuteListener<Params, Progress, Result> {
        void onPreExecute(CustomAsyncTask<Params, Progress, Result> task);
    }

    public interface OnPostExecuteListener<Params, Progress, Result> {
        void onPostExecute(CustomAsyncTask<Params, Progress, Result> task,
                Result result);
    }

    public interface OnProgressUpdateListener<Params, Progress, Result> {
        void onProgressUpdate(CustomAsyncTask<Params, Progress, Result> task,
                Progress... values);
    }

    private Bundle mExtras;
    private OnPostExecuteListener<Params, Progress, Result> mOnPostExecuteListener;
    private OnPreExecuteListener<Params, Progress, Result> mOnPreExecuteListener;
    private OnProgressUpdateListener<Params, Progress, Result> mOnProgressUpdateListener;

    @Override
    protected abstract Result doInBackground(Params... params);

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);

        if (mOnPostExecuteListener != null) {
            mOnPostExecuteListener.onPostExecute(this, result);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (mOnPreExecuteListener != null) {
            mOnPreExecuteListener.onPreExecute(this);
        }
    }

    @Override
    protected void onProgressUpdate(Progress... values) {
        super.onProgressUpdate(values);

        if (mOnProgressUpdateListener != null) {
            mOnProgressUpdateListener.onProgressUpdate(this, values);
        }
    }

    public CustomAsyncTask<Params, Progress, Result> setOnPostExecuteListener(
            OnPostExecuteListener<Params, Progress, Result> onPostExecuteListener) {
        mOnPostExecuteListener = onPostExecuteListener;
        
        return this;
    }

    public void setOnPreExecuteListener(
            OnPreExecuteListener<Params, Progress, Result> onPreExecuteListener) {
        mOnPreExecuteListener = onPreExecuteListener;
    }

    public void setOnProgressUpdateListener(
            OnProgressUpdateListener<Params, Progress, Result> onProgressUpdateListener) {
        mOnProgressUpdateListener = onProgressUpdateListener;
    }

    public Bundle getExtras() {
        return mExtras;
    }

    public void setExtras(Bundle extras) {
        mExtras = extras;
    }

}
