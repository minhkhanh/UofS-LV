package client.menu.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.DonViTinhMonAnDTO;

public class SessionManager {

    public class ServiceSession {

        private Integer mMaBan;
        List<ChiTietOrderDTO> mOrderItems = new ArrayList<ChiTietOrderDTO>();

        protected ServiceSession(int maBan) {
            mMaBan = maBan;
        }

        public void addOrderItem(Integer maMonAn, Integer maDonViTinh) {
            for (ChiTietOrderDTO i : mOrderItems) {
                if (i.getMaMonAn() == maMonAn && i.getMaDonViTinh() == maDonViTinh) {
                    i.setSoLuong(i.getSoLuong() + 1);
                    return;
                }
            }

            ChiTietOrderDTO chiTiet = new ChiTietOrderDTO();
            chiTiet.setMaMonAn(maMonAn);
            chiTiet.setMaDonViTinh(maDonViTinh);
            chiTiet.setSoLuong(1);
            mOrderItems.add(chiTiet);
        }

        public Integer getMaBan() {
            return mMaBan;
        }

        public void setMaBan(Integer maBan) {
            mMaBan = maBan;
        }

        public List<ChiTietOrderDTO> getOrderItems() {
            return mOrderItems;
        }
        
        
    }

    List<ServiceSession> mSessionList = new ArrayList<ServiceSession>();
    int mIndexCurrent = -1;

    private ServiceSession createSession(Integer maBan) {
        ServiceSession session = new ServiceSession(maBan);

        mSessionList.add(session);

        // set the latest added session as current session
        mIndexCurrent = mSessionList.size() - 1;

        return session;
    }

    public ServiceSession loadCurrentSession() {
        return loadSession(mIndexCurrent);
    }

    public ServiceSession loadSession(Integer maBan) {
        for (int i = 0; i < mSessionList.size(); ++i) {
            if (mSessionList.get(i).getMaBan() == maBan) {
                mIndexCurrent = i;
                return mSessionList.get(i);
            }
        }

        return createSession(maBan);
    }
}
