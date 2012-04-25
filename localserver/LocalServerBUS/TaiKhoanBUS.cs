using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class TaiKhoanBUS
    {
        public static List<TaiKhoan> LayDanhSachTaiKhoan()
        {
            return TaiKhoanDAO.LayDanhSachTaiKhoan();
        }

        public static List<TaiKhoan> LayDanhSachTaiKhoanTheoNhom(int maNhomTaiKhoan)
        {
            return TaiKhoanDAO.LayDanhSachTaiKhoanTheoNhom(maNhomTaiKhoan);
        }

        public static TaiKhoan LayTaiKhoan(int maTaiKhoan)
        {
            return TaiKhoanDAO.LayTaiKhoan(maTaiKhoan);
        }

        public static TaiKhoan KiemTraTaiKhoan(string tenKhaiKhoan, string matKhau)
        {
            if (String.IsNullOrEmpty(tenKhaiKhoan)) throw new ArgumentException("Value cannot be null or empty.", "tenKhaiKhoan");
            if (String.IsNullOrEmpty(matKhau)) throw new ArgumentException("Value cannot be null or empty.", "matKhau");
            TaiKhoan taiKhoan = TaiKhoanDAO.LayTaiKhoanTheoTenTaiKhoan(tenKhaiKhoan);
            if (taiKhoan == null || taiKhoan.MatKhau != matKhau || !taiKhoan.Active) return null;
            return taiKhoan;
        }

        public static bool CapNhatTaiKhoan(TaiKhoan taiKhoan)
        {
            return TaiKhoanDAO.CapNhatTaiKhoan(taiKhoan);
        }

        public static bool XoaTaiKhoan(TaiKhoan taiKhoan)
        {
            return TaiKhoanDAO.XoaTaiKhoan(taiKhoan);
        }
    }
}
