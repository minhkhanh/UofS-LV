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
    }
}
