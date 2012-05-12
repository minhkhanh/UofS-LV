using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietKhongCheBienOrderDAO
    {
        public static ChiTietKhongCheBienOrder LayChiTietKhongCheBienOrder(int maChiTietKhongCheBienOrder)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietKhongCheBienOrders.Where(c => c.ChiTietOrder.MaChiTietOrder == maChiTietKhongCheBienOrder);
            if (temp.Count() > 0)
            {
                ChiTietKhongCheBienOrder or = temp.First();
                return or;
            }
            return null;
        }

        public static bool Them(ChiTietKhongCheBienOrder chiTietKhongCheBienOrder)
        {
            bool result = false;
            ThucDonDienTu.DataContext.ChiTietKhongCheBienOrders.InsertOnSubmit(chiTietKhongCheBienOrder);
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

        public static bool CapNhat(ChiTietKhongCheBienOrder chiTietKhongCheBienOrder)
        {
            bool result = false;
            //var temp = LayChiTietKhongCheBienOrder(chiTietKhongCheBienOrder.MaChiTietKhongCheBienOrder);
            //if (temp != null)
            //{
            //    temp.SoLuongKhongCheBien = chiTietKhongCheBienOrder.SoLuongKhongCheBien;
            //}

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
