package tablet.demo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

public class ImgBtnAdapter extends BaseAdapter {
	Context context = null;
	SampleDTO[] data = new SampleDTO[0];
	
	public ImgBtnAdapter(SampleDTO[] _data, Context _context) {
		context = _context;
		data = _data;
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int pos) {
		if (pos < 0 || pos >= data.length) {
			return null;
		}
		
		return data[pos];
	}

	@Override
	public long getItemId(int pos) {
		if (pos < 0 || pos >= data.length) {
			return -1;
		}
		
		return data[pos].getId();
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		Button btn = null;
		//ImageButton imgBtn = null;
		
		if (convertView == null) {
			btn = new Button(context);
			btn.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
			//btn.setImageResource(R.drawable.image_button);
			//btn.setBackgroundColor(100);
			Resources res = context.getResources();
			Drawable drawable = res.getDrawable(R.drawable.green_square);
			btn.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
			btn.setText(data[pos].getName());
		} else {
			btn = (Button) convertView;
		}		
		
		return btn;
	}

}
