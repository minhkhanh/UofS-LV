using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietCheBienOrderDAO
    {
        public static ChiTietCheBienOrder LayChiTietCheBienOrder(int maChiTietOrder)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietCheBienOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietCheBienOrder or = temp.First();
                return or;
            }
            return null;
        }

        public static bool ThemChiTietCheBienOrder(ChiTietCheBienOrder _chiTietCheBienOrder)
        {
            bool result = false;
            ThucDonDienTu.DataContext.ChiTietCheBienOrders.InsertOnSubmit(_chiTietCheBienOrder);
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

        public static bool SuaChiTietCheBienOrder(ChiTietCheBienOrder _chiTietCheBienOrder)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.ChiTietCheBienOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == _chiTietCheBienOrder._maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietCheBienOrder ct = temp.First();
                ct._maChiTietOrder = _chiTietCheBienOrder._maChiTietOrder;
                ct.SoLuongDaCheBien = _chiTietCheBienOrder.SoLuongDaCheBien;
                ct.SoLuongDangCheBien = _chiTietCheBienOrder.SoLuongDangCheBien;
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
