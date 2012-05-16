package client.menu.bus;

import client.menu.R;
import client.menu.app.MyApplication;
import android.app.Activity;
import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public final class Repository extends RelativeLayout {

    public Repository(Context context) {
        super(context);

        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.item_order, this);
    }
}
