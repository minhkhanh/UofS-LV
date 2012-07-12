package emenu.client.util;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;

public class FragmentTabListener implements TabListener {

    private Fragment mContent;
    private final String mTag;
    private boolean mIsInitDone = false;

    public FragmentTabListener(Fragment f, String tag) {
        mContent = f;
        mTag = tag;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        if (mIsInitDone) {
            ft.attach(mContent);
        } else {
            ft.add(android.R.id.content, mContent, mTag);
            mIsInitDone = true;
        }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        if (mIsInitDone) {
            ft.detach(mContent);
        }
    }
}
