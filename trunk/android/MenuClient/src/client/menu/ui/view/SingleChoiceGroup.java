package client.menu.ui.view;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceGroup {
    private int mDefaultSelection = -1;
    private List<CheckMarkView> mItems = new ArrayList<CheckMarkView>();

    public SingleChoiceGroup(int defaultSelection) {
        mDefaultSelection = defaultSelection;
    }

    public void toggleItem(CheckMarkView item) {
        if (item.isChecked()) {
            for (CheckMarkView c : mItems) {
                if (c != item) {
                    c.setChecked(false);
                }
            }
        } else {
            item.setChecked(true);
        }
    }

    public void addItem(CheckMarkView item) {
        mItems.add(item);

        if (mItems.size() - 1 == mDefaultSelection)
            item.setChecked(true);
    }

    public void removeItem(CheckMarkView item) {
        mItems.remove(item);
    }
}
