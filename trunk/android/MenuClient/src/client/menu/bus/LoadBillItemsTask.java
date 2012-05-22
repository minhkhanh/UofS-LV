package client.menu.bus;

import java.util.ArrayList;
import java.util.List;

import client.menu.app.MyAppLocale;
import client.menu.db.dao.DonViTinhDAO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.ui.activity.BillActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;

public class LoadBillItemsTask extends AsyncTask<Void, Integer, List<ContentValues>> {
    private Activity mHost;
    private List<ChiTietOrderDTO> mOrderItems;
    
    public LoadBillItemsTask(Activity host, List<ChiTietOrderDTO> orderItems) {
        mHost = host;
        mOrderItems = orderItems;
    }

    @Override
    protected List<ContentValues> doInBackground(Void... params) {
        List<ContentValues> result = new ArrayList<ContentValues>();
        NgonNguDTO n = MyAppLocale.getCurrentLanguage(mHost);

        for (ChiTietOrderDTO c : mOrderItems) {
            ContentValues values = DonViTinhDAO.getInstance()
                    .contentByDonViTinhMonAn(c.getMaMonAn(), c.getMaDonViTinh(),
                            n.getMaNgonNgu());
            c.to(values);

            result.add(values);
        }

        return result;
    }

}
