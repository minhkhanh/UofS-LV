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
    }
}
