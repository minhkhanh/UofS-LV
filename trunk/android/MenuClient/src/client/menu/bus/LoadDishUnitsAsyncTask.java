package client.menu.bus;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import client.menu.R;
import client.menu.app.MyAppLocale;
import client.menu.db.contract.DonViTinhDaNgonNguContract;
import client.menu.db.contract.DonViTinhMonAnContract;

public class LoadDishUnitsAsyncTask extends AsyncTask<Void, Integer, SimpleCursorAdapter> {

    private Activity mHostActivity;
    private AdapterView<SimpleCursorAdapter> mAdapterView;
    private Integer mMaMonAn;
    private Integer mMaDonViTinhChon;

    private int mSelectedIndex = -1;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public LoadDishUnitsAsyncTask(Activity activity, AdapterView view, Integer maMonAn,
            Integer maDonViTinh) {
        mHostActivity = activity;
        mAdapterView = view;
        mMaMonAn = maMonAn;
        mMaDonViTinhChon = maDonViTinh;
    }

    @Override
    protected void onPostExecute(SimpleCursorAdapter result) {
        super.onPostExecute(result);

        mAdapterView.setAdapter(result);
        mAdapterView.setSelection(mSelectedIndex);
        mAdapterView.setFocusable(false); // does the magic!
    }

    @Override
    protected SimpleCursorAdapter doInBackground(Void... params) {
        String[] projection = null;

        String selection = DonViTinhMonAnContract.CL_MA_MON_AN + "=? and "
                + DonViTinhDaNgonNguContract.CL_MA_NGON_NGU + "=?";

        String[] selArgs = new String[] { mMaMonAn.toString(),
                MyAppLocale.getCurrentLanguage(mHostActivity).getMaNgonNgu().toString() };

        Cursor cursor = mHostActivity.getContentResolver().query(
                DonViTinhMonAnContract.URI_DONVITINHMONAN_INNER_DANGONNGU, projection,
                selection, selArgs, null);

        if (cursor == null || mMaDonViTinhChon == null) {
            mSelectedIndex = -1;
        } else {
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor
                        .getColumnIndex(DonViTinhMonAnContract.CL_MA_DON_VI)) == mMaDonViTinhChon) {
                    mSelectedIndex = cursor.getPosition();
                    break;
                }
            }

            cursor.moveToPosition(-1);
        }

        // U.logOwnTag("selIndex " + String.valueOf(mSelectedIndex));

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(mHostActivity,
                R.layout.item_dish_units_spinner, cursor, new String[] {
                        DonViTinhDaNgonNguContract.CL_TEN_DON_VI,
                        DonViTinhMonAnContract.CL_DON_GIA }, new int[] {
                        R.id.textUnitName, R.id.textUnitPrice }, 0);
        adapter.setDropDownViewResource(R.layout.item_dish_units_spinner);

        return adapter;
    }
}
