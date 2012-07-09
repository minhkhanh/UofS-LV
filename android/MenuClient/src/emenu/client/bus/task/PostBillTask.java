package emenu.client.bus.task;

import java.util.List;

import org.apache.http.client.HttpClient;

import emenu.client.dao.HoaDonDAO;

public class PostBillTask extends CustomAsyncTask<Integer, Void, String> {

    List<String> mVoucherCodes;
    HttpClient mClient;

    public PostBillTask(HttpClient client, List<String> voucherCodes) {
        mVoucherCodes = voucherCodes;
        mClient = client;
    }

    @Override
    protected String doInBackground(Integer... params) {
        try {
            return HoaDonDAO.getInstance().postLapHoaDon(mClient, params[0], mVoucherCodes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
