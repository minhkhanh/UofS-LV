package client.menu.ui.view;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class CheckableRelativeLayout extends RelativeLayout {
    boolean mChecked;
    ImageView mImageView;
//    int mLayoutResource;
//    int mCheckMarkResource;

    public CheckableRelativeLayout(Context context, int layoutRes, int checkMarkRes, int selectorRes) {
        super(context);
        
//        mLayoutResource = layoutRes;
//        mCheckMarkResource = checkMarkRes;
        
        prepareViews(layoutRes, checkMarkRes, selectorRes);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    private void prepareViews(int layoutRes, int checkMarkRes, int selectorRes) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutRes, this);
        
        mImageView = (ImageView) findViewById(checkMarkRes);
        mImageView.setBackgroundResource(selectorRes);
    }
    
    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }

    public void toggle() {
        setChecked(!mChecked);
        mImageView.setSelected(mChecked);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            toggle();
        }
        
        return super.onTouchEvent(event);
    }
}
