package emenu.client.bus.task;

import java.util.List;

import emenu.client.dao.HoaDonDAO;

public class PostBillTask extends CustomAsyncTask<Integer, Void, String> {

    List<String> mVoucherCodes;

    public PostBillTask(List<String> voucherCodes) {
        mVoucherCodes = voucherCodes;
    }

    @Override
    protected String doInBackground(Integer... params) {
        try {
            return HoaDonDAO.getInstance().postLapHoaDon(params[0], mVoucherCodes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
