package emenu.client.menu.adapter;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import emenu.client.menu.R;
import emenu.client.menu.view.RelatedDishView;
import emenu.client.util.U;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;

public class RelatedDishesAdapter extends CustomArrayAdapter<ContentValues> {
    int mGalleryItemBackground;

    static class ViewHolder {
        ImageView mAvatar;
        TextView mName;
    }

    public RelatedDishesAdapter(Context context, List<ContentValues> data) {
        super(context, data);

        TypedArray attr = getContext().obtainStyledAttributes(R.styleable.HelloGallery);
        mGalleryItemBackground = attr.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 0);
        attr.recycle();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelatedDishView view = null;
        if (convertView == null) {
            view = new RelatedDishView(getContext());
        } else {
            view = (RelatedDishView) convertView;
        }

        view.bindData(getItem(position));

        return view;
    }

}
