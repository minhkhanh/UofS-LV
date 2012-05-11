using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class BoPhanCheBienDAO
    {
        public static List<BoPhanCheBien> LayDanhSachBoPhanCheBien()
        {
            return ThucDonDienTu.DataContext.BoPhanCheBiens.ToList();
        }

        public static List<ChiTietOrder> LayDanhSachChiTietOrderCanCheBien(BoPhanCheBien boPhanCheBien)
        {
            //return
            //    ThucDonDienTu.DataContext.ChiTietOrders.Where(
            //        c =>
            //        c.BoPhanCheBien == boPhanCheBien && c.DuocPhepCheBien == true &&
            //        (!ThucDonDienTu.DataContext.ChiTietCheBienOrders.Where(bo => bo.ChiTietOrder == c).Any() || ThucDonDienTu.DataContext.ChiTietCheBienOrders.Where(bo => bo.ChiTietOrder == c).First().SoLuongDaCheBien + ThucDonDienTu.DataContext.ChiTietCheBienOrders.Where(bo => bo.ChiTietOrder == c).First().SoLuongDangCheBien < c.SoLuong)).ToList();
            return
                ThucDonDienTu.DataContext.ChiTietOrders.Where(
                    c =>
                    c.BoPhanCheBien == boPhanCheBien && c.DuocPhepCheBien ).ToList();
        }

        public static BoPhanCheBien LayBoPhanCheBienTheoMa(int maBoPhanCheBien)
        {
            var temp = ThucDonDienTu.DataContext.BoPhanCheBiens.Where(b => b.MaBoPhanCheBien == maBoPhanCheBien);
            if (temp.Count() > 0)
            {
                return temp.First();
            }
            return null;
        }
    }
}
