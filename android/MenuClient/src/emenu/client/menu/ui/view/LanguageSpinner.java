package emenu.client.menu.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import emenu.client.db.dto.NgonNguDTO;

public class LanguageSpinner extends Spinner {

    public LanguageSpinner(Context context, AttributeSet attrs, int defStyle, int mode) {
        super(context, attrs, defStyle, mode);
    }

    public LanguageSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LanguageSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LanguageSpinner(Context context, int mode) {
        super(context, mode);
    }

    public LanguageSpinner(Context context) {
        super(context);
    }

    public void selectItem(NgonNguDTO ngonNgu) {
        SpinnerAdapter adapter = getAdapter();
        for (int i = 0; i < adapter.getCount(); ++i) {
            if (adapter.getItem(i) instanceof NgonNguDTO) {
                NgonNguDTO n = (NgonNguDTO) adapter.getItem(i);
                if (n.getKiHieu().compareTo(ngonNgu.getKiHieu()) == 0) {
                    setSelection(i);
                    return;
                }
            }
        }
    }
}
