package client.menu.bus;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import client.menu.app.MyApplication;
import client.menu.db.dto.ChiTietOrderDTO;

public class SessionManager {

    public class ServiceOrder {
        List<ChiTietOrderDTO> mOrderItems = new ArrayList<ChiTietOrderDTO>();
        
        public List<ChiTietOrderDTO> getData() {
            return mOrderItems;
        }

        public ChiTietOrderDTO getItem(int index) {
            return mOrderItems.get(index);
        }

        public int getCount() {
            return mOrderItems.size();
        }

        public int getItemQuantity(Integer maMonAn, Integer maDonViTinh) {
            for (int i = 0; i < mOrderItems.size(); ++i) {
                ChiTietOrderDTO chiTiet = mOrderItems.get(i);
                if (chiTiet.getMaMonAn() == maMonAn
                        && chiTiet.getMaDonViTinh() == maDonViTinh) {
                    return chiTiet.getSoLuong();
                }
            }

            return 0;
        }

        public ChiTietOrderDTO addItem(Integer maMonAn, Integer maDonViTinh,
                Integer soLuong, String ghiChuMon) {
            for (ChiTietOrderDTO i : mOrderItems) {
                if (i.getMaMonAn() == maMonAn && i.getMaDonViTinh() == maDonViTinh) {
                    i.setSoLuong(i.getSoLuong() + 1);
                    i.setGhiChu(ghiChuMon);
                    return i;
                }
            }

            ChiTietOrderDTO chiTiet = new ChiTietOrderDTO();
            chiTiet.setMaMonAn(maMonAn);
            chiTiet.setMaDonViTinh(maDonViTinh);
            chiTiet.setSoLuong(soLuong);
            chiTiet.setGhiChu(ghiChuMon);
            mOrderItems.add(chiTiet);

            return chiTiet;
        }
    }

    public class ServiceSession {

        private Integer mMaBan;
        private ServiceOrder mOrder = new ServiceOrder();

        protected ServiceSession(Integer maBan) {
            mMaBan = maBan;
        }

        public ServiceOrder getOrder() {
            return mOrder;
        }

        public Integer getMaBan() {
            return mMaBan;
        }

        public void setMaBan(Integer maBan) {
            mMaBan = maBan;
        }
    }

    List<ServiceSession> mSessionList = new ArrayList<ServiceSession>();
    int mIndexCurrent = -1;

    public static final ServiceSession loadCurrentSession(Activity activity) {
        SessionManager manager = MyApplication.getSessionManager(activity);

        if (manager.mIndexCurrent < 0
                || manager.mIndexCurrent >= manager.mSessionList.size()) {
            throw new ArrayIndexOutOfBoundsException(
                    "Session list index is out of bound: " + manager.mIndexCurrent);
        }

        return manager.mSessionList.get(manager.mIndexCurrent);
    }

    private ServiceSession createSession(Integer maBan) {
        for (ServiceSession s : mSessionList) {
            if (s.getMaBan() == maBan) {
                throw new IllegalArgumentException("Duplicated session identification: "
                        + maBan);
            }
        }

        ServiceSession session = new ServiceSession(maBan);

        mSessionList.add(session);

        // set the latest added session as current session
        mIndexCurrent = mSessionList.size() - 1;

        return session;
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
