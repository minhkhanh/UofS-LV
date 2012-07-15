package emenu.client.db.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TableSelection {

    public static class TableIdSelection {
        private Integer mMainTabId;
        private List<Integer> mTabIdList;

        public static final String COL_MAINTAB = "MainTabId";
        public static final String COL_TABLIST = "TabIdList";

        public JSONObject toJson() throws JSONException {
            JSONObject jsonObject = new JSONObject();
            if (mMainTabId != null)
                jsonObject.put(COL_MAINTAB, mMainTabId);

            JSONArray jsonArray = new JSONArray();
            if (mTabIdList != null) {
                for (Integer b : mTabIdList) {
                    jsonArray.put(b);
                }
            }
            jsonObject.put(COL_TABLIST, jsonArray);

            return jsonObject;
        }

        public Integer getMainTabId() {
            return mMainTabId;
        }

        public void setMainTabId(Integer mainTabId) {
            mMainTabId = mainTabId;
        }

        public List<Integer> getTabIdList() {
            return mTabIdList;
        }

        public void setTabIdList(List<Integer> tabIdList) {
            mTabIdList = tabIdList;
        }
    }

    private BanDTO mMainTab;
    private List<BanDTO> mTabList;

    public enum SelectionState {
        None, SingleFree, SingleBusy, MultiFree, Mixed, GroupBusy
    }

    public BanDTO findTableById(Integer id) {
        for (BanDTO b : mTabList) {
            if (b.getMaBan() == id)
                return b;
        }

        return null;
    }

    public TableIdSelection createIdSelection() {
        TableIdSelection idSelection = new TableIdSelection();
        if (mMainTab != null)
            idSelection.setMainTabId(mMainTab.getMaBan());
        if (mTabList != null) {
            List<Integer> idList = new ArrayList<Integer>();
            for (BanDTO b : mTabList) {
                idList.add(b.getMaBan());
            }
            idSelection.mTabIdList = idList;
        }

        return idSelection;
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
        boolean hasBusy = false;
        for (BanDTO b : mTabList) {
            if (b.getMaBanChinh() == null)
                hasFree = true;
            else
                hasBusy = true;

            if (hasFree && hasBusy)
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

    private void findMainTab() {
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
