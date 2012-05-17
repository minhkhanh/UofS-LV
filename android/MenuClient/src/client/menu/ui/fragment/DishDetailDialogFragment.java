package client.menu.ui.fragment;

import client.menu.R;
import client.menu.ui.activity.MainMenuActivity;
import client.menu.util.U;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class DishDetailDialogFragment extends DialogFragment {
    
    Integer mMaMonAn;

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnOK:
//                    U.toastText(getActivity(), "Xác thực thành công!");

//                    Intent intent = new Intent(getActivity(), MainMenuActivity.class);
//                    startActivity(intent);

                    dismiss();
                    break;
                case R.id.btnCancel:
                    dismiss();
                    break;
            }
        }
    };
    
    public DishDetailDialogFragment(Integer maMonAn) {
        mMaMonAn = maMonAn;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // getDialog().setTitle(getString(R.string.title_dialog_auth));
        getDialog().setCanceledOnTouchOutside(false);

        View layout = inflater.inflate(R.layout.dialog_dish_detail, container, false);

        return layout;
    }
}
