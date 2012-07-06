package emenu.client.menu.view;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceGroup {
    private int mDefaultSelection = -1;
    private List<ICheckable> mItems = new ArrayList<ICheckable>();

    public SingleChoiceGroup(int defaultSelection) {
        mDefaultSelection = defaultSelection;
    }

    public void toggleItem(ICheckable item) {
        if (item.isChecked()) {
            for (ICheckable c : mItems) {
                if (c != item) {
                    c.setChecked(false);
                }
            }
        } else {
            item.setChecked(true);
        }
    }

    public void addItem(ICheckable item) {
        mItems.add(item);

        if (mItems.size() - 1 == mDefaultSelection)
            item.setChecked(true);
    }

    public void removeItem(ICheckable item) {
        mItems.remove(item);
    }
}
