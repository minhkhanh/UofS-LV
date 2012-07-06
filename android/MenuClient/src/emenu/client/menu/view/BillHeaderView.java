package emenu.client.menu.view;

import emenu.client.menu.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BillHeaderView extends RelativeLayout {
    
    private TextView mHeaderText;
    
    public BillHeaderView(Context context) {
        super(context);
        initView();
    }
    
    public BillHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            initView();
    }

    public BillHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            initView();
    }
    
    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.frame_bill_header, this);
        
        mHeaderText = (TextView) findViewById(R.id.textHeader);
    }
    
    public void setText(int resId) {
        mHeaderText.setText(resId);
    }

    public void setText(String text) {
        mHeaderText.setText(text);
    }
}
