package client.menu.bus;

import java.util.ArrayList;
import java.util.List;

import android.database.DataSetObservable;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.util.U;

public class SessionManager {

    public class ServiceOrder extends DataSetObservable {

        List<ChiTietOrderDTO> mOrderItems = new ArrayList<ChiTietOrderDTO>();

        public final void debugLogItems() {
            for (int i = 0; i < mOrderItems.size(); ++i) {
                U.logOwnTag(i + " : ( MaMonAn: " + mOrderItems.get(i).getMaMonAn()
                        + ", MaDonViTinh: " + mOrderItems.get(i).getMaDonViTinh()
                        + ", SoLuong: " + mOrderItems.get(i).getSoLuong() + ")");
            }
        }

        public void gather() {
            for (int i = 0; i < mOrderItems.size() - 1; ++i) {
                ChiTietOrderDTO chiTiet1 = mOrderItems.get(i);
                for (int j = i + 1; j < mOrderItems.size();) {
                    ChiTietOrderDTO chiTiet2 = mOrderItems.get(j);
                    if (chiTiet1.getMaMonAn() == chiTiet2.getMaMonAn()
                            && chiTiet1.getMaDonViTinh() == chiTiet2.getMaDonViTinh()) {
                        chiTiet1.setSoLuong(chiTiet1.getSoLuong() + chiTiet2.getSoLuong());
                        chiTiet1.setGhiChu(chiTiet1.getGhiChu() + "\n"
                                + chiTiet2.getGhiChu());

                        mOrderItems.remove(j);
                    } else {
                        ++j;
                    }
                }
            }
        }

        public List<ChiTietOrderDTO> getContent() {
            return mOrderItems;
        }

        public ChiTietOrderDTO getItem(int index) {
            return mOrderItems.get(index);
        }

        public int getCount() {
            return mOrderItems.size();
        }

        public void removeItem(ChiTietOrderDTO chiTietOrder) {
            for (int i = 0; i < mOrderItems.size(); ++i) {
                ChiTietOrderDTO c = mOrderItems.get(i);
                if (c.getMaMonAn() == chiTietOrder.getMaMonAn()
                        && c.getMaDonViTinh() == chiTietOrder.getMaDonViTinh()) {
                    mOrderItems.remove(i);
                    return;
                }
            }
        }

        public ChiTietOrderDTO addItem(Integer maMonAn, Integer maDonViTinh,
                Integer soLuong, String ghiChuMon) {
            for (ChiTietOrderDTO i : mOrderItems) {
                if (i.getMaMonAn() == maMonAn && i.getMaDonViTinh() == maDonViTinh) {
                    i.setSoLuong(i.getSoLuong() + soLuong);
                    // i.setGhiChu(ghiChuMon);
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

        private BanDTO mBan;
        private ServiceOrder mOrder;

        protected ServiceSession(BanDTO ban) {
            mBan = ban;
            mOrder = new ServiceOrder();

            mOrder.addItem(1, 1, 1, null);
            mOrder.addItem(2, 2, 2, null);
        }

        public ServiceOrder getOrder() {
            return mOrder;
        }

        public BanDTO getBan() {
            return mBan;
        }
    }

    private static SessionManager mInstance;

    List<ServiceSession> mSessionList = new ArrayList<ServiceSession>();
    int mIndexCurrent = -1;

    private SessionManager() {
    }

    public static final void createInstance() {
        mInstance = new SessionManager();
    }

    public static final SessionManager getInstance() {
        return mInstance;
    }

    public ServiceSession loadCurrentSession() {
        if (mIndexCurrent < 0 || mIndexCurrent >= mSessionList.size()) {
            throw new ArrayIndexOutOfBoundsException(
                    "Session list index is out of bound: " + mIndexCurrent);
        }

        return mSessionList.get(mIndexCurrent);
    }

    private ServiceSession createSession(BanDTO ban) {
        for (ServiceSession s : mSessionList) {
            if (s.getBan().getMaBan() == ban.getMaBan()) {
                throw new IllegalArgumentException("Duplicated session identification: "
                        + ban.getMaBan());
            }
        }

        ServiceSession session = new ServiceSession(ban);

        mSessionList.add(session);

        // set the latest added session as current session
        mIndexCurrent = mSessionList.size() - 1;

        return session;
    }

    public ServiceSession loadSession(BanDTO ban) {
        for (int i = 0; i < mSessionList.size(); ++i) {
            if (mSessionList.get(i).getBan().getMaBan() == ban.getMaBan()) {
                mIndexCurrent = i;
                return mSessionList.get(i);
            }
        }

        return createSession(ban);
    }
}
