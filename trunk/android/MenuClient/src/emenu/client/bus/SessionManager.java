package emenu.client.bus;

import java.util.ArrayList;
import java.util.List;

import android.database.DataSetObservable;
import emenu.client.db.dto.ChiTietOrderDTO;
import emenu.client.menu.util.U;

public class SessionManager {

    public static class OrderItemId {
        public static final OrderItemId fromOrderItem(ChiTietOrderDTO c) {
            return new OrderItemId(c.getMaMonAn(), c.getMaDonViTinh());
        }

        Integer mDishId;
        Integer mUnitId;

        public OrderItemId(Integer dishId, Integer unitId) {
            mDishId = dishId;
            mUnitId = unitId;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof OrderItemId))
                return false;

            OrderItemId thatId = (OrderItemId) o;

            return mDishId == thatId.mDishId && mUnitId == thatId.mUnitId;
        }
    }

    public class ServiceOrder extends DataSetObservable {

        private List<ChiTietOrderDTO> mOrderItems = new ArrayList<ChiTietOrderDTO>();

        public void clear() {
            mOrderItems.clear();
        }

        public final void logItemsDebug() {
            for (int i = 0; i < mOrderItems.size(); ++i) {
                U.logOwnTag(i + " : ( MaMonAn: " + mOrderItems.get(i).getMaMonAn()
                        + ", MaDonViTinh: " + mOrderItems.get(i).getMaDonViTinh()
                        + ", SoLuong: " + mOrderItems.get(i).getSoLuong() + ", GhiChu: "
                        + mOrderItems.get(i).getGhiChu() + ")");
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

        public List<ChiTietOrderDTO> getOrderItems() {

            return mOrderItems;
        }

        public ChiTietOrderDTO getItem(OrderItemId id) {
            for (int i = 0; i < mOrderItems.size(); ++i) {
                ChiTietOrderDTO c = mOrderItems.get(i);
                if (OrderItemId.fromOrderItem(c).equals(id))
                    return c;
            }

            return null;
        }

        public ServiceOrder removeItem(OrderItemId id) {
            for (int i = 0; i < mOrderItems.size(); ++i) {
                if (OrderItemId.fromOrderItem(mOrderItems.get(i)).equals(id)) {
                    mOrderItems.remove(i);
                    return this;
                }
            }

            return this;
        }

        public ChiTietOrderDTO addItem(Integer maMonAn, Integer maDonViTinh,
                Integer soLuong, String ghiChuMon) {
            if (ghiChuMon == null)
                ghiChuMon = "";

            for (ChiTietOrderDTO i : mOrderItems) {
                if (i.getMaMonAn() == maMonAn && i.getMaDonViTinh() == maDonViTinh) {
                    i.setSoLuong(i.getSoLuong() + soLuong);
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

        private Integer mOrderId;
        private ServiceOrder mOrder;

        protected ServiceSession(Integer orderId) {
            mOrderId = orderId;
            mOrder = new ServiceOrder();
            // mOrder.addItem(1, 1, 1, null);
            // mOrder.addItem(2, 2, 2, null);
        }

        public ServiceOrder bindOrder() {
            for (ChiTietOrderDTO c : mOrder.mOrderItems) {
                c.setMaOrder(mOrderId);
                c.setTinhTrang(0);
            }

            return mOrder;
        }

        // public void unbindOrder() {
        // for (ChiTietOrderDTO c : mOrder.mOrderItems) {
        // c.setMaOrder(-1);
        // c.setTinhTrang(-1);
        // }
        // }

        public ServiceOrder getOrder() {
            return mOrder;
        }

        public Integer getOrderId() {
            return mOrderId;
        }

        public void finish() {
            mOrderId = -1;
            mOrder.clear();
        }

        public boolean isFinished() {
            return mOrderId == -1;
        }
    }

    private static SessionManager mInstance;

    List<ServiceSession> mSessionList = new ArrayList<ServiceSession>();
    int mCurrentPos = -1;
    int mLastFinishedPos = -1;

    private SessionManager() {
    }

    public static final void createInstance() {
        mInstance = new SessionManager();
    }

    public static final SessionManager getInstance() {
        return mInstance;
    }

    public void finishCurrentSession() {
        if (mCurrentPos < 0 || mCurrentPos >= mSessionList.size()) {
            throw new ArrayIndexOutOfBoundsException(
                    "Current session not found with index = " + mCurrentPos);
        }

        mSessionList.get(mCurrentPos).finish();
        mLastFinishedPos = mCurrentPos;

        U.logOwnTag("finish current session");
    }

    public ServiceSession loadCurrentSession() {
        if (mCurrentPos < 0 || mCurrentPos >= mSessionList.size()) {
            throw new ArrayIndexOutOfBoundsException(
                    "Current session not found with index = " + mCurrentPos);
        }

        return mSessionList.get(mCurrentPos);
    }

    public ServiceSession loadSession(Integer orderId) {
        for (int i = 0; i < mSessionList.size(); ++i) {
            if (mSessionList.get(i).getOrderId() == orderId) {
                mCurrentPos = i;

                U.logOwnTag("load existing session " + orderId);
                return mSessionList.get(i);
            }
        }

        ServiceSession session = new ServiceSession(orderId);

        if (mLastFinishedPos >= 0) {
            mSessionList.add(mLastFinishedPos, session);
            mCurrentPos = mLastFinishedPos;
            mLastFinishedPos = -1;
        } else {
            mSessionList.add(session);
            // set the latest added session as current session
            mCurrentPos = mSessionList.size() - 1;
        }

        U.logOwnTag("load new session " + orderId);
        return session;
    }
}
