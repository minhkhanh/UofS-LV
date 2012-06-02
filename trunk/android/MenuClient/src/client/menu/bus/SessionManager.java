package client.menu.bus;

import java.util.ArrayList;
import java.util.List;

import android.database.DataSetObservable;
import client.menu.db.dto.BanDTO;
import client.menu.db.dto.ChiTietOrderDTO;
import client.menu.db.dto.HoaDonDTO;
import client.menu.db.dto.OrderDTO;
import client.menu.util.U;

public class SessionManager {

    public class ServiceOrder extends DataSetObservable {

        private ServiceSession mSession;
        private List<ChiTietOrderDTO> mOrderItems = new ArrayList<ChiTietOrderDTO>();
        private Integer mOrderId = -1;

        public ServiceOrder(ServiceSession session) {
            mSession = session;
        }

        public List<ChiTietOrderDTO> getUnbindedItems() {
            List<ChiTietOrderDTO> items = new ArrayList<ChiTietOrderDTO>();
            for (ChiTietOrderDTO c : mOrderItems) {
                if (c.getMaOrder() == null) {
                    items.add(c);
                }
            }

            return items;
        }

        public List<ChiTietOrderDTO> getBindedItems() {
            List<ChiTietOrderDTO> items = new ArrayList<ChiTietOrderDTO>();
            for (ChiTietOrderDTO c : mOrderItems) {
                if (c.getMaOrder() != null) {
                    items.add(c);
                }
            }

            return items;
        }

        // public void bindOrderId(Integer orderId) {
        // for (ChiTietOrderDTO c : mOrderItems) {
        // c.setMaOrder(orderId);
        // }
        //
        // mOrderId = orderId;
        // }
        //
        // public void unbindOrderId() {
        // for (ChiTietOrderDTO c : mOrderItems) {
        // c.setMaOrder(null);
        // }
        // }
        //
        // public boolean isBindedOrderId() {
        // return (mOrderId != -1);
        // }

        public void setOrderId(Integer orderId) {
            mOrderId = orderId;
        }

        public HoaDonDTO makeHoaDon() {
            HoaDonDTO hoaDon = new HoaDonDTO();
            hoaDon.setMaBanChinh(mSession.getMaBanChinh());

            return hoaDon;
        }

        public OrderDTO makeOrder() {
            OrderDTO order = new OrderDTO();
            order.setMaBan(mSession.getMaBanChinh());
            order.setMaOrder(mOrderId);

            return order;
        }

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

        private Integer mMaBanChinh;
        private ServiceOrder mOrder;

        protected ServiceSession(Integer maBanChinh) {
            mMaBanChinh = maBanChinh;
            mOrder = new ServiceOrder(this);

            mOrder.addItem(1, 1, 1, null);
            mOrder.addItem(2, 2, 2, null);
        }

        public ServiceOrder getOrder() {
            return mOrder;
        }

        public Integer getMaBanChinh() {
            return mMaBanChinh;
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

    public void destroyCurrentSession() {
        if (mIndexCurrent != -1) {
            mSessionList.remove(mIndexCurrent);
            mIndexCurrent = -1;
        }
    }

    public ServiceSession loadCurrentSession() {
        if (mIndexCurrent < 0 || mIndexCurrent >= mSessionList.size()) {
            throw new ArrayIndexOutOfBoundsException(
                    "Session list index is out of bound: " + mIndexCurrent);
        }

        return mSessionList.get(mIndexCurrent);
    }

    private ServiceSession createSession(Integer maBanChinh) {
        for (ServiceSession s : mSessionList) {
            if (s.getMaBanChinh() == maBanChinh) {
                throw new IllegalArgumentException("Duplicated session identification: "
                        + maBanChinh);
            }
        }

        ServiceSession session = new ServiceSession(maBanChinh);

        mSessionList.add(session);

        // set the latest added session as current session
        mIndexCurrent = mSessionList.size() - 1;

        U.logOwnTag("create session " + maBanChinh);
        return session;
    }

    public ServiceSession loadSession(Integer maBanChinh) {
        for (int i = 0; i < mSessionList.size(); ++i) {
            if (mSessionList.get(i).getMaBanChinh() == maBanChinh) {
                mIndexCurrent = i;
                
                U.logOwnTag("load session " + maBanChinh);
                return mSessionList.get(i);
            }
        }

        return createSession(maBanChinh);
    }
}
