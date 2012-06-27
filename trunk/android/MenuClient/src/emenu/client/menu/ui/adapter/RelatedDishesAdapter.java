package emenu.client.menu.ui.adapter;

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
import client.menu.R;
import emenu.client.menu.db.dto.MonAnDTO;
import emenu.client.menu.db.dto.MonAnDaNgonNguDTO;

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
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_related_dish, null);

            holder = new ViewHolder();
            holder.mName = (TextView) convertView.findViewById(R.id.textRelatedName);
            holder.mAvatar = (ImageView) convertView.findViewById(R.id.imgRelatedAvatar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mName.setText(getItem(position).getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));

        byte[] encodedImg = getItem(position).getAsByteArray(MonAnDTO.CL_HINH_ANH);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(encodedImg, 0,
                encodedImg.length);
        holder.mAvatar.setImageBitmap(decodedBitmap);
        holder.mAvatar.setBackgroundResource(mGalleryItemBackground);

        return convertView;
    }

}
