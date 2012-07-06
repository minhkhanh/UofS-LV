package emenu.client.db.dto;

import java.util.List;

public class ChiTietNhomBan {
    BanDTO mBanChinh;
    List<BanDTO> mListBanPhu;
    
    public BanDTO getBanChinh() {
        return mBanChinh;
    }
    public void setBanChinh(BanDTO banChinh) {
        mBanChinh = banChinh;
    }
    public List<BanDTO> getListBanPhu() {
        return mListBanPhu;
    }
    public void setListBanPhu(List<BanDTO> listBanPhu) {
        mListBanPhu = listBanPhu;
    }
    
    
}
