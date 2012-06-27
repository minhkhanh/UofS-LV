package client.menu.ui.view;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceGroup {
    private int mDefaultSelection = -1;
    private List<CheckableInterface> mItems = new ArrayList<CheckableInterface>();

    public SingleChoiceGroup(int defaultSelection) {
        mDefaultSelection = defaultSelection;
    }

    public void toggleItem(CheckableInterface item) {
        if (item.isChecked()) {
            for (CheckableInterface c : mItems) {
                if (c != item) {
                    c.setChecked(false);
                }
            }
        } else {
            item.setChecked(true);
        }
    }

    public void addItem(CheckableInterface item) {
        mItems.add(item);

        if (mItems.size() - 1 == mDefaultSelection)
            item.setChecked(true);
    }

    public void removeItem(CheckableInterface item) {
        mItems.remove(item);
    }
}
