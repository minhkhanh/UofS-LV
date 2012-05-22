package client.menu.ui.view;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class CheckableRelativeLayout extends RelativeLayout {
    boolean mChecked;

    public CheckableRelativeLayout(Context context) {
        super(context);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        setBackgroundDrawable(checked ? new ColorDrawable(0xff0000a0) : null);
    }

    public void toggle() {
        setChecked(!mChecked);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            toggle();
        }
        
        return super.onTouchEvent(event);
    }
}
