package emenu.client.menu.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class CheckMarkView extends ImageView implements CheckableInterface {

    private SingleChoiceGroup mGroup;

    private boolean mChecked;
    private OnMarkCheckedListener mOnMarkCheckedListener;

    public interface OnMarkCheckedListener {
        void onMarkChecked(boolean isChecked);
    }

    public CheckMarkView(Context context) {
        super(context);
        initView();
    }

    public CheckMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            initView();
        }
    }

    public CheckMarkView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            initView();
        }
    }

    private void initView() {
        setEnabled(false); // unchecked at first
        setClickable(true);
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        setEnabled(mChecked);

        if (mOnMarkCheckedListener != null)
            mOnMarkCheckedListener.onMarkChecked(mChecked);
    }

    public void toggle() {
        setChecked(!mChecked);
        if (mGroup != null) {
            mGroup.toggleItem(this);
        }
    }

    @Override
    public void joinGroup(SingleChoiceGroup group) {
        mGroup = group;
        mGroup.addItem(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            toggle();
        }

        // return super.onTouchEvent(event);
        return false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (mGroup != null) {
            mGroup.removeItem(this);
        }
    }

    public OnMarkCheckedListener getOnToggledListener() {
        return mOnMarkCheckedListener;
    }

    public void setOnToggledListener(OnMarkCheckedListener onToggledListener) {
        mOnMarkCheckedListener = onToggledListener;
    }
}
