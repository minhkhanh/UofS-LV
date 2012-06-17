package client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import client.menu.app.MyAppLocale;
import client.menu.app.MyApplication;
import client.menu.dao.AbstractDAO;
import client.menu.dao.DonViTinhDAO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;
import client.menu.db.dto.KhuyenMaiDTO;
import client.menu.db.dto.NgonNguDTO;
import client.menu.util.U;

import android.content.ContentValues;

public class GetBillItemsTask extends CustomAsyncTask<Integer, Void, List<ContentValues>> {

    private void addMixedValues(List<ContentValues> listValues,
            List<ChiTietOrderDTO> listDto, Integer langId) {
        for (ChiTietOrderDTO c : listDto) {
            ContentValues v = new ContentValues();
            v = c.toContentValues();
            v.putAll(DonViTinhDAO.getInstance().contentByDonViTinhMonAn(langId,
                    c.getMaMonAn(), c.getMaDonViTinh()));

            listValues.add(v);
        }
    }

    @Override
    protected List<ContentValues> doInBackground(Integer... params) {
        String url = AbstractDAO.LOCAL_SERVER_URL
                + "layDanhSachChiTietOrderJson?maOrder=" + params[0];

        NgonNguDTO nn = MyAppLocale.getCurrentLanguage(MyApplication.getInstance());

        List<ContentValues> result = new ArrayList<ContentValues>();
        List<ChiTietOrderDTO> list = new ArrayList<ChiTietOrderDTO>();

        try {
            String response = U.loadGetResponse(url);
            JSONArray jsonArray = new JSONArray(response);
            list = ChiTietOrderDTO.fromArrayJson(jsonArray);

            for (ChiTietOrderDTO c : list) {
                ContentValues v = new ContentValues();
                v = c.toContentValues();
                v.putAll(DonViTinhDAO.getInstance().contentByDonViTinhMonAn(
                        nn.getMaNgonNgu(), c.getMaMonAn(), c.getMaDonViTinh()));

                url = AbstractDAO.LOCAL_SERVER_URL
                        + "timKhuyenMaiMonJson?maChiTietOrder=" + c.getMaChiTiet();

                response = U.loadGetResponse(url);
                if (response != null && response != "") {
                    ContentValues valuesKhuyenMai = KhuyenMaiDTO
                            .toContentValues(new JSONObject(response));
                    v.putAll(valuesKhuyenMai);
//                    Integer unitPrice = v.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);
//                    Float promValue = valuesKhuyenMai
//                            .getAsFloat(KhuyenMaiDTO.CL_GIA_GIAM);
//                    Float promRate = valuesKhuyenMai
//                            .getAsFloat(KhuyenMaiDTO.CL_TI_LE_GIAM);
//                    if (promValue == null || promRate == null) {
//                        v.put(KhuyenMaiDTO.CL_EX_PROM_TOTAL, 0f);
//                    } else {
//                        v.put(KhuyenMaiDTO.CL_EX_PROM_TOTAL, promValue + promRate
//                                * unitPrice);
//                    }
                }

                result.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
