package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import emenu.client.dao.AbstractDAO;
import emenu.client.dao.DonViTinhDAO;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.db.dto.KhuyenMaiDTO;
import emenu.client.db.dto.NgonNguDTO;
import emenu.client.menu.app.CustomerLocale;
import emenu.client.menu.app.MenuApplication;
import emenu.client.util.U;

import android.content.ContentValues;

public class GetBillItemsTask extends CustomAsyncTask<Integer, Void, List<ContentValues>> {

    private Integer mLangId = MenuApplication.getInstance().customerLocale.getLanguage()
            .getMaNgonNgu();

    private void addMixedValues(List<ContentValues> listValues,
            List<ChiTietOrderDTO> listDto, Integer langId) {
        for (ChiTietOrderDTO c : listDto) {
            ContentValues v = new ContentValues();
            v = c.toContentValues();
            v.putAll(DonViTinhDAO.getInstance().contentByDishUnitWithProm(langId,
                    c.getMaMonAn(), c.getMaDonViTinh()));

            listValues.add(v);
        }
    }

    @Override
    protected List<ContentValues> doInBackground(Integer... params) {
        String url = AbstractDAO.SERVER_URL_SLASH
                + "layDanhSachChiTietOrderJson?maOrder=" + params[0];

        List<ContentValues> result = new ArrayList<ContentValues>();
        List<ChiTietOrderDTO> list = new ArrayList<ChiTietOrderDTO>();

        try {
            String response = U.loadGetResponse(url);
            JSONArray jsonArray = new JSONArray(response);
            list = ChiTietOrderDTO.fromArrayJson(jsonArray);

            for (ChiTietOrderDTO c : list) {
                ContentValues v = new ContentValues();
                v = c.toContentValues();
                v.putAll(DonViTinhDAO.getInstance().contentByDishUnitWithProm(mLangId,
                        c.getMaMonAn(), c.getMaDonViTinh()));

                // service url may be changed!
                url = AbstractDAO.SERVER_URL_SLASH
                        + "timKhuyenMaiMonJson?maChiTietOrder=" + c.getMaChiTiet();

                response = U.loadGetResponse(url);
                if (response != null && !response.equals("")) {
                    ContentValues valuesKhuyenMai = KhuyenMaiDTO
                            .toContentValues(new JSONObject(response));
                    v.putAll(valuesKhuyenMai);
                    // Integer unitPrice =
                    // v.getAsInteger(DonViTinhMonAnDTO.CL_DON_GIA);
                    // Float promValue = valuesKhuyenMai
                    // .getAsFloat(KhuyenMaiDTO.CL_GIA_GIAM);
                    // Float promRate = valuesKhuyenMai
                    // .getAsFloat(KhuyenMaiDTO.CL_TI_LE_GIAM);
                    // if (promValue == null || promRate == null) {
                    // v.put(KhuyenMaiDTO.CL_EX_PROM_TOTAL, 0f);
                    // } else {
                    // v.put(KhuyenMaiDTO.CL_EX_PROM_TOTAL, promValue + promRate
                    // * unitPrice);
                    // }
                }

                result.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
