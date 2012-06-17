package client.menu.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class CheckableLayout extends FrameLayout {

    CheckMarkView mCheckMark;

    public CheckableLayout(Context context, int layoutResId, int checkMarkResId) {
        super(context);

        initView(layoutResId, checkMarkResId);
    }

    public CheckableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void initView(int layoutResId, int checkMarkResId) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutResId, this);

        mCheckMark = (CheckMarkView) findViewById(checkMarkResId);
    }

    public boolean isChecked() {
        return mCheckMark.isChecked();
    }

    public void setChecked(boolean checked) {
        mCheckMark.setChecked(checked);
    }

    public void joinGroup(SingleChoiceGroup group) {
        mCheckMark.joinGroup(group);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mCheckMark.toggle();
        }

        return super.onTouchEvent(event);
    }
}
