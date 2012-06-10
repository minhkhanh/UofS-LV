package client.menu.ui.adapter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import client.menu.db.dto.BanDTO;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TableGroupsAdapter extends CustomArrayAdapter<JSONObject> {

    public TableGroupsAdapter(Context context, List<JSONObject> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String text = "";
        try {
            JSONArray jsonArray = getItem(position).getJSONArray("listChild");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                text += jsonObj.getString(BanDTO.CL_TEN_BAN);
                text += ",";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        TextView t = new TextView(getContext());
        t.setText(text);
        
        return t;
    }
}
