using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class ChiTietOrderDAO
    {
        public static ChiTietOrder LayChiTietOrder(int maChiTietOrder)
        {
            var temp = ThucDonDienTu.DataContext.ChiTietOrders.Where(m => m.MaChiTietOrder == maChiTietOrder);
            if (temp.Count() > 0)
            {
                ChiTietOrder ct = temp.First();
                return ct;
            }
            return null;
        }

        public static bool ThemChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            bool result = false;
            ThucDonDienTu.DataContext.ChiTietOrders.InsertOnSubmit(_chiTietOrder);
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
