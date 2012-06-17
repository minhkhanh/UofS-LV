package client.menu.bus.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import client.menu.dao.AbstractDAO;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.ChiTietNhomBan;
import client.menu.util.U;

import android.content.Context;

public class GetTableGroupsTask extends
        CustomAsyncTask<Integer, Void, List<ChiTietNhomBan>> {
    @Override
    protected List<ChiTietNhomBan> doInBackground(Integer... params) {
        String url = AbstractDAO.LOCAL_SERVER_URL + "layDanhSachBanChinhJson?maKhuVuc="
                + params[0];
        List<ChiTietNhomBan> list = new ArrayList<ChiTietNhomBan>();
        try {
            String response = U.loadGetResponse(url);
            JSONArray listGroup = new JSONArray(response);
            for (int i = 0; i < listGroup.length(); ++i) {
                JSONObject group = listGroup.getJSONObject(i);
                url = AbstractDAO.LOCAL_SERVER_URL
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
