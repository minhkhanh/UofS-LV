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
                    c.BoPhanCheBien == boPhanCheBien && c.TinhTrang != 3 && c.Order.TinhTrang!=3 ).ToList();
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

        public static List<BoPhanCheBien> LayBoPhanCheBienTheoTaiKhoan(TaiKhoan taiKhoan)
        {
            return  ThucDonDienTu.DataContext.BoPhanCheBiens.Where(b => b.TaiKhoan == taiKhoan).ToList();
        }

        public static bool Xoa(int maBoPhanCheBien)
        {
            try
            {
                var objBoPhanCheBien = LayBoPhanCheBienTheoMa(maBoPhanCheBien);
                ThucDonDienTu.DataContext.BoPhanCheBiens.DeleteOnSubmit(objBoPhanCheBien);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool Them(BoPhanCheBien boPhanCheBien)
        {
            try
            {
                ThucDonDienTu.DataContext.BoPhanCheBiens.InsertOnSubmit(boPhanCheBien);
                ThucDonDienTu.DataContext.SubmitChanges();
                return true;
            }
            catch (Exception e)
            {
                System.Diagnostics.Debug.Write(e.StackTrace);
            }
            return false;
        }

        public static bool CapNhat(BoPhanCheBien boPhanCheBien)
        {
            bool result = false;
            var temp = ThucDonDienTu.DataContext.BoPhanCheBiens.Where(b => b.MaBoPhanCheBien == boPhanCheBien.MaBoPhanCheBien);
            if (temp.Count() > 0)
            {
                BoPhanCheBien b = temp.First();
                b.TaiKhoan = boPhanCheBien.TaiKhoan;
                b.TenBoPhan = boPhanCheBien.TenBoPhan;
                b.MoTa = boPhanCheBien.MoTa;
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
