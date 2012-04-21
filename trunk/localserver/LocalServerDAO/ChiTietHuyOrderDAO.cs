using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietHuyOrderDAO
    {
        public static ChiTietHuyOrder LayChiTietHuyOrder(int maChiTietOrder)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietHuyOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietHuyOrder or = temp.First();
                return or;
            }
            return null;
        }

        public static bool ThemChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            bool result = false;
            ThucDonDienTu.DataContext.ChiTietHuyOrders.InsertOnSubmit(_chiTietHuyOrder);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
                result = true;
            }
            catch (Exception e)
            {
                result = false;
            }

            return result;
        }

        public static bool SuaChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.ChiTietHuyOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == _chiTietHuyOrder._maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietHuyOrder ct = temp.First();
                ct._maChiTietOrder = _chiTietHuyOrder._maChiTietOrder;
                ct.SoLuongChoPhep = _chiTietHuyOrder.SoLuongChoPhep;
                ct.SoLuongYeuCau = _chiTietHuyOrder.SoLuongYeuCau;
                ct.TinhTrangHuy = _chiTietHuyOrder.TinhTrangHuy;
            }

            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();
                result = true;
            }
            catch (Exception e)
            {
                result = false;
            }

            return result;
        }
    }
}
