package emenu.client.menu.ui.view;

import emenu.client.menu.ui.view.CheckMarkView.OnMarkCheckedListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class CheckableLayout extends RelativeLayout implements CheckableInterface,
        OnMarkCheckedListener {

    private CheckMarkView mCheckMark;

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

    protected void initView(int layoutResId, int checkMarkResId) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutResId, this);

        mCheckMark = (CheckMarkView) findViewById(checkMarkResId);
        mCheckMark.setOnToggledListener(this);
    }

    @Override
    public boolean isChecked() {
        return mCheckMark.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        mCheckMark.setChecked(checked);
    }

    @Override
    public void joinGroup(SingleChoiceGroup group) {
        mCheckMark.joinGroup(group);
        // mGroup = group;
        // mGroup.addItem(this);
    }

    @Override
    public void toggle() {
        mCheckMark.toggle();
        // setChecked(!isChecked());
        // if (mGroup != null)
        // mGroup.toggleItem(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            toggle();
        }

        return super.onTouchEvent(event);
        // return false;
    }

    @Override
    public void onMarkChecked(boolean isChecked) {
    }

    // @Override
    // protected void onDetachedFromWindow() {
    // super.onDetachedFromWindow();
    //
    // if (mGroup != null) {
    // mGroup.removeItem(this);
    // }
    // }
}
