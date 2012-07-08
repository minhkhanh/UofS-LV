package emenu.client.db.dto;

import java.util.ArrayList;
import java.util.List;

public class TableSelection {

    private BanDTO mMainTab;
    private List<BanDTO> mTabList;

    public enum SelectionState {
        None, SingleFree, SingleBusy, MultiFree, Mixed, GroupBusy
    }

    public List<Integer> getTabIds() {
        List<Integer> list = new ArrayList<Integer>();
        for (BanDTO b : mTabList) {
            list.add(b.getMaBan());
        }

        return list;
    }

    public SelectionState getState() {
        if (mTabList == null)
            return SelectionState.None;

        if (mTabList.size() == 1) {
            if (mTabList.get(0).getMaBanChinh() == null) {
                return SelectionState.SingleFree;
            }

            return SelectionState.SingleBusy;
        }

        boolean hasFree = false;
        for (BanDTO b : mTabList) {
            if (b.getMaBanChinh() == null)
                hasFree = true;
            else if (hasFree)
                return SelectionState.Mixed;
        }

        if (hasFree)
            return SelectionState.MultiFree;

        return SelectionState.GroupBusy;
    }

    public BanDTO getMainTab() {
        return mMainTab;
    }

    public void setMainTab(BanDTO banChinh) {
        mMainTab = banChinh;
    }

    public List<BanDTO> getTabList() {
        return mTabList;
    }

    public void findMainTab() {
        if (mTabList == null || mTabList.size() == 0)
            return;
        
        mMainTab = null;
        for (BanDTO b : mTabList) {
            if (b.getMaBanChinh() != null) {
                mMainTab = b;
                break;
            }
        }

        if (mMainTab == null)
            mMainTab = mTabList.get(0);
    }

    public void setTabList(List<BanDTO> listBan) {
        mTabList = listBan;

        findMainTab();
    }

}
