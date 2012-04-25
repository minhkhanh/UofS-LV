using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class NhomTaiKhoanDAO
    {
        public static List<NhomTaiKhoan> LayDanhSachNhomTaiKhoan()
        {
            return ThucDonDienTu.DataContext.NhomTaiKhoans.ToList();

        }

        public static NhomTaiKhoan LayNhomTaiKhoanTheoMa(int nhomTaiKhoan)
        {
            var temp = ThucDonDienTu.DataContext.NhomTaiKhoans.Where(b => b.MaNhomTaiKhoan == nhomTaiKhoan);
            if (temp.Count() == 0)
                return null;
            return temp.First();
        }
    }
}
