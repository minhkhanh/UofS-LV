package client.menu.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class BreifOrderView extends RelativeLayout {
    public BreifOrderView(Context context) {
        super(context);
        prepareViews();
    }

    public BreifOrderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    public BreifOrderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            prepareViews();
        }
    }

    private void prepareViews() {
        
    }
    
    public void bindData() {
        
    }
}
