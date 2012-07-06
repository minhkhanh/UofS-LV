package emenu.client.menu.adapter;

import android.content.ContentValues;

public interface IDishSwappableAdapter {
    // these method must match CustomArrayAdapter method name
    ContentValues getItem(int position);
    void remove(int position);
    int getCount();
    void add(ContentValues c);
    void add(int position, ContentValues c);
    void notifyDataSetChanged();
    // end
    
    
}
