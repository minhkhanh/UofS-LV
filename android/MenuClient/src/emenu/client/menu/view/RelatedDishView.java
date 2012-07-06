package emenu.client.menu.view;

import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.GetDishPromotionTask;
import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.db.dto.MonAnDTO;
import emenu.client.db.dto.MonAnDaNgonNguDTO;
import emenu.client.menu.R;
import emenu.client.menu.fragment.DishDetailDlgFragment;
import emenu.client.util.C;
import emenu.client.util.U;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class RelatedDishView extends RelativeLayout implements OnClickListener {
    private ContentValues mValues;
    private TextView mName;
    private ImageView mAvatar;
    private ImageView mSaleImage;

    private OnPostExecuteListener<Integer, Void, KhuyenMaiDTO> mOnPostGetDishPromotion = new OnPostExecuteListener<Integer, Void, KhuyenMaiDTO>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Integer, Void, KhuyenMaiDTO> task,
                KhuyenMaiDTO result) {
            if (result != null) {
                mSaleImage.setVisibility(View.VISIBLE);
                mValues.putAll(result.toContentValues());
            } else {
                mSaleImage.setVisibility(View.GONE);
            }
        }
    };

    public RelatedDishView(Context context) {
        super(context);
        initView();
    }

    public RelatedDishView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            initView();
    }

    public RelatedDishView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.item_related_dish, this);

        TypedArray attr = getContext().obtainStyledAttributes(R.styleable.HelloGallery);
        int galleryItemBackground = attr.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 0);
        attr.recycle();

        mName = (TextView) findViewById(R.id.textRelatedName);
        mAvatar = (ImageView) findViewById(R.id.imgRelatedAvatar);
        mAvatar.setBackgroundResource(galleryItemBackground);
        mAvatar.setClickable(true);
        mAvatar.setOnClickListener(this);

        mSaleImage = (ImageView) findViewById(R.id.imgSale);
    }

    public void bindData(ContentValues v) {
        mValues = v;

        mName.setText(mValues.getAsString(MonAnDaNgonNguDTO.CL_TEN_MON));
        U.setDishAvatar(mValues, mAvatar);
        
        if (mValues.getAsInteger(KhuyenMaiDTO.CL_MA_KHUYEN_MAI) != null) {
            mSaleImage.setVisibility(View.VISIBLE);
        } else {
            mSaleImage.setVisibility(View.INVISIBLE);
        }

//        new GetDishPromotionTask().setOnPostExecuteListener(mOnPostGetDishPromotion)
//                .execute(mValues.getAsInteger(MonAnDTO.CL_MA_MON_AN));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgRelatedAvatar:
                DishDetailDlgFragment dlg = new DishDetailDlgFragment(mValues, null, 0);
                U.showDlgFragment((Activity) getContext(), dlg, false);
                break;

            default:
                break;
        }
    }
}
