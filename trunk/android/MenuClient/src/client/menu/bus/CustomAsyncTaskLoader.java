package client.menu.bus;

import android.content.AsyncTaskLoader;
import android.content.Context;

public abstract class CustomAsyncTaskLoader<T> extends AsyncTaskLoader<T> {

    protected T mData;

    public CustomAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(T data) {
        if (isReset()) {
            if (data != null) {
                releaseResources(data);
            }
        }

        T oldData = data;
        mData = data;

        if (isStarted()) {
            super.deliverResult(data);
        }

        if (oldData != null) {
            releaseResources(oldData);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }

        // super.onStartLoading();
        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        // super.onStopLoading();
        cancelLoad();
    }

    @Override
    public void onCanceled(T data) {
        super.onCanceled(data);

        releaseResources(data);
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();

        if (mData != null) {
            releaseResources(mData);
            mData = null;
        }
    }

    abstract protected void releaseResources(T data);
}
