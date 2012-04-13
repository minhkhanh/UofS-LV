package tablet.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;

public class ImgBtnAdapter extends BaseAdapter {
	Context mContext = null;
	int mCount = 0;
	
	public ImgBtnAdapter(int count, Context context) {
		mContext = context;
		mCount = count;
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		ImageButton imgBtn = null;
		
		if (convertView == null) {
			imgBtn = new ImageButton(mContext);
			imgBtn.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
		} else {
			imgBtn = (ImageButton) convertView;
		}

		imgBtn.setImageResource(R.drawable.image_button);
		imgBtn.setBackgroundColor(0);
		
		return imgBtn;
	}

}
