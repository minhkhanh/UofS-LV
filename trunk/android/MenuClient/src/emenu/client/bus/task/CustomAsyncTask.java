package emenu.client.bus.task;

import android.app.ProgressDialog;
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

    private ProgressDialog mWaitingDialog;
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
        
        if (mWaitingDialog != null)
            mWaitingDialog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        
        if (mWaitingDialog != null)
            mWaitingDialog.show();

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
        if (mExtras == null)
            mExtras = new Bundle();
        return mExtras;
    }

    public void setExtras(Bundle extras) {
        mExtras = extras;
    }

    public ProgressDialog getWaitingDialog() {
        return mWaitingDialog;
    }

    public void setWaitingDialog(ProgressDialog waitingDialog) {
        mWaitingDialog = waitingDialog;
    }

}
