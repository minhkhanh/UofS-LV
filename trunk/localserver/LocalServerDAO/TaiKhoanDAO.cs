using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class TaiKhoanDAO
    {
        public static List<TaiKhoan> LayDanhSachTaiKhoan()
        {
            return ThucDonDienTu.DataContext.TaiKhoans.ToList();
        }

        public static List<TaiKhoan> LayDanhSachTaiKhoanTheoNhom(int maNhomTaiKhoan)
        {
            var temp = ThucDonDienTu.DataContext.NhomTaiKhoans.Where(t => t.MaNhomTaiKhoan == maNhomTaiKhoan);
            if (temp.Count() > 0)
            {
                NhomTaiKhoan ntk = temp.First();
                return ThucDonDienTu.DataContext.TaiKhoans.Where(t => t.NhomTaiKhoan == ntk).ToList();
            }
            return null;
        }

        public static TaiKhoan LayTaiKhoan(int maTaiKhoan)
        {
            var temp = ThucDonDienTu.DataContext.TaiKhoans.Where(t => t.MaTaiKhoan == maTaiKhoan);
            if (temp.Count() > 0)
            {
                TaiKhoan tk = temp.First();
                return tk;
            }
            return null;
        }

        public static TaiKhoan LayTaiKhoanTheoTenTaiKhoan(string tenKhaiKhoan)
        {
            var temp = ThucDonDienTu.DataContext.TaiKhoans.Where(t => t.TenTaiKhoan == tenKhaiKhoan);
            if (temp.Count() > 0)
            {
                TaiKhoan tk = temp.First();
                return tk;
            }
            return null;
        }
    }
}
