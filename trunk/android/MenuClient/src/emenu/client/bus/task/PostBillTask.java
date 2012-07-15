package emenu.client.bus.task;

import java.util.List;

import org.apache.http.client.HttpClient;

import emenu.client.dao.HoaDonDAO;

public class PostBillTask extends CustomAsyncTask<Integer, Void, String> {

    private List<String> mVoucherCodes;

    public PostBillTask(List<String> voucherCodes) {
        mVoucherCodes = voucherCodes;
    }

    @Override
    protected String doInBackground(Integer... params) {
        try {
            String response = HoaDonDAO.getInstance().postLapHoaDon(params[0],
                    mVoucherCodes);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
