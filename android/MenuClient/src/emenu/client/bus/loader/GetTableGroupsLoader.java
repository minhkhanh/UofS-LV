package emenu.client.bus.loader;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import emenu.client.dao.AbstractDAO;
import emenu.client.db.dto.BanDTO;
import emenu.client.db.dto.ChiTietNhomBan;
import emenu.client.util.U;
import android.app.Activity;

public class GetTableGroupsLoader extends CustomAsyncTaskLoader<List<ChiTietNhomBan>> {

    Integer mMaKhuVuc;

    public GetTableGroupsLoader(Activity context, Integer maKhuVuc) {
        super(context);

        mMaKhuVuc = maKhuVuc;
    }

    @Override
    public List<ChiTietNhomBan> loadInBackground() {
        String url = AbstractDAO.SERVER_URL_SLASH + "layDanhSachBanChinhJson?maKhuVuc="
                + mMaKhuVuc;
        List<ChiTietNhomBan> list = new ArrayList<ChiTietNhomBan>();
        try {
            String response = U.loadGetResponse(url);
            JSONArray listGroup = new JSONArray(response);
            for (int i = 0; i < listGroup.length(); ++i) {
                JSONObject group = listGroup.getJSONObject(i);
                url = AbstractDAO.SERVER_URL_SLASH
                        + "layDanhSachBanThuocBanChinhJson?maBanChinh="
                        + group.getInt(BanDTO.CL_MA_BAN_CHINH);
                response = U.loadGetResponse(url);
                JSONArray listChild = new JSONArray(response);

                BanDTO banChinh = BanDTO.fromJson(group);
                List<BanDTO> listBanPhu = BanDTO.fromArrayJson(listChild);
                ChiTietNhomBan ct = new ChiTietNhomBan();
                ct.setBanChinh(banChinh);
                ct.setListBanPhu(listBanPhu);

                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
