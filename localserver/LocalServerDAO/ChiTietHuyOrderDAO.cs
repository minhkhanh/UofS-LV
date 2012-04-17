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

        public static ChiTietHuyOrder ThemChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            ThucDonDienTu.DataContext.ChiTietHuyOrders.InsertOnSubmit(_chiTietHuyOrder);
            try
            {
                ThucDonDienTu.DataContext.SubmitChanges();

            }
            catch (Exception e)
            {
                _chiTietHuyOrder = null;
            }

            return _chiTietHuyOrder;
        }

        public static bool SuaChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.ChiTietHuyOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == _chiTietHuyOrder.ChiTietOrder.MaChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietHuyOrder or = temp.First();
                or = _chiTietHuyOrder;
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
