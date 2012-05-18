package client.menu.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout {
    boolean mChecked;

    public CheckableLinearLayout(Context context) {
        super(context);
    }
    
    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs,
            int defStyle) {
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
}
