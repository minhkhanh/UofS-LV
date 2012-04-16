package client.menu.adapter;

import client.menu.R;
import client.menu.dto.BanDTO;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class BanDtoButtonAdapter extends BaseAdapter {
	Context context = null;
	BanDTO[] data = new BanDTO[0];
	
	public BanDtoButtonAdapter(BanDTO[] _data, Context _context) {
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
		
		return data[pos].getMaBan();
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		Button btn = null;
		//ImageButton imgBtn = null;
		
		if (convertView == null) {
			btn = new Button(context);
			btn.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT));
			Resources res = context.getResources();
			Drawable drawable = res.getDrawable(R.drawable.green_square);
			btn.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
			btn.setText(data[pos].getTenBan());
			btn.setBackgroundColor(R.color.red);
		} else {
			btn = (Button) convertView;
		}		
		
		return btn;
	}

}
