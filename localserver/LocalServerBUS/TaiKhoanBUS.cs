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

        public static TaiKhoan LayTaiKhoanTheoTenTaiKhoan(string tenTaiKhoan)
        {
            return TaiKhoanDAO.LayTaiKhoanTheoTenTaiKhoan(tenTaiKhoan);
        }


        public static TaiKhoan KiemTraTaiKhoan(string tenDangNhap, string matKhau)
        {
            if (String.IsNullOrEmpty(tenDangNhap)) 
                throw new ArgumentException("Value cannot be null or empty.", "tenKhaiKhoan");
            if (String.IsNullOrEmpty(matKhau)) 
                throw new ArgumentException("Value cannot be null or empty.", "matKhau");
            TaiKhoan taiKhoan = TaiKhoanDAO.LayTaiKhoanTheoTenTaiKhoan(tenDangNhap);
            if (taiKhoan == null || taiKhoan.MatKhau != matKhau || !taiKhoan.Active) 
                return null;
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

        public static bool ThemTaiKhoan(TaiKhoan taiKhoan)
        {
            return TaiKhoanDAO.ThemTaiKhoan(taiKhoan);
        }

        // Chi cho phep Quan ly, Nhan vien phuc vu dang nhap
        public static TaiKhoan ChungThucMobilePhucVu(string tenDangNhap, string matKhau)
        {
            TaiKhoan taiKhoan = KiemTraTaiKhoan(tenDangNhap, matKhau);
            if (taiKhoan == null)
                return null;

            if (taiKhoan.NhomTaiKhoan.MaNhomTaiKhoan == 2 || taiKhoan.NhomTaiKhoan.MaNhomTaiKhoan == 3)
                return taiKhoan;

            return null;
        }

        // Chi cho phep quan ly dang nhap
        public static TaiKhoan ChungThucMobileQuanLy(string tenDangNhap, string matKhau)
        {
            TaiKhoan taiKhoan = KiemTraTaiKhoan(tenDangNhap, matKhau);
            if (taiKhoan == null)
                return null;

            if (taiKhoan.NhomTaiKhoan.MaNhomTaiKhoan == 2)
                return taiKhoan;

            return null;
        }
    }
}
